package fr.myprysm.vertx.elasticsearch.kotlin.action.search

import fr.myprysm.vertx.elasticsearch.action.search.ClearScrollResponse
import org.elasticsearch.rest.RestStatus

fun ClearScrollResponse(
  numFreed: Int? = null,
  status: RestStatus? = null,
  succeeded: Boolean? = null): ClearScrollResponse = fr.myprysm.vertx.elasticsearch.action.search.ClearScrollResponse().apply {

  if (numFreed != null) {
    this.setNumFreed(numFreed)
  }
  if (status != null) {
    this.setStatus(status)
  }
  if (succeeded != null) {
    this.setSucceeded(succeeded)
  }
}

