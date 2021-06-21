 
package io.authress.client.models


/**
 * A client configuration.
 * @param keyId The unique id of the client.
 * @param clientId The unique id of the client.
 * @param generationDate 
 * @param accessKey An encoded access key which contains identifying information for client access token creation. For direct use with the Authress SDKs, not meant to be decoded. Must be saved on created, as it will never be returned from the API ever again. Authress only saved the corresponding public key to verify private access keys.
 */
data class ClientAccessKey (

    /* The unique id of the client. */
    val keyId: kotlin.String? = null,
    /* The unique id of the client. */
    val clientId: kotlin.String,
    val generationDate: java.time.LocalDateTime? = null,
    /* An encoded access key which contains identifying information for client access token creation. For direct use with the Authress SDKs, not meant to be decoded. Must be saved on created, as it will never be returned from the API ever again. Authress only saved the corresponding public key to verify private access keys. */
    val accessKey: kotlin.String? = null
) {
}