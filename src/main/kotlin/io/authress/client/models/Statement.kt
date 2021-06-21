 
package io.authress.client.models

import io.authress.client.models.StatementResources

/**
 * 
 * @param roles 
 * @param resources 
 */
data class Statement (

    val roles: kotlin.Array<kotlin.String>,
    val resources: kotlin.Array<StatementResources>
) {
}