package io.authress.client

public interface ITokenProvider {
    fun resolveToken(): kotlin.String?
}

public class ConstantTokenProvider(val token: kotlin.String?) : ITokenProvider {
    override fun resolveToken(): kotlin.String? {
        return token
    }
}

public class ServiceClientTokenProvider(val token: kotlin.String?) : ITokenProvider {
    override fun resolveToken(): kotlin.String? {
        return token
    }
}