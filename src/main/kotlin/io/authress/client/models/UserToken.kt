 
package io.authress.client.models

import io.authress.client.models.AccountLinks

/**
 * A JWT token with represents the user.
 * @param userId 
 * @param tokenId The unique identifier for the token
 * @param token The access token
 * @param links 
 */
data class UserToken (

    val userId: kotlin.String,
    /* The unique identifier for the token */
    val tokenId: kotlin.String,
    /* The access token */
    val token: kotlin.String,
    val links: AccountLinks? = null
) {
}