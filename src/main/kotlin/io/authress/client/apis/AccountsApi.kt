 
package io.authress.client.apis

import io.authress.client.models.Account
import io.authress.client.models.AccountCollection
import io.authress.client.models.IdentityCollection
import io.authress.client.models.IdentityRequest

import io.authress.client.infrastructure.*

class AccountsApi(basePath: kotlin.String = "/") : ApiClient(basePath) {

    /**
     * Get account information.
     * Includes the original configuration information.
     * @param accountId The unique identifier for the account 
     * @return Account
     */
    @Suppress("UNCHECKED_CAST")
    fun getAccount(accountId: kotlin.String): Account {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/accounts/{accountId}".replace("{" + "accountId" + "}", "$accountId")
        )
        val response = request<Account>(
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
     * Get all linked identities for this account.
     * Returns a list of identities linked for this account.
     * @return IdentityCollection
     */
    @Suppress("UNCHECKED_CAST")
    fun getAccountIdentities(): IdentityCollection {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/identities"
        )
        val response = request<IdentityCollection>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as IdentityCollection
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Get all accounts user has access to
     * Returns a list of accounts that the user has access to.
     * @param earliestCacheTime Ensure the accounts list is not cached before this time. (optional)
     * @return AccountCollection
     */
    @Suppress("UNCHECKED_CAST")
    fun getAccounts(earliestCacheTime: java.time.LocalDateTime? = null): AccountCollection {
        val localVariableQuery: MultiValueMap = mapOf("earliestCacheTime" to listOf("$earliestCacheTime"))
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/accounts", query = localVariableQuery
        )
        val response = request<AccountCollection>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as AccountCollection
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Link a new account identity.
     * An identity is a JWT subscriber *sub* and issuer *iss*. Only one account my be linked to a particular JWT combination. Allows calling the API with a federated token directly instead of using a client access key.
     * @param body  
     * @return void
     */
    fun linkIdentity(body: IdentityRequest): Unit {
        val localVariableBody: kotlin.Any? = body
        
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v1/identities"
        )
        val response = request<Any?>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
