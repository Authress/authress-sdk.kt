 
package io.authress.client.models

import io.authress.client.models.Statement
import io.authress.client.models.User

/**
 * A logical grouping of access related elements
 * @param users The list of users the access applies to
 * @param statements A list of statements which match roles to resources. Users here will have all statements apply to them
 */
data class AccessTemplate (

    /* The list of users the access applies to */
    val users: kotlin.Array<User>,
    /* A list of statements which match roles to resources. Users here will have all statements apply to them */
    val statements: kotlin.Array<Statement>
) {
}