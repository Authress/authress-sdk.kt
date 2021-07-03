 
package io.authress.client.apis

import java.net.URLEncoder

import io.authress.client.models.ResourcePermission
import io.authress.client.models.ResourcePermissionCollection
import io.authress.client.models.ResourceUsersCollection

import io.authress.client.infrastructure.*

class ResourcePermissionsApi(val apiClient: ApiClient) {

    /**
     * Get a resource permissions object.
     * Permissions can be set globally at a resource level. This will apply to all users in an account.
     * @param resourceUri The uri path of a resource to validate, must be URL encoded, uri segments are allowed. 
     * @return ResourcePermission
     */
    @Suppress("UNCHECKED_CAST")
    fun getResourcePermissions(resourceUri: kotlin.String): ResourcePermission {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/resources/{resourceUri}".replace("{" + "resourceUri" + "}", URLEncoder.encode(resourceUri))
        )
        val response = apiClient.request<ResourcePermission>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ResourcePermission
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>))
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>))
        }
    }
    /**
     * Get the users that have explicit access to this resource.
     * Get the resource users. This result is a list of users that have some permission to the resource. Users with access to parent resources and users with access only to a sub-resource will not be returned in this result. In the case that the resource has multiple users, the list will be paginated.
     * @param resourceUri The uri path of a resource to validate, must be URL encoded, uri segments are allowed. 
     * @param limit Max number of results to return (optional, default to 20)
     * @param cursor Continuation cursor for paging (will automatically be set) (optional)
     * @return ResourceUsersCollection
     */
    @Suppress("UNCHECKED_CAST")
    fun getResourceUsers(resourceUri: kotlin.String, limit: kotlin.Int? = null, cursor: kotlin.String? = null): ResourceUsersCollection {
        val localVariableQuery: Map<String, List<String>> = mapOf("limit" to listOf("$limit"), "cursor" to listOf("$cursor"))
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/resources/{resourceUri}/users".replace("{" + "resourceUri" + "}", URLEncoder.encode(resourceUri)), query = localVariableQuery
        )
        val response = apiClient.request<ResourceUsersCollection>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ResourceUsersCollection
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>))
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>))
        }
    }
    /**
     * List resource configurations.
     * Permissions can be set globally at a resource level. Lists any resources with a globally set resource policy.
     * @return ResourcePermissionCollection
     */
    @Suppress("UNCHECKED_CAST")
    fun getResources(): ResourcePermissionCollection {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/resources"
        )
        val response = apiClient.request<ResourcePermissionCollection>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ResourcePermissionCollection
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>))
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>))
        }
    }
    /**
     * Update a resource permissions object.
     * Updates the global permissions on a resource. This applies to all users in an account.
     * @param body The contents of the permission to set on the resource. Overwrites existing data. 
     * @param resourceUri The uri path of a resource to validate, must be URL encoded, uri segments are allowed. 
     * @return void
     */
    fun updateResourcePermissions(body: ResourcePermission, resourceUri: kotlin.String): Unit {
        val localVariableBody: kotlin.Any? = body
        
        val localVariableConfig = RequestConfig(
                RequestMethod.PUT,
                "/v1/resources/{resourceUri}".replace("{" + "resourceUri" + "}", URLEncoder.encode(resourceUri))
        )
        val response = apiClient.request<Any?>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>))
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>))
        }
    }
}
