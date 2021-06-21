 
package io.authress.client.models


/**
 * The collective action and associate grants on a permission
 * @param action The action the permission grants, can be scoped using `:` and parent actions imply sub-resource permissions, action:* or action implies action:sub-action. This property is case-insensitive, it will always be cast to lowercase before comparing actions to user permissions.
 * @param allow Does this permission grant the user the ability to execute the action?
 * @param grant Allows the user to give the permission to others without being able to execute the action.
 * @param &#x60;delegate&#x60; Allows delegating or granting the permission to others without being able to execute tha action.
 */
data class PermissionObject (

    /* The action the permission grants, can be scoped using `:` and parent actions imply sub-resource permissions, action:* or action implies action:sub-action. This property is case-insensitive, it will always be cast to lowercase before comparing actions to user permissions. */
    val action: kotlin.String,
    /* Does this permission grant the user the ability to execute the action? */
    val allow: kotlin.Boolean,
    /* Allows the user to give the permission to others without being able to execute the action. */
    val grant: kotlin.Boolean,
    /* Allows delegating or granting the permission to others without being able to execute tha action. */
    val `delegate`: kotlin.Boolean
) {
}