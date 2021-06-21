 
package io.authress.client.models

import io.authress.client.models.UserRole

/**
 * A collect of roles that the user has to a resource.
 * @param userId 
 * @param roles A list of the roles
 */
data class UserRoleCollection (

    val userId: kotlin.String,
    /* A list of the roles */
    val roles: kotlin.Array<UserRole>
) {
}