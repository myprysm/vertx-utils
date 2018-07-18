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
        headers: Map<String, String>? = null): BaseRequest = fr.myprysm.vertx.elasticsearch.action.BaseRequest(io.vertx.core.json.JsonObject()).apply {

  if (headers != null) {
      for (item in headers) {
          this.addHeader(item.key, item.value)
      }
  }
}

