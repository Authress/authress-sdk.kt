 
package io.authress.client.models


/**
 * A map of client specific options
 * @param grantUserPermissionsAccess Grant the client access to verify authorization on behalf of any user.
 */
data class ClientOptions (

    /* Grant the client access to verify authorization on behalf of any user. */
    val grantUserPermissionsAccess: kotlin.Boolean? = null
) {
}