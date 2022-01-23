package io.authress.client

import java.util.Base64
import org.json.JSONObject
import com.nimbusds.jose.crypto.Ed25519Verifier
import com.nimbusds.jose.jwk.*
import com.nimbusds.jwt.SignedJWT
import com.nimbusds.jose.util.Base64URL

import io.authress.client.infrastructure.*
import io.authress.client.models.*

public class TokenVerifier(val issuer: kotlin.String?) {
    @Suppress("UNCHECKED_CAST")
    fun verify(authenticationToken: kotlin.String): JSONObject? {
        try {
            val header = JSONObject(String(Base64.getUrlDecoder().decode(authenticationToken.split(".")[0])))
            val payload = JSONObject(String(Base64.getUrlDecoder().decode(authenticationToken.split(".")[1])))
            val keyId = header["kid"] as String?
            val issuer = payload["iss"] as String?

            if (keyId == null) {
                throw IllegalArgumentException("Invalid kid in token")
            }

            if (issuer == null) {
                throw IllegalArgumentException("Invalid issuer in token")
            }

            val request = RequestConfig(RequestMethod.GET, "/.well-known/openid-configuration/jwks")
            val response = ApiClient(issuer).request<Jwks>(request)

            val jwks = when (response.responseType) {
                ResponseType.Success -> (response as Success<*>).data as Jwks
                ResponseType.ServerError -> throw ServerException((response as ServerError<*>))
                ResponseType.ClientError -> throw ClientException((response as ClientError<*>))
                else -> throw ClientException((response as ClientError<*>))
            }
            
            val key = jwks.keys.find { k -> k["kid"] == keyId }
            if (key == null) {
                throw IllegalArgumentException("Invalid JWT: Key Id is no longer valid.")
            }

            val signedJWT = SignedJWT.parse(authenticationToken)
            val octetKey = OctetKeyPair.Builder(Curve.Ed25519, Base64URL(key["x"])).keyUse(KeyUse.SIGNATURE).keyID(keyId).build()
            val verifier = Ed25519Verifier(octetKey)

            if (!signedJWT.verify(verifier)) {
                throw IllegalArgumentException("Token is not valid.")
            }

            return payload
        } catch (e: IllegalArgumentException) {
            throw RuntimeException("Invalid JWT: $authenticationToken")
        }
    }
}