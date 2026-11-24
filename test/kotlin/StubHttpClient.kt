import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

internal class StubHttpClient(private val statusCode: Int = 200, private val respond: (Request) -> String = { "{}" }) {
    val requestedUrls = mutableListOf<String>()

    val client: OkHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val request = chain.request()
        requestedUrls.add(request.url.toString())
        Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .code(statusCode)
            .message("stub")
            .body(respond(request).toResponseBody("application/json".toMediaType()))
            .build()
    }.build()
}
