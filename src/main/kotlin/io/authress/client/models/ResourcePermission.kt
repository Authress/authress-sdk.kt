 
package io.authress.client.models

import io.authress.client.models.ResourcePermissionPermissions

/**
 * 
 * @param permissions 
 */
data class ResourcePermission (

    val permissions: kotlin.Array<ResourcePermissionPermissions>
) {
}