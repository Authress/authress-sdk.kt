 
package io.authress.client.models


/**
 * A url linking object that complies to application/links+json RFC. Either is an IANA approved link relation or has a custom rel specified.
 * @param href The absolute url pointing to the reference resource.
 * @param rel Optional property indicating the type of link if it is not a default IANA approved global link relation.
 */
data class Link (

    /* The absolute url pointing to the reference resource. */
    val href: kotlin.String,
    /* Optional property indicating the type of link if it is not a default IANA approved global link relation. */
    val rel: kotlin.String? = null
) {
}