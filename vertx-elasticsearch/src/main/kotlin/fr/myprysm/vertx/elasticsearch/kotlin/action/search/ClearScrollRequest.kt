package fr.myprysm.vertx.elasticsearch.kotlin.action.search

import fr.myprysm.vertx.elasticsearch.action.search.ClearScrollRequest

fun ClearScrollRequest(
        headers: Map<String, String>? = null,
        scrollIds: Iterable<String>? = null): ClearScrollRequest = fr.myprysm.vertx.elasticsearch.action.search.ClearScrollRequest(io.vertx.core.json.JsonObject()).apply {

  if (headers != null) {
      for (item in headers) {
          this.addHeader(item.key, item.value)
      }
  }
  if (scrollIds != null) {
      for (item in scrollIds) {
          this.addScrollId(item)
      }
  }
}

