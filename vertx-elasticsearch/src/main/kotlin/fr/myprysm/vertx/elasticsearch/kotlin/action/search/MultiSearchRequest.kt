package fr.myprysm.vertx.elasticsearch.kotlin.action.search

import fr.myprysm.vertx.elasticsearch.action.search.MultiSearchRequest
import fr.myprysm.vertx.elasticsearch.action.search.SearchRequest

fun MultiSearchRequest(
  headers: Map<String, String>? = null,
  maxConcurrentSearchRequests: Int? = null,
  requests: Iterable<fr.myprysm.vertx.elasticsearch.action.search.SearchRequest>? = null): MultiSearchRequest = fr.myprysm.vertx.elasticsearch.action.search.MultiSearchRequest().apply {

  if (headers != null) {
    this.setHeaders(headers)
  }
  if (maxConcurrentSearchRequests != null) {
    this.setMaxConcurrentSearchRequests(maxConcurrentSearchRequests)
  }
  if (requests != null) {
    this.setRequests(requests.toList())
  }
}

