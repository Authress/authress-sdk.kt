 
package io.authress.client

import io.authress.client.apis.*
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
}
