 
package io.authress.client.models

import io.authress.client.models.AccountLinks

/**
 * 
 * @param accountId 
 * @param createdTime 
 * @param domain The top authress sub domain specific for this account to be used with this API.
 * @param company 
 * @param links 
 */
data class Account (

    val accountId: kotlin.String,
    val createdTime: java.time.LocalDateTime,
    /* The top authress sub domain specific for this account to be used with this API. */
    val domain: kotlin.String? = null,
    val company: kotlin.Any,
    val links: AccountLinks
) {
}