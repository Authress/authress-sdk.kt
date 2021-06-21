 
package io.authress.client.models

import io.authress.client.models.AccessRequest
import io.authress.client.models.ResourcePermissionCollectionLinks

/**
 * A collection of access requests
 * @param records A list of access requests
 * @param links 
 */
data class AccessRequestCollection (

    /* A list of access requests */
    val records: kotlin.Array<AccessRequest>? = null,
    val links: ResourcePermissionCollectionLinks
) {
}