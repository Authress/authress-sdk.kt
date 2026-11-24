import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.crypto.Ed25519Signer
import com.nimbusds.jose.jwk.Curve
import com.nimbusds.jose.jwk.OctetKeyPair
import com.nimbusds.jose.jwk.gen.OctetKeyPairGenerator
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import io.authress.client.TokenVerificationException
import io.authress.client.TokenVerifier
import io.authress.client.getAllowedIssuerOrigins
import org.json.JSONObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.Date
import java.util.stream.Stream

internal class TokenVerifierTest {
    private val signingKey: OctetKeyPair = OctetKeyPairGenerator(Curve.Ed25519).generate()

    private fun jwksStub(statusCode: Int = 200) = StubHttpClient(statusCode) { request ->
        val key = JSONObject().put("kid", request.url.queryParameter("kid")).put("kty", "OKP").put("crv", "Ed25519").put("x", signingKey.x.toString())
        JSONObject().put("keys", listOf(key)).toString()
    }

    private fun token(
        issuer: String?,
        sub: String = "user_alice",
        keyId: String? = "kid-1",
        expiresInSeconds: Long? = 3600,
        key: OctetKeyPair = signingKey
    ): String {
        val claims = JWTClaimsSet.Builder().subject(sub)
        if (issuer != null) claims.issuer(issuer)
        if (expiresInSeconds != null) claims.expirationTime(Date(System.currentTimeMillis() + expiresInSeconds * 1000))
        val header = JWSHeader.Builder(JWSAlgorithm.EdDSA).apply { if (keyId != null) keyID(keyId) }.build()
        val jwt = SignedJWT(header, claims.build())
        jwt.sign(Ed25519Signer(key))
        return jwt.serialize()
    }

    private fun assertRejected(http: StubHttpClient, configuredIssuer: String?, token: String) {
        val error = assertThrows(TokenVerificationException::class.java) { TokenVerifier(configuredIssuer, http.client).verify(token) }
        assertFalse(token.isNotEmpty() && error.message!!.contains(token), "the bearer token must never appear in the error message")
    }

    companion object {
        private const val clientId = "client_abc"
        private const val authressUrl = "https://authress.company.com"
        private const val attackerUrl = "https://attacker.example"

        @JvmStatic
        fun issuerPathCases(): Stream<Arguments> = Stream.of(
            Arguments.of("service client token with its own sub", "/v1/clients/$clientId", clientId, true),
            Arguments.of("service client token claiming another sub", "/v1/clients/$clientId", "user_alice", false),
            Arguments.of("issuer with no path", "", "user_alice", true),
            Arguments.of("issuer with an arbitrary non-client path", "/tenant/custom", "user_alice", true),
            Arguments.of("issuer with a deep non-client path", "/a/b/c/d", "user_alice", true),
            Arguments.of("issuer with a version-like dotted segment", "/v1.0/tenants/acme", "user_alice", true),
            Arguments.of("issuer with deep path and trailing slash", "/a/b/c/", "user_alice", true),
            Arguments.of("service client with trailing slash and its own sub", "/v1/clients/$clientId/", clientId, true),
            Arguments.of("service client with trailing slash claiming another sub", "/v1/clients/$clientId/", "user_alice", false),
            Arguments.of("client path nested below another prefix is not a service client issuer", "/tenant/v1/clients/$clientId", "user_alice", true),
            Arguments.of("client path with extra segment is not a service client issuer", "/v1/clients/$clientId/extra", "user_alice", true),
            Arguments.of("trailing dot segment hides the client id", "/v1/clients/$clientId/.", "user_alice", false),
            Arguments.of("dot segment inside the path", "/v1/./clients/$clientId", clientId, false),
            Arguments.of("parent segment redirects the client id after the sub match", "/v1/clients/user_alice/../$clientId", "user_alice", false),
            Arguments.of("parent segment moves the path out of the clients prefix", "/v1/x/../clients/$clientId", "user_alice", false),
            Arguments.of("parent segment in a non-client path", "/a/b/../c", "user_alice", false),
            Arguments.of("percent-encoded dot segment", "/v1/clients/$clientId/%2e", "user_alice", false),
            Arguments.of("percent-encoded slash in a segment", "/tenant/a%2Fb", "user_alice", false),
            Arguments.of("percent-encoded backslash in a segment", "/tenant/a%5cb", "user_alice", false),
            Arguments.of("backslash treated as a path separator", "/v1/clients/$clientId\\.", "user_alice", false),
            Arguments.of("query appended to the issuer swallows the jwks path", "/v1/clients/user_alice?", "user_alice", false),
            Arguments.of("fragment appended to the issuer swallows the jwks path", "/v1/clients/$clientId#", clientId, false),
            Arguments.of("empty segment in a non-client path", "/tenant//custom", "user_alice", false),
            Arguments.of("double trailing slash", "/v1/clients/$clientId//", "user_alice", false),
            Arguments.of("trailing newline", "/v1/clients/$clientId\n", "user_alice", false)
        )
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("issuerPathCases")
    fun issuerPathIsValidatedBeforeAnyKeysAreFetched(label: String, issuerPath: String, sub: String, accepted: Boolean) {
        val http = jwksStub()
        val signed = token("$authressUrl$issuerPath", sub)

        if (accepted) {
            assertEquals(sub, TokenVerifier(authressUrl, http.client).verify(signed)!!.getString("sub"), label)
            return
        }

        assertRejected(http, authressUrl, signed)
        assertEquals(emptyList<String>(), http.requestedUrls)
    }

    @Test
    fun keysAreFetchedFromTheValidatedIssuerPathWithTheKeyId() {
        val http = jwksStub()

        TokenVerifier(authressUrl, http.client).verify(token("$authressUrl/v1/clients/$clientId/", clientId))

        assertEquals(listOf("$authressUrl/v1/clients/$clientId/.well-known/openid-configuration/jwks?kid=kid-1"), http.requestedUrls)
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "/", "//"])
    fun configuredIssuerTrailingSlashesAreIgnored(trailingSlashes: String) {
        val verified = TokenVerifier("$authressUrl$trailingSlashes", jwksStub().client).verify(token(authressUrl))

        assertEquals("user_alice", verified!!.getString("sub"))
    }

    @Test
    fun tokenFromAnotherIssuerIsRejectedWithoutContactingIt() {
        val http = jwksStub()

        assertRejected(http, authressUrl, token(attackerUrl))

        assertEquals(emptyList<String>(), http.requestedUrls)
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "   "])
    fun missingConfiguredIssuerRejectsEveryToken(configuredIssuer: String) {
        val http = jwksStub()

        assertRejected(http, configuredIssuer, token(attackerUrl))

        assertEquals(emptyList<String>(), http.requestedUrls)
    }

    @Test
    fun nullConfiguredIssuerRejectsEveryToken() {
        val http = jwksStub()

        assertRejected(http, null, token(attackerUrl))

        assertEquals(emptyList<String>(), http.requestedUrls)
    }

    @Test
    fun expiredTokenIsRejected() {
        assertRejected(jwksStub(), authressUrl, token(authressUrl, expiresInSeconds = -60))
    }

    @Test
    fun tokenWithoutExpirationIsRejected() {
        assertRejected(jwksStub(), authressUrl, token(authressUrl, expiresInSeconds = null))
    }

    @Test
    fun tokenWithoutKeyIdIsRejectedWithoutFetchingKeys() {
        val http = jwksStub()

        assertRejected(http, authressUrl, token(authressUrl, keyId = null))

        assertEquals(emptyList<String>(), http.requestedUrls)
    }

    @Test
    fun tokenWithoutIssuerIsRejected() {
        assertRejected(jwksStub(), authressUrl, token(null))
    }

    @Test
    fun tokenSignedByAnotherKeyIsRejected() {
        val otherKey = OctetKeyPairGenerator(Curve.Ed25519).generate()

        assertRejected(jwksStub(), authressUrl, token(authressUrl, key = otherKey))
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "not-a-token", "a.b", "a.b.c", "....", "eyJhbGciOiJFZERTQSJ9.e30.c2ln"])
    fun malformedTokensAreRejectedWithTheVerificationException(malformed: String) {
        assertRejected(jwksStub(), authressUrl, malformed)
    }

    @ParameterizedTest
    @ValueSource(ints = [404, 500])
    fun keyFetchFailuresAreRejectedWithTheVerificationException(statusCode: Int) {
        assertRejected(jwksStub(statusCode), authressUrl, token(authressUrl))
    }

    @Test
    fun allowedIssuerOriginsForACustomDomain() {
        assertEquals(setOf("https://authress.company.com:443"), getAllowedIssuerOrigins("authress.company.com//"))
    }

    @Test
    fun allowedIssuerOriginsForAnAuthressDomainIncludeTheLoginAndApiHosts() {
        assertEquals(
            setOf("https://acc.api-eu-west.authress.io:443", "https://acc.login.authress.io:443", "https://acc.api.authress.io:443"),
            getAllowedIssuerOrigins("https://acc.api-eu-west.authress.io")
        )
    }

    @Test
    fun allowedIssuerOriginsForLocalhostUseHttp() {
        assertEquals(setOf("http://localhost:8080"), getAllowedIssuerOrigins("localhost:8080"))
    }

    @Test
    fun allowedIssuerOriginsForAnInvalidDomainAreEmpty() {
        assertTrue(getAllowedIssuerOrigins("https://").isEmpty())
    }
}
