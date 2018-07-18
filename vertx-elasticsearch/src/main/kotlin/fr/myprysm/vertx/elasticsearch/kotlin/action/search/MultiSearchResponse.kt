package fr.myprysm.vertx.elasticsearch.kotlin.action.search

import fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponse
import fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponseItem

fun MultiSearchResponse(
  responses: Iterable<fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponseItem>? = null): MultiSearchResponse = fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponse().apply {

  if (responses != null) {
    this.setResponses(responses.toList())
  }
}

