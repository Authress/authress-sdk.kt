 
package io.authress.client.models

import io.authress.client.models.Statement

/**
 * 
 * @param statements A list of statements which match roles to resources. The token will have all statements apply to it.
 * @param expires The ISO8601 datetime when the token will expire. Default is 24 hours from now.
 */
data class TokenRequest (

    /* A list of statements which match roles to resources. The token will have all statements apply to it. */
    val statements: kotlin.Array<Statement>,
    /* The ISO8601 datetime when the token will expire. Default is 24 hours from now. */
    val expires: java.time.LocalDateTime
) {
}