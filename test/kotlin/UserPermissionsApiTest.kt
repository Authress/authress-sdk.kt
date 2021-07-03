import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.*

import io.authress.client.*
import io.authress.client.infrastructure.*

internal class UserPermissionsApiTest {
    @Test @Disabled
    fun test() {
        val accessToken = "TOKEN"
        val authressClient = AuthressClient("https://authress-test.api-eu-west.authress.io", ServiceClientTokenProvider(accessToken))
        
        try {
            val result = authressClient.userPermissions.authorizeUser("Authress|google-oauth2|109357119042447700062", "resources/resourceId", "READ")
            assertEquals(true, result is Unit)
        } catch (e: ClientException) {
            println("Error Executing test: $e")
            throw e
        }
    }
}