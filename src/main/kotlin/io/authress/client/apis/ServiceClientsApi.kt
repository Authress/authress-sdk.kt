 
package io.authress.client.apis

import io.authress.client.models.Client
import io.authress.client.models.ClientAccessKey
import io.authress.client.models.ClientCollection

import io.authress.client.infrastructure.*

class ServiceClientsApi(val apiClient: ApiClient) {

    /**
     * Create a new client.
     * Creates a service client to interact with Authress or any other service on behalf of users. Each client has secret private keys used to authenticate with Authress. To use service clients created through other mechanisms, skip creating a client and create access records with the client identifier.
     * @param body  
     * @return Client
     */
    @Suppress("UNCHECKED_CAST")
    fun createClient(body: Client): Client {
        val localVariableBody: kotlin.Any? = body
        
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v1/clients"
        )
        val response = apiClient.request<Client>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Client
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Remove an access key for a client.
     * Deletes an access key for a client prevent it from being used to authenticate with Authress.
     * @param clientId The unique identifier of the client. 
     * @param keyId The id of the access key to remove from the client. 
     * @return void
     */
    fun deleteAccessKey(clientId: kotlin.String, keyId: kotlin.String): Unit {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/v1/clients/{clientId}/access-keys/{keyId}".replace("{" + "clientId" + "}", "$clientId").replace("{" + "keyId" + "}", "$keyId")
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
     * Delete a client.
     * This deletes the service client.
     * @param clientId The unique identifier for the client. 
     * @return void
     */
    fun deleteClient(clientId: kotlin.String): Unit {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/v1/clients/{clientId}".replace("{" + "clientId" + "}", "$clientId")
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
     * Get a client.
     * Returns all information related to client except for the private access keys.
     * @param clientId The unique identifier for the client. 
     * @return Client
     */
    @Suppress("UNCHECKED_CAST")
    fun getClient(clientId: kotlin.String): Client {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/clients/{clientId}".replace("{" + "clientId" + "}", "$clientId")
        )
        val response = apiClient.request<Client>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Client
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Get clients collection.
     * Returns all clients that the user has access to in the account.
     * @return ClientCollection
     */
    @Suppress("UNCHECKED_CAST")
    fun getClients(): ClientCollection {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/clients"
        )
        val response = apiClient.request<ClientCollection>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ClientCollection
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Request a new access key.
     * Create a new access key for the client so that a service can authenticate with Authress as that client. Using the client will allow delegation of permission checking of users.
     * @param clientId The unique identifier of the client. 
     * @return ClientAccessKey
     */
    @Suppress("UNCHECKED_CAST")
    fun requestAccessKey(clientId: kotlin.String): ClientAccessKey {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v1/clients/{clientId}/access-keys".replace("{" + "clientId" + "}", "$clientId")
        )
        val response = apiClient.request<ClientAccessKey>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ClientAccessKey
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Update a client.
     * Updates a client information.
     * @param body  
     * @param clientId The unique identifier for the client. 
     * @return Client
     */
    @Suppress("UNCHECKED_CAST")
    fun updateClient(body: Client, clientId: kotlin.String): Client {
        val localVariableBody: kotlin.Any? = body
        
        val localVariableConfig = RequestConfig(
                RequestMethod.PUT,
                "/v1/clients/{clientId}".replace("{" + "clientId" + "}", "$clientId")
        )
        val response = apiClient.request<Client>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Client
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
