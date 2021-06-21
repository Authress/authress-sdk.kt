 
package io.authress.client.models

import io.authress.client.models.Tenant

/**
 * A collection of tenants.
 * @param connections 
 */
data class TenantCollection (

    val connections: kotlin.Array<Tenant>? = null
) {
}