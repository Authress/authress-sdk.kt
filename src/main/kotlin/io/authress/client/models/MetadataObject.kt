 
package io.authress.client.models

/**
 * Metadata and additional properties relevant.
 * @param userId 
 * @param metadata A JSON object limited to 10KB. The owner identified by the sub will always have access to read and update this data. Service clients may have access if the related property on the client is set. Access is restricted to authorized users.
 */
data class MetadataObject (

    val userId: kotlin.String,
    /* A JSON object limited to 10KB. The owner identified by the sub will always have access to read and update this data. Service clients may have access if the related property on the client is set. Access is restricted to authorized users. */
    val metadata: kotlin.Any
) {
}