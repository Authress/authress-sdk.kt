 
package io.authress.client.models

import io.authress.client.models.AccessRecord
import io.authress.client.models.ResourcePermissionCollectionLinks

/**
 * A collection of access records
 * @param records A list of access records
 * @param links 
 */
data class AccessRecordCollection (

    /* A list of access records */
    val records: kotlin.Array<AccessRecord>,
    val links: ResourcePermissionCollectionLinks
) {
}