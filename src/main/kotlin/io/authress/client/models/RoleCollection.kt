 
package io.authress.client.models

import io.authress.client.models.ResourcePermissionCollectionLinks
import io.authress.client.models.Role

/**
 * A collection of roles
 * @param records A list of roles
 * @param links 
 */
data class RoleCollection (

    /* A list of roles */
    val records: kotlin.Array<Role>? = null,
    val links: ResourcePermissionCollectionLinks
) {
}