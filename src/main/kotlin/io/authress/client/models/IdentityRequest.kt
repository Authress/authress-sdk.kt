 
package io.authress.client.models


/**
 * Request to link an identity provider's audience and your app's audience with Authress.
 * @param jwt A valid JWT OIDC compliant token which will still pass authentication requests to the identity provider. Must contain a unique audience and issuer.
 * @param preferredAudience If the `jwt` token contains more than one valid audience, then the single audience that should associated with Authress. If more than one audience is preferred, repeat this call with each one.
 */
data class IdentityRequest (

    /* A valid JWT OIDC compliant token which will still pass authentication requests to the identity provider. Must contain a unique audience and issuer. */
    val jwt: kotlin.String,
    /* If the `jwt` token contains more than one valid audience, then the single audience that should associated with Authress. If more than one audience is preferred, repeat this call with each one. */
    val preferredAudience: kotlin.String? = null
) {
}