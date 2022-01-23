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

    @Test @Disabled
    fun validator() {
        val accessToken = "eyJhbGciOiJFZERTQSIsImtpZCI6ImdwQ3dlZmdkaXFDZUhTbmN0Z05kbWsiLCJ0eXAiOiJhdCtqd3QifQ.eyJpc3MiOiJodHRwczovL2E0OGNvcGpyZjVxcmpuMW5pYWtmemZxbHAubG9naW4uYXV0aHJlc3MuaW8iLCJzdWIiOiJ1aWFxUWpjZmNNQlNTaXl3cTRwNDlnNFlRIiwiaWF0IjoxNjQyOTYyOTc1LCJleHAiOjE2NDMwNDkzNzUsInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwiLCJhenAiOiJnb29nbGUiLCJjbGllbnRfaWQiOiJBVVRIUkVTU19DT05ORUNUSU9OX1RFU1QifQ.UL7jGgeBT5iWtZ0EdS0MBHgukg2VxLsa3v6Fw9hwKCF2Vq6H7TlEDkZ1bFOHAR0gEJX8ZLLPWZYph6WknbtoCQ"
        val tokenVerifier = TokenVerifier("https://a48copjrf5qrjn1niakfzfqlp.login.authress.io")
        
        try {
            tokenVerifier.verify(accessToken)
        } catch (e: ClientException) {
            println("Invalid Token: $e")
            throw e
        }
    }
}