package fr.myprysm.vertx.elasticsearch.kotlin.action.search

import fr.myprysm.vertx.elasticsearch.action.search.MultiSearchRequest
import fr.myprysm.vertx.elasticsearch.action.search.SearchRequest

fun MultiSearchRequest(
        headers: Map<String, String>? = null,
        requests: Iterable<fr.myprysm.vertx.elasticsearch.action.search.SearchRequest>? = null): MultiSearchRequest = fr.myprysm.vertx.elasticsearch.action.search.MultiSearchRequest(io.vertx.core.json.JsonObject()).apply {

  if (headers != null) {
      for (item in headers) {
          this.addHeader(item.key, item.value)
      }
  }
  if (requests != null) {
      for (item in requests) {
          this.addRequest(item)
      }
  }
}

