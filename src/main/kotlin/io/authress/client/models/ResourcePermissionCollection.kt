 
package io.authress.client.models

import io.authress.client.models.ResourcePermission
import io.authress.client.models.ResourcePermissionCollectionLinks

/**
 * A collection of resource permissions that have been defined.
 * @param resources 
 * @param links 
 */
data class ResourcePermissionCollection (

    val resources: kotlin.Array<ResourcePermission>,
    val links: ResourcePermissionCollectionLinks
) {
}