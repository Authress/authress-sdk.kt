 
package io.authress.client.models


/**
 * 
 * @param resourceUri A resource path which can be top level, fully qualified, or end with a *. Parent resources imply permissions to sub-resources.
 */
data class StatementResources (

    /* A resource path which can be top level, fully qualified, or end with a *. Parent resources imply permissions to sub-resources. */
    val resourceUri: kotlin.String
) {
}