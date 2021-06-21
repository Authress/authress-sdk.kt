 
package io.authress.client.models

import io.authress.client.models.ResourcePermissionCollectionLinks
import io.authress.client.models.UserResourcesResources

/**
 * A collect of permissions that the user has to a resource.
 * @param userId 
 * @param resources A list of the resources the user has some permission to.
 * @param links 
 */
data class UserResources (

    val userId: kotlin.String,
    /* A list of the resources the user has some permission to. */
    val resources: kotlin.Array<UserResourcesResources>? = null,
    val links: ResourcePermissionCollectionLinks
) {
}