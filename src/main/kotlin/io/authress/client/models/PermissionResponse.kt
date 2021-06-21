 
package io.authress.client.models

import io.authress.client.models.PermissionObject

/**
 * A collect of permissions that the user has to a resource.
 * @param userId 
 * @param permissions A list of the permissions
 */
data class PermissionResponse (

    val userId: kotlin.String,
    /* A list of the permissions */
    val permissions: kotlin.Array<PermissionObject>
) {
}