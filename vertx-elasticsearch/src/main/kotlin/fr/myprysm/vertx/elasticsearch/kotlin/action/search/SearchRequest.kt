package fr.myprysm.vertx.elasticsearch.kotlin.action.search

import fr.myprysm.vertx.elasticsearch.action.search.SearchRequest

/**
 * A function providing a DSL for building [fr.myprysm.vertx.elasticsearch.action.search.SearchRequest] objects.
 *
 * SearchRequest
 *
 * @param headers  Add a header to the request.
 *
 * <p/>
 * NOTE: This function has been automatically generated from the [fr.myprysm.vertx.elasticsearch.action.search.SearchRequest original] using Vert.x codegen.
 */
fun SearchRequest(
        headers: Map<String, String>? = null): SearchRequest = fr.myprysm.vertx.elasticsearch.action.search.SearchRequest(io.vertx.core.json.JsonObject()).apply {

  if (headers != null) {
      for (item in headers) {
          this.addHeader(item.key, item.value)
      }
  }
}

