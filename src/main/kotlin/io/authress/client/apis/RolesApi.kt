 
package io.authress.client.apis

import io.authress.client.models.Role
import io.authress.client.models.RoleCollection

import io.authress.client.infrastructure.*

class RolesApi(basePath: kotlin.String = "/") : ApiClient(basePath) {

    /**
     * Create a role.
     * Creates a role with permissions.
     * @param body  
     * @return Role
     */
    @Suppress("UNCHECKED_CAST")
    fun createRole(body: Role): Role {
        val localVariableBody: kotlin.Any? = body
        
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v1/roles"
        )
        val response = request<Role>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Role
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Deletes a role.
     * Remove a role. If a record references the role, that record will not be modified.
     * @param roleId The identifier of the role. 
     * @return void
     */
    fun deleteRole(roleId: kotlin.String): Unit {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/v1/roles/{roleId}".replace("{" + "roleId" + "}", "$roleId")
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Get a role.
     * Roles contain a list of permissions that will be applied to any user or resource
     * @param roleId The identifier of the role. 
     * @return Role
     */
    @Suppress("UNCHECKED_CAST")
    fun getRole(roleId: kotlin.String): Role {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/roles/{roleId}".replace("{" + "roleId" + "}", "$roleId")
        )
        val response = request<Role>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Role
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Get all roles.
     * Get all the account roles. Roles contain a list of permissions that will be applied to any user or resource
     * @return RoleCollection
     */
    @Suppress("UNCHECKED_CAST")
    fun getRoles(): RoleCollection {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/roles"
        )
        val response = request<RoleCollection>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as RoleCollection
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Update a role.
     * Updates a role adding or removing permissions.
     * @param body  
     * @param roleId The identifier of the role. 
     * @return Role
     */
    @Suppress("UNCHECKED_CAST")
    fun updateRole(body: Role, roleId: kotlin.String): Role {
        val localVariableBody: kotlin.Any? = body
        
        val localVariableConfig = RequestConfig(
                RequestMethod.PUT,
                "/v1/roles/{roleId}".replace("{" + "roleId" + "}", "$roleId")
        )
        val response = request<Role>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Role
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
