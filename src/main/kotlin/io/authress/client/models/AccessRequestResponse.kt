 
package io.authress.client.models


/**
 * A dynamic body to support request PATCH operations
 * @param status New result, either approve or deny the request
 */
data class AccessRequestResponse (

    /* New result, either approve or deny the request */
    val status: AccessRequestResponse.Status
) {
    /**
    * New result, either approve or deny the request
    * Values: APPROVED,DENIED
    */
    enum class Status(val value: kotlin.String){
        APPROVED("APPROVED"),
        DENIED("DENIED");
    }
}