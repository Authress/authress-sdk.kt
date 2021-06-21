 
package io.authress.client.models

import io.authress.client.models.Link

/**
 * 
 * @param self 
 * @param next 
 */
data class ResourcePermissionCollectionLinks (

    val self: Link,
    val next: Link? = null
) {
}