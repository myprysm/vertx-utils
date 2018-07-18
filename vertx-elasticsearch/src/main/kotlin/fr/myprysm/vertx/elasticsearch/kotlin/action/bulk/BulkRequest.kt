package fr.myprysm.vertx.elasticsearch.kotlin.action.bulk

import fr.myprysm.vertx.elasticsearch.action.bulk.BulkRequest
import fr.myprysm.vertx.elasticsearch.action.support.DocWriteRequest
import org.elasticsearch.action.support.WriteRequest.RefreshPolicy

fun BulkRequest(
  headers: Map<String, String>? = null,
  refreshPolicy: RefreshPolicy? = null,
  requests: Iterable<fr.myprysm.vertx.elasticsearch.action.support.DocWriteRequest>? = null,
  timeout: Long? = null,
  waitForActiveShards: Int? = null): BulkRequest = fr.myprysm.vertx.elasticsearch.action.bulk.BulkRequest().apply {

  if (headers != null) {
    this.setHeaders(headers)
  }
  if (refreshPolicy != null) {
    this.setRefreshPolicy(refreshPolicy)
  }
  if (requests != null) {
    this.setRequests(requests.toList())
  }
  if (timeout != null) {
    this.setTimeout(timeout)
  }
  if (waitForActiveShards != null) {
    this.setWaitForActiveShards(waitForActiveShards)
  }
}

