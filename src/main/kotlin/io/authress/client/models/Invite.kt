 
package io.authress.client.models

import io.authress.client.models.AccountLinks
import io.authress.client.models.Statement

/**
 * The user invite used to invite users to your application or to Authress as an admin.
 * @param inviteId The unique identifier for the invite.
 * @param statements A list of statements which match roles to resources. The invited user will all statements apply to them
 * @param links 
 */
data class Invite (

    /* The unique identifier for the invite. */
    val inviteId: kotlin.String,
    /* A list of statements which match roles to resources. The invited user will all statements apply to them */
    val statements: kotlin.Array<Statement>,
    val links: AccountLinks
) {
}