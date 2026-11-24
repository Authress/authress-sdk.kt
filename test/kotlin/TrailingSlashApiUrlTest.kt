import com.sun.net.httpserver.HttpServer
import io.authress.client.AuthressClient
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.net.InetSocketAddress

internal class TrailingSlashApiUrlTest {
    private lateinit var server: HttpServer
    private val requestedPaths = mutableListOf<String>()

    @BeforeEach
    fun startServer() {
        server = HttpServer.create(InetSocketAddress("127.0.0.1", 0), 0)
        server.createContext("/") { exchange ->
            requestedPaths.add(exchange.requestURI.rawPath)
            val body = "{}".toByteArray()
            exchange.responseHeaders.add("Content-Type", "application/json")
            exchange.sendResponseHeaders(200, body.size.toLong())
            exchange.responseBody.use { it.write(body) }
        }
        server.start()
    }

    @AfterEach
    fun stopServer() {
        server.stop(0)
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "/", "//"])
    fun apiUrlTrailingSlashesDoNotChangeTheRequestPath(trailingSlashes: String) {
        val client = AuthressClient("http://127.0.0.1:${server.address.port}$trailingSlashes")

        runCatching { client.serviceClients.getClients() }

        assertEquals(listOf("/v1/clients"), requestedPaths)
    }
}
