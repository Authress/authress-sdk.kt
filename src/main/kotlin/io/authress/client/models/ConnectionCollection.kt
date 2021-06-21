 
package io.authress.client.models

import io.authress.client.models.Connection

/**
 * A collection of connections.
 * @param connections 
 */
data class ConnectionCollection (

    val connections: kotlin.Array<Connection>
) {
}