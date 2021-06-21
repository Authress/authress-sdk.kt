 
package io.authress.client.models


/**
 * 
 * @param tenantId 
 * @param name 
 * @param supportedContentType 
 */
data class ConnectionData (

    val tenantId: kotlin.String? = null,
    val name: kotlin.String? = null,
    val supportedContentType: ConnectionData.SupportedContentType? = null
) {
    /**
    * 
    * Values: JSON,XWWWFORMURLENCODED
    */
    enum class SupportedContentType(val value: kotlin.String){
        JSON("application/json"),
        XWWWFORMURLENCODED("application/x-www-form-urlencoded");
    }
}