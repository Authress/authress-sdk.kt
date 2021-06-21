 
package io.authress.client.models

import io.authress.client.models.TenantConnection
import io.authress.client.models.TenantData

/**
 * 
 * @param tenantId 
 * @param tenantLookupIdentifier 
 * @param &#x60;data&#x60; 
 * @param connection 
 * @param createdTime 
 */
data class Tenant (

    val tenantId: kotlin.String,
    val tenantLookupIdentifier: kotlin.String? = null,
    val `data`: TenantData? = null,
    val connection: TenantConnection? = null,
    val createdTime: java.time.LocalDateTime? = null
) {
}