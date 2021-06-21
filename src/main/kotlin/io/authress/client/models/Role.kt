 
package io.authress.client.models

import io.authress.client.models.PermissionObject

/**
 * The role which contains a list of permissions.
 * @param roleId Unique identifier for the role, can be specified on creation, and used by records to map to permissions.
 * @param name A helpful name for this role
 * @param description A description for when to the user as well as additional information.
 * @param permissions A list of the permissions
 */
data class Role (

    /* Unique identifier for the role, can be specified on creation, and used by records to map to permissions. */
    val roleId: kotlin.String,
    /* A helpful name for this role */
    val name: kotlin.String,
    /* A description for when to the user as well as additional information. */
    val description: kotlin.String? = null,
    /* A list of the permissions */
    val permissions: kotlin.Array<PermissionObject>
) {
}