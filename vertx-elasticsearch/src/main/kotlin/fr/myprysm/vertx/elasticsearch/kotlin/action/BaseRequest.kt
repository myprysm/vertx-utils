package fr.myprysm.vertx.elasticsearch.kotlin.action

import fr.myprysm.vertx.elasticsearch.action.BaseRequest

/**
 * A function providing a DSL for building [fr.myprysm.vertx.elasticsearch.action.BaseRequest] objects.
 *
 * Base DataObject request.
 *
 * @param headers  Add a header to the request.
 *
 * <p/>
 * NOTE: This function has been automatically generated from the [fr.myprysm.vertx.elasticsearch.action.BaseRequest original] using Vert.x codegen.
 */
fun BaseRequest(
        headers: Map<String, String>? = null): BaseRequest = fr.myprysm.vertx.elasticsearch.action.BaseRequest().apply {

    if (headers != null) {
        this.setHeaders(headers)
    }
}

