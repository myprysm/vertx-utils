package fr.myprysm.vertx.elasticsearch.kotlin.action.admin.indices.delete

import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexRequest

/**
 * A function providing a DSL for building [fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexRequest] objects.
 *
 * DeleteIndexRequest.
 *
 * @param headers  Add a header to the request.
 *
 * <p/>
 * NOTE: This function has been automatically generated from the [fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexRequest original] using Vert.x codegen.
 */
fun DeleteIndexRequest(
        headers: Map<String, String>? = null): DeleteIndexRequest = fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexRequest(io.vertx.core.json.JsonObject()).apply {

  if (headers != null) {
      for (item in headers) {
          this.addHeader(item.key, item.value)
      }
  }
}

