@file:Suppress("unused")
package io.authress.client.infrastructure

import java.lang.RuntimeException

open class ClientException : RuntimeException {

    /**
     * Constructs an [ClientException] with no detail message.
     */
    constructor() : super()

    /**
     * Constructs an [ClientException] with the specified detail message.

     * @param   message   the detail message.
     */
    constructor(error: ClientError<*>) : super(error.body as? String ?: "Client error")

    companion object {
        private const val serialVersionUID: Long = 123L
    }
}

open class ServerException : RuntimeException {

    /**
     * Constructs an [ServerException] with no detail message.
     */
    constructor() : super()

    /**
     * Constructs an [ServerException] with the specified detail message.

     * @param   message   the detail message.
     */
    constructor(error: ServerError<*>) : super(error.message ?: "Server error")

    companion object {
        private const val serialVersionUID: Long = 456L
    }
}