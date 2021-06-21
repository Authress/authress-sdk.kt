 
package io.authress.client.models

import io.authress.client.models.ClientOptions

/**
 * A client configuration.
 * @param clientId The unique id of the client.
 * @param createdTime 
 * @param name The name of the client
 * @param options 
 */
data class Client (

    /* The unique id of the client. */
    val clientId: kotlin.String,
    val createdTime: java.time.LocalDateTime,
    /* The name of the client */
    val name: kotlin.String? = null,
    val options: ClientOptions? = null
) {
}