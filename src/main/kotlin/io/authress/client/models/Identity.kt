 
package io.authress.client.models


/**
 * The identifying information which uniquely links a JWT OIDC token to an identity provider
 * @param issuer The issuer of the JWT token. This can be any OIDC compliant provider.
 * @param audience The audience of the JWT token. This can be either an audience for your entire app, or one particular audience for it. If there is more than one audience, multiple identities can be created.
 */
data class Identity (

    /* The issuer of the JWT token. This can be any OIDC compliant provider. */
    val issuer: kotlin.String,
    /* The audience of the JWT token. This can be either an audience for your entire app, or one particular audience for it. If there is more than one audience, multiple identities can be created. */
    val audience: kotlin.String
) {
}