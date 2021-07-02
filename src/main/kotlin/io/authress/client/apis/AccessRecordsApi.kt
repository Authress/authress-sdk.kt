 
package io.authress.client.apis

import io.authress.client.models.AccessRecord
import io.authress.client.models.AccessRecordCollection
import io.authress.client.models.AccessRequest
import io.authress.client.models.AccessRequestCollection
import io.authress.client.models.AccessRequestResponse
import io.authress.client.models.Account
import io.authress.client.models.ClaimRequest
import io.authress.client.models.ClaimResponse
import io.authress.client.models.Invite

import io.authress.client.infrastructure.*

class AccessRecordsApi(val apiClient: ApiClient) {

    /**
     * Claim a resource by an allowed user.
     * Claim a resource by allowing a user to pick an identifier and receive admin access to that resource if it hasn&#x27;t already been claimed. This only works for resources specifically marked as &lt;strong&gt;CLAIM&lt;/strong&gt;. The result will be a new access record listing that user as the admin for this resource. The resourceUri will be appended to the collection resource uri using a &#x27;/&#x27; (forward slash) automatically.
     * @param body  
     * @return ClaimResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun createClaim(body: ClaimRequest): ClaimResponse {
        val localVariableBody: kotlin.Any? = body
        
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v1/claims"
        )
        val response = apiClient.request<ClaimResponse>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ClaimResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Create a new invite.
     * Invites are used to easily assign permissions to users that have not been created in your identity provider yet. Create the invite with an email address, and then when the user accepts the invite they will automatically get the permissions assigned here. Invites automatically expire after 7 days.
     * @param body  
     * @return Invite
     */
    @Suppress("UNCHECKED_CAST")
    fun createInvite(body: Invite): Invite {
        val localVariableBody: kotlin.Any? = body
        
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v1/invites"
        )
        val response = apiClient.request<Invite>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Invite
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Create a new access record.
     * Specify user roles for specific resources. (Records have a maximum size of ~100KB)
     * @param body  
     * @return AccessRecord
     */
    @Suppress("UNCHECKED_CAST")
    fun createRecord(body: AccessRecord): AccessRecord {
        val localVariableBody: kotlin.Any? = body
        
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v1/records"
        )
        val response = apiClient.request<AccessRecord>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as AccessRecord
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Create a new access request.
     * Specify a request in the form of an access record that an admin can approve.
     * @param body  
     * @return AccessRequest
     */
    @Suppress("UNCHECKED_CAST")
    fun createRequest(body: AccessRequest): AccessRequest {
        val localVariableBody: kotlin.Any? = body
        
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v1/requests"
        )
        val response = apiClient.request<AccessRequest>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as AccessRequest
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Delete an invite.
     * Deletes an invite.
     * @param inviteId The identifier of the invite. 
     * @return void
     */
    fun deleteInvite(inviteId: kotlin.String): Unit {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/v1/invites/{inviteId}".replace("{" + "inviteId" + "}", "$inviteId")
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
     * Deletes an access record.
     * Remove an access record, removing associated permissions from all users in record. If a user has a permission from another record, that permission will not be removed. (Note: This disables the record by changing the status to &lt;strong&gt;DELETED&lt;/strong&gt; but not completely remove the record for tracking purposes.
     * @param recordId The identifier of the access record. 
     * @return void
     */
    fun deleteRecord(recordId: kotlin.String): Unit {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/v1/records/{recordId}".replace("{" + "recordId" + "}", "$recordId")
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
     * Deletes an access request.
     * Remove an access request.
     * @param requestId The identifier of the access request. 
     * @return void
     */
    fun deleteRequest(requestId: kotlin.String): Unit {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/v1/requests/{requestId}".replace("{" + "requestId" + "}", "$requestId")
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
     * Get an access record for the account.
     * Access records contain information assigning permissions to users for resources.
     * @param recordId The identifier of the access record. 
     * @return AccessRecord
     */
    @Suppress("UNCHECKED_CAST")
    fun getRecord(recordId: kotlin.String): AccessRecord {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/records/{recordId}".replace("{" + "recordId" + "}", "$recordId")
        )
        val response = apiClient.request<AccessRecord>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as AccessRecord
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Get all account records.
     * Returns a paginated records list for the account. Only records the user has access to are returned. This query resource is meant for administrative actions only, therefore has a lower rate limit tier than user permissions related resources.
     * @param limit Max number of results to return (optional, default to 20)
     * @param cursor Continuation cursor for paging (will automatically be set) (optional)
     * @param filter Filter to search records by. This is a case insensitive search through every text field. (optional)
     * @param status Filter records by their current status. (optional)
     * @return AccessRecordCollection
     */
    @Suppress("UNCHECKED_CAST")
    fun getRecords(limit: kotlin.Int? = null, cursor: kotlin.String? = null, filter: kotlin.String? = null, status: kotlin.String? = null): AccessRecordCollection {
        val localVariableQuery: Map<String, List<String>> = mapOf("limit" to listOf("$limit"), "cursor" to listOf("$cursor"), "filter" to listOf("$filter"), "status" to listOf("$status"))
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/records", query = localVariableQuery
        )
        val response = apiClient.request<AccessRecordCollection>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as AccessRecordCollection
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Get an access request for the account.
     * Access request contain information requesting permissions for users to resources.
     * @param requestId The identifier of the access request. 
     * @return AccessRequest
     */
    @Suppress("UNCHECKED_CAST")
    fun getRequest(requestId: kotlin.String): AccessRequest {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/requests/{requestId}".replace("{" + "requestId" + "}", "$requestId")
        )
        val response = apiClient.request<AccessRequest>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as AccessRequest
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Get all access requests.
     * Returns a paginated request list. Only requests the user has access to are returned. This query resource is meant for administrative actions only, therefore has a lower rate limit tier than user permissions related resources.
     * @param limit Max number of results to return (optional, default to 20)
     * @param cursor Continuation cursor for paging (will automatically be set) (optional)
     * @param status Filter records by their current status. (optional)
     * @return AccessRequestCollection
     */
    @Suppress("UNCHECKED_CAST")
    fun getRequests(limit: kotlin.Int? = null, cursor: kotlin.String? = null, status: kotlin.String? = null): AccessRequestCollection {
        val localVariableQuery: Map<String, List<String>> = mapOf("limit" to listOf("$limit"), "cursor" to listOf("$cursor"), "status" to listOf("$status"))
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/requests", query = localVariableQuery
        )
        val response = apiClient.request<AccessRequestCollection>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as AccessRequestCollection
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Approve or deny an access request.
     * Updates an access request, approving it or denying it.
     * @param body  
     * @param requestId The identifier of the access request. 
     * @return AccessRequest
     */
    @Suppress("UNCHECKED_CAST")
    fun respondToAccessRequest(body: AccessRequestResponse, requestId: kotlin.String): AccessRequest {
        val localVariableBody: kotlin.Any? = body
        
        val localVariableConfig = RequestConfig(
                RequestMethod.PATCH,
                "/v1/requests/{requestId}".replace("{" + "requestId" + "}", "$requestId")
        )
        val response = apiClient.request<AccessRequest>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as AccessRequest
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Accept an invite.
     * Accepts an invite by claiming this invite by this user. The user token used for this request will gain the permissions associated with the invite.
     * @param inviteId The identifier of the invite. 
     * @return Account
     */
    @Suppress("UNCHECKED_CAST")
    fun respondToInvite(inviteId: kotlin.String): Account {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.PATCH,
                "/v1/invites/{inviteId}".replace("{" + "inviteId" + "}", "$inviteId")
        )
        val response = apiClient.request<Account>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Account
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Update an access record.
     * Updates an access record adding or removing user permissions to resources. (Records have a maximum size of ~100KB)
     * @param body  
     * @param recordId The identifier of the access record. 
     * @param ifUnmodifiedSince The expected last time the record was modified. (&lt;a href&#x3D;\&quot;https://tools.ietf.org/html/rfc7231#section-7.1.1.1\&quot; target&#x3D;\&quot;_blank\&quot;&gt;format&lt;/a&gt;) (optional)
     * @return AccessRecord
     */
    @Suppress("UNCHECKED_CAST")
    fun updateRecord(body: AccessRecord, recordId: kotlin.String, ifUnmodifiedSince: kotlin.String? = null): AccessRecord {
        val localVariableBody: kotlin.Any? = body
        
        val localVariableHeaders: kotlin.collections.Map<kotlin.String, kotlin.String> = mapOf("If-Unmodified-Since" to ifUnmodifiedSince.toString())
        val localVariableConfig = RequestConfig(
                RequestMethod.PUT,
                "/v1/records/{recordId}".replace("{" + "recordId" + "}", "$recordId"), headers = localVariableHeaders
        )
        val response = apiClient.request<AccessRecord>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as AccessRecord
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
