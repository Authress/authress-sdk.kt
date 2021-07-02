 
package io.authress.client.apis

import io.authress.client.models.Connection
import io.authress.client.models.ConnectionCollection
import io.authress.client.models.Tenant
import io.authress.client.models.TenantCollection

import io.authress.client.infrastructure.*

class LoginManagementApi(val apiClient: ApiClient) {

    /**
     * Create a new SSO connection.
     * Specify identity connection details for Authress identity aggregation.
     * @param body  
     * @return Connection
     */
    @Suppress("UNCHECKED_CAST")
    fun createConnection(body: Connection): Connection {
        val localVariableBody: kotlin.Any? = body
        
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v1/connections"
        )
        val response = apiClient.request<Connection>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Connection
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Create a new tenant.
     * Specify tenant identity details for tenant tracking, separation, and user management. Tenant identifiers are persisted to access tokens created by Authress.
     * @param body  
     * @return Tenant
     */
    @Suppress("UNCHECKED_CAST")
    fun createTenant(body: Tenant): Tenant {
        val localVariableBody: kotlin.Any? = body
        
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v1/tenants"
        )
        val response = apiClient.request<Tenant>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Tenant
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Delete the SSO connection.
     * Delete an identity connection details for Authress identity aggregation.
     * @param connectionId The connection identifier. 
     * @return void
     */
    fun deleteConnection(connectionId: kotlin.String): Unit {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/v1/connections/{connectionId}".replace("{" + "connectionId" + "}", "$connectionId")
        )
        val response = apiClient.request<Any?>(
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
     * Delete the tenant configuration.
     * Delete a tenant. If a connection was created for the tenant then it is deleted as well.
     * @param tenantId The tenantId. 
     * @return void
     */
    fun deleteTenant(tenantId: kotlin.String): Unit {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/v1/tenants/{tenantId}".replace("{" + "tenantId" + "}", "$tenantId")
        )
        val response = apiClient.request<Any?>(
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
     * Get the SSO connection.
     * Get the identity connection details for Authress identity aggregation.
     * @param connectionId The connection identifier. 
     * @return Connection
     */
    @Suppress("UNCHECKED_CAST")
    fun getConnection(connectionId: kotlin.String): Connection {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/connections/{connectionId}".replace("{" + "connectionId" + "}", "$connectionId")
        )
        val response = apiClient.request<Connection>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Connection
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Get all SSO connections.
     * Returns a paginated connection list for the account. Only connections the user has access to are returned.
     * @return ConnectionCollection
     */
    @Suppress("UNCHECKED_CAST")
    fun getConnections(): ConnectionCollection {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/connections"
        )
        val response = apiClient.request<ConnectionCollection>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ConnectionCollection
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Get the tenant.
     * Get the tenant details for an Authress tenant.
     * @param tenantId The tenantId. 
     * @return Tenant
     */
    @Suppress("UNCHECKED_CAST")
    fun getTenant(tenantId: kotlin.String): Tenant {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/tenants/{tenantId}".replace("{" + "tenantId" + "}", "$tenantId")
        )
        val response = apiClient.request<Tenant>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Tenant
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Get all tenants.
     * Returns a paginated tenants list for the account. Only tenants the user has access to are returned.
     * @return TenantCollection
     */
    @Suppress("UNCHECKED_CAST")
    fun getTenants(): TenantCollection {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/tenants"
        )
        val response = apiClient.request<TenantCollection>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as TenantCollection
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Update the SSO connection.
     * Specify identity connection details for Authress identity aggregation.
     * @param body  
     * @param connectionId The connection identifier. 
     * @return Connection
     */
    @Suppress("UNCHECKED_CAST")
    fun updateConnection(body: Connection, connectionId: kotlin.String): Connection {
        val localVariableBody: kotlin.Any? = body
        
        val localVariableConfig = RequestConfig(
                RequestMethod.PUT,
                "/v1/connections/{connectionId}".replace("{" + "connectionId" + "}", "$connectionId")
        )
        val response = apiClient.request<Connection>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Connection
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Update a tenant&#x27;s configuration.
     * Updates the tenants information and linked tenant if it exists.
     * @param body  
     * @param tenantId The tenantId. 
     * @return Tenant
     */
    @Suppress("UNCHECKED_CAST")
    fun updateTenant(body: Tenant, tenantId: kotlin.String): Tenant {
        val localVariableBody: kotlin.Any? = body
        
        val localVariableConfig = RequestConfig(
                RequestMethod.PUT,
                "/v1/tenants/{tenantId}".replace("{" + "tenantId" + "}", "$tenantId")
        )
        val response = apiClient.request<Tenant>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Tenant
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
