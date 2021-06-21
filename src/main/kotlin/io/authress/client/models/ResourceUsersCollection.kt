 
package io.authress.client.models

import io.authress.client.models.ResourcePermissionCollectionLinks
import io.authress.client.models.UserRoleCollection

/**
 * A collection of users with explicit permission to a resource.
 * @param users A list of users
 * @param links 
 */
data class ResourceUsersCollection (

    /* A list of users */
    val users: kotlin.Array<UserRoleCollection>,
    val links: ResourcePermissionCollectionLinks
) {
}