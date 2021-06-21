 
package io.authress.client.models

import io.authress.client.models.Client
import io.authress.client.models.ResourcePermissionCollectionLinks

/**
 * The collection of a list of clients
 * @param clients A list of clients
 * @param links 
 */
data class ClientCollection (

    /* A list of clients */
    val clients: kotlin.Array<Client>,
    val links: ResourcePermissionCollectionLinks
) {
}