package io.authress.client

import java.util.Base64
import org.json.JSONObject
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import com.nimbusds.jose.crypto.Ed25519Verifier
import com.nimbusds.jose.jwk.*
import com.nimbusds.jwt.SignedJWT
import com.nimbusds.jose.util.Base64URL

import io.authress.client.infrastructure.*
import io.authress.client.models.*

/**
 * The token could not be trusted. The message never contains the token.
 */
class TokenVerificationException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)

internal data class CanonicalIssuer(val url: String, val path: String)

private val issuerPattern = Regex("^([a-z][a-z0-9+.-]*://[^/@?#\\\\]+)(/[^?#\\\\]*)?\\z", RegexOption.IGNORE_CASE)
private val encodedPathSeparatorPattern = Regex("%(2e|2f|5c)", RegexOption.IGNORE_CASE)
private val serviceClientPathPattern = Regex("^/v\\d/clients/([^/]+)/?$")
private val authressDomainPattern = Regex("^([a-z0-9-]+)[.][a-z0-9-]+[.]authress[.]io$")
private val localDomainPattern = Regex("^(localhost|authress\\.localhost\\.localstack\\.cloud:4566$)")

/**
 * The service client check and the JWKS fetch must read the same issuer value, so any issuer that a runtime could normalise differently is rejected.
 */
internal fun getCanonicalIssuer(issuer: String): CanonicalIssuer? {
    val issuerMatch = issuerPattern.matchEntire(issuer) ?: return null
    val path = (issuerMatch.groupValues[2]).removeSuffix("/")
    val segments = if (path.isEmpty()) emptyList() else path.substring(1).split("/")
    if (segments.any { it.isEmpty() || it == "." || it == ".." || encodedPathSeparatorPattern.containsMatchIn(it) }) {
        return null
    }

    return CanonicalIssuer("${issuerMatch.groupValues[1]}$path", path)
}

private fun getOrigin(url: String): String? {
    val httpUrl = url.toHttpUrlOrNull() ?: return null
    return "${httpUrl.scheme}://${httpUrl.host}:${httpUrl.port}"
}

/**
 * Authress accounts are reachable on both the login and api hosts, so tokens issued from either are accepted for the configured account domain.
 */
internal fun getAllowedIssuerOrigins(configuredIssuer: String): Set<String> {
    val domainWithScheme = when {
        configuredIssuer.startsWith("http") -> configuredIssuer
        localDomainPattern.containsMatchIn(configuredIssuer) -> "http://$configuredIssuer"
        else -> "https://$configuredIssuer"
    }
    val configuredUrl = domainWithScheme.trimEnd('/').toHttpUrlOrNull() ?: return emptySet()
    val origins = mutableSetOf(getOrigin(configuredUrl.toString())!!)
    val accountId = authressDomainPattern.matchEntire(configuredUrl.host)?.groupValues?.get(1)
    if (accountId != null) {
        origins.add(getOrigin("https://$accountId.login.authress.io")!!)
        origins.add(getOrigin("https://$accountId.api.authress.io")!!)
    }
    return origins
}

/**
 * Verifies Authress tokens against the keys published by the configured Authress custom domain.
 */
public class TokenVerifier internal constructor(val issuer: kotlin.String?, private val httpClient: OkHttpClient) {
    constructor(issuer: kotlin.String?) : this(issuer, ApiClient.client)

    fun verify(authenticationToken: kotlin.String): JSONObject? {
        try {
            return verifyToken(authenticationToken)
        } catch (e: TokenVerificationException) {
            throw e
        } catch (e: Exception) {
            throw TokenVerificationException("Unauthorized: Invalid token", e)
        }
    }

    private fun verifyToken(authenticationToken: kotlin.String): JSONObject {
        val configuredIssuer = issuer?.takeIf { it.isNotBlank() }
            ?: throw TokenVerificationException("Unauthorized: The Authress custom domain must be specified")

        val tokenParts = authenticationToken.split(".")
        if (tokenParts.size != 3) {
            throw TokenVerificationException("Unauthorized: Invalid token")
        }

        val header = JSONObject(String(Base64.getUrlDecoder().decode(tokenParts[0])))
        val payload = JSONObject(String(Base64.getUrlDecoder().decode(tokenParts[1])))

        val keyId = header.optString("kid")
        if (keyId.isBlank()) {
            throw TokenVerificationException("Unauthorized: No KID in token")
        }

        val canonicalIssuer = getCanonicalIssuer(payload.optString("iss"))
        if (canonicalIssuer == null || getOrigin(canonicalIssuer.url) !in getAllowedIssuerOrigins(configuredIssuer)) {
            throw TokenVerificationException("Unauthorized: Invalid Issuer")
        }

        if (!payload.has("exp") || payload.optLong("exp") * 1000 <= System.currentTimeMillis()) {
            throw TokenVerificationException("Unauthorized: Token is expired")
        }

        val serviceClientId = serviceClientPathPattern.matchEntire(canonicalIssuer.path)?.groupValues?.get(1)
        if (serviceClientId != null && serviceClientId != payload.optString("sub")) {
            throw TokenVerificationException("Unauthorized: Invalid Sub found for service client token")
        }

        val request = RequestConfig(RequestMethod.GET, "/.well-known/openid-configuration/jwks", query = mapOf("kid" to listOf(keyId)))
        val response = ApiClient(canonicalIssuer.url, httpClient).request<Jwks>(request)

        val jwks = when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Jwks
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>))
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>))
            else -> throw ClientException((response as ClientError<*>))
        }

        val key = jwks.keys.find { k -> k["kid"] == keyId }
            ?: throw TokenVerificationException("Unauthorized: Key Id is no longer valid")

        val signedJWT = SignedJWT.parse(authenticationToken)
        val octetKey = OctetKeyPair.Builder(Curve.Ed25519, Base64URL(key["x"])).keyUse(KeyUse.SIGNATURE).keyID(keyId).build()
        if (!signedJWT.verify(Ed25519Verifier(octetKey))) {
            throw TokenVerificationException("Unauthorized: Token signature is not valid")
        }

        return payload
    }
}
