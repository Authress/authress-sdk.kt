 
package io.authress.client.models

import io.authress.client.models.Identity

/**
 * 
 * @param identities 
 */
data class IdentityCollection (

    val identities: kotlin.Array<Identity>
) {
}