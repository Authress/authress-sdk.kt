 
package io.authress.client.models

import io.authress.client.models.AccountLinks
import io.authress.client.models.Statement
import io.authress.client.models.User

/**
 * The access record which links users to roles.
 * @param recordId Unique identifier for the record, can be specified on record creation.
 * @param name A helpful name for this record
 * @param description More details about this record
 * @param capacity Percentage capacity of record that is filled.
 * @param lastUpdated The expected last time the record was updated
 * @param status Current status of the access record.
 * @param users The list of users this record applies to
 * @param admins The list of admin that can edit this record even if they do not have global record edit permissions.
 * @param statements A list of statements which match roles to resources. Users in this record have all statements apply to them
 * @param links 
 */
data class AccessRecord (

    /* Unique identifier for the record, can be specified on record creation. */
    val recordId: kotlin.String? = null,
    /* A helpful name for this record */
    val name: kotlin.String,
    /* More details about this record */
    val description: kotlin.String? = null,
    /* Percentage capacity of record that is filled. */
    val capacity: kotlin.Float? = null,
    /* The expected last time the record was updated */
    val lastUpdated: java.time.LocalDateTime? = null,
    /* Current status of the access record. */
    val status: AccessRecord.Status? = null,
    /* The list of users this record applies to */
    val users: kotlin.Array<User>,
    /* The list of admin that can edit this record even if they do not have global record edit permissions. */
    val admins: kotlin.Array<User>? = null,
    /* A list of statements which match roles to resources. Users in this record have all statements apply to them */
    val statements: kotlin.Array<Statement>,
    val links: AccountLinks
) {
    /**
    * Current status of the access record.
    * Values: ACTIVE,DELETED
    */
    enum class Status(val value: kotlin.String){
        ACTIVE("ACTIVE"),
        DELETED("DELETED");
    }
}