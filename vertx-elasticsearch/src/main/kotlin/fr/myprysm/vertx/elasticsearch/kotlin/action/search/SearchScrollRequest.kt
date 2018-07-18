package fr.myprysm.vertx.elasticsearch.kotlin.action.search

import fr.myprysm.vertx.elasticsearch.action.search.SearchScrollRequest

fun SearchScrollRequest(
        headers: Map<String, String>? = null): SearchScrollRequest = fr.myprysm.vertx.elasticsearch.action.search.SearchScrollRequest(io.vertx.core.json.JsonObject()).apply {

  if (headers != null) {
      for (item in headers) {
          this.addHeader(item.key, item.value)
      }
  }
}

