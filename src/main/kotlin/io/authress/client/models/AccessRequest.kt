 
package io.authress.client.models

import io.authress.client.models.AccessTemplate
import io.authress.client.models.AccountLinks

/**
 * The access requested by a user.
 * @param requestId Unique identifier for the request.
 * @param lastUpdated The expected last time the record was updated
 * @param status Current status of the access record.
 * @param access 
 * @param links 
 */
data class AccessRequest (

    /* Unique identifier for the request. */
    val requestId: kotlin.String,
    /* The expected last time the record was updated */
    val lastUpdated: java.time.LocalDateTime? = null,
    /* Current status of the access record. */
    val status: AccessRequest.Status? = null,
    val access: AccessTemplate,
    val links: AccountLinks
) {
    /**
    * Current status of the access record.
    * Values: OPEN,APPROVED,DENIED,DELETED
    */
    enum class Status(val value: kotlin.String){
        OPEN("OPEN"),
        APPROVED("APPROVED"),
        DENIED("DENIED"),
        DELETED("DELETED");
    }
}