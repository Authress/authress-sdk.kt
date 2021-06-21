 
package io.authress.client.models


/**
 * 
 * @param action 
 * @param allow 
 */
data class ResourcePermissionPermissions (

    val action: ResourcePermissionPermissions.Action,
    val allow: kotlin.Boolean
) {
    /**
    * 
    * Values: CLAIM,PUBLIC
    */
    enum class Action(val value: kotlin.String){
        CLAIM("CLAIM"),
        PUBLIC("PUBLIC");
    }
}