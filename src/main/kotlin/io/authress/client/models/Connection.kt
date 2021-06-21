 
package io.authress.client.models

import io.authress.client.models.ConnectionData

/**
 * 
 * @param connectionId 
 * @param authenticationUrl 
 * @param tokenUrl 
 * @param clientId 
 * @param clientSecret 
 * @param &#x60;data&#x60; 
 * @param createdTime 
 */
data class Connection (

    val connectionId: kotlin.String? = null,
    val authenticationUrl: kotlin.String,
    val tokenUrl: kotlin.String,
    val clientId: kotlin.String? = null,
    val clientSecret: kotlin.String? = null,
    val `data`: ConnectionData? = null,
    val createdTime: java.time.LocalDateTime? = null
) {
}