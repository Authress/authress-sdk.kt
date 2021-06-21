 
package io.authress.client.models


/**
 * 
 * @param collectionResource The parent resource to add a sub-resource to. The resource must have a resource configuration that add the permission CLAIM for this to work.
 * @param resourceId The sub-resource the user is requesting Admin ownership over.
 */
data class ClaimRequest (

    /* The parent resource to add a sub-resource to. The resource must have a resource configuration that add the permission CLAIM for this to work. */
    val collectionResource: kotlin.String,
    /* The sub-resource the user is requesting Admin ownership over. */
    val resourceId: kotlin.String
) {
}