import io.authress.client.apis.ServiceClientsApi
import io.authress.client.infrastructure.ApiClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class TrailingSlashApiUrlTest {
    @ParameterizedTest
    @ValueSource(strings = ["", "/", "//"])
    fun apiUrlTrailingSlashesDoNotChangeTheRequestUrl(trailingSlashes: String) {
        val http = StubHttpClient()

        runCatching { ServiceClientsApi(ApiClient("https://authress.company.com$trailingSlashes", http.client)).getClients() }

        assertEquals(listOf("https://authress.company.com/v1/clients"), http.requestedUrls)
    }
}
