package fr.myprysm.vertx.elasticsearch.kotlin.action.search

import fr.myprysm.vertx.elasticsearch.action.search.ClearScrollRequest

fun ClearScrollRequest(
        headers: Map<String, String>? = null,
        scrollIds: Iterable<String>? = null): ClearScrollRequest = fr.myprysm.vertx.elasticsearch.action.search.ClearScrollRequest().apply {

  if (headers != null) {
      this.setHeaders(headers)
  }
  if (scrollIds != null) {
      this.setScrollIds(scrollIds.toList())
  }
}

