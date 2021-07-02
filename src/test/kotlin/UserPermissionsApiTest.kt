package io.authress.tests

import kotlin.test.*

import io.authress.client.apis.*
import io.authress.client.infrastructure.*

internal class UserPermissionsApiTest {
    @Test
    fun test() {
        val accessToken = "lalala"
        val authressClient = AuthressClient("https://ACCOUNT_ID.api-REGION.authress.io", ServiceClientTokenProvider(accessToken))
        val result = authressClient.userPermissions.authorizeUser("UserID", "resources/resourceId", "READ")
    }
}