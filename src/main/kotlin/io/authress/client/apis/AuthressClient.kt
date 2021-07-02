 
package io.authress.client.apis

import io.authress.client.models.PermissionResponse
import io.authress.client.models.UserResources
import io.authress.client.models.UserRoleCollection

import io.authress.client.infrastructure.*

class AuthressClient(val authressUrl: kotlin.String) {
    val apiClient: ApiClient = ApiClient(authressUrl)
    val records: AccessRecordsApi = AccessRecordsApi(apiClient)
    val accounts: AccountsApi = AccountsApi(apiClient)
    val login: LoginManagementApi = LoginManagementApi(apiClient)
    val resources: ResourcePermissionsApi = ResourcePermissionsApi(apiClient)
    val roles: RolesApi = RolesApi(apiClient)
    val serviceClients: ServiceClientsApi = ServiceClientsApi(apiClient)
    val userPermissions: UserPermissionsApi = UserPermissionsApi(apiClient)

    constructor(authressUrl: kotlin.String, tokenProvider: ITokenProvider) : this(authressUrl) {
        apiClient.tokenProvider = tokenProvider;
    }

    /**
     * Set's a user token directly into this client to be used on all api calls
     * @param token The user's JWT access token
     * @return void
     */
    fun setToken(token: kotlin.String) {
        apiClient.tokenProvider = ConstantTokenProvider(token);
    }
    /**
     * Get the permissions a user has to a resource.
     * Get a summary of the permissions a user has to a particular resource.
     * @param userId The user to check permissions on 
     * @param resourceUri The uri path of a resource to validate, must be URL encoded, uri segments are allowed. 
     * @return PermissionResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getUserPermissionsForResource(userId: kotlin.String, resourceUri: kotlin.String): PermissionResponse {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/users/{userId}/resources/{resourceUri}/permissions".replace("{" + "userId" + "}", "$userId").replace("{" + "resourceUri" + "}", "$resourceUri")
        )
        val response = apiClient.request<PermissionResponse>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PermissionResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Get the resources a user has to permission to.
     * Get the users resources. This result is a list of resource uris that a user has an explicit permission to, a user with * access to all sub resources will return an empty list. To get a user&#x27;s list of resources in this cases, it is recommended to also check explicit access to the collection resource, using the &lt;strong&gt;authorizeUser&lt;/strong&gt; endpoint. In the case that the user only has access to a subset of resources in a collection, the list will be paginated.
     * @param userId The user to check permissions on 
     * @param resourceUri The top level uri path of a resource to query for. Will only match explicit or collection resource children. Will not partial match resource names. (optional, default to *)
     * @param permissions Permission to check, &#x27;*&#x27; and scoped permissions can also be checked here. By default if the user has any permission explicitly to a resource, it will be included in the list. (optional)
     * @param limit Max number of results to return (optional, default to 20)
     * @param cursor Continuation cursor for paging (will automatically be set) (optional)
     * @return UserResources
     */
    @Suppress("UNCHECKED_CAST")
    fun getUserResources(userId: kotlin.String, resourceUri: kotlin.String? = null, permissions: kotlin.String? = null, limit: kotlin.Int? = null, cursor: kotlin.String? = null): UserResources {
        val localVariableQuery: Map<String, List<String>> = mapOf("resourceUri" to listOf("$resourceUri"), "permissions" to listOf("$permissions"), "limit" to listOf("$limit"), "cursor" to listOf("$cursor"))
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/users/{userId}/resources".replace("{" + "userId" + "}", "$userId"), query = localVariableQuery
        )
        val response = apiClient.request<UserResources>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as UserResources
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Get the roles a user has to a resource.
     * Get a summary of the roles a user has to a particular resource. Users can be assigned roles from multiple access records, this may cause the same role to appear in the list more than once.
     * @param userId The user to get roles for. 
     * @param resourceUri The uri path of a resource to get roles for, must be URL encoded. Checks for explicit resource roles, roles attached to parent resources are not returned. 
     * @return UserRoleCollection
     */
    @Suppress("UNCHECKED_CAST")
    fun getUserRolesForResource(userId: kotlin.String, resourceUri: kotlin.String): UserRoleCollection {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/users/{userId}/resources/{resourceUri}/roles".replace("{" + "userId" + "}", "$userId").replace("{" + "resourceUri" + "}", "$resourceUri")
        )
        val response = apiClient.request<UserRoleCollection>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as UserRoleCollection
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}