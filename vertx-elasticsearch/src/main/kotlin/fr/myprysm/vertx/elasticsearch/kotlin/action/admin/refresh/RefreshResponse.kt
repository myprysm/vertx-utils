package fr.myprysm.vertx.elasticsearch.kotlin.action.admin.refresh

import fr.myprysm.vertx.elasticsearch.action.admin.refresh.RefreshResponse
import fr.myprysm.vertx.elasticsearch.action.support.ShardFailure
import org.elasticsearch.rest.RestStatus

fun RefreshResponse(
  failedShards: Int? = null,
  shardFailures: Iterable<fr.myprysm.vertx.elasticsearch.action.support.ShardFailure>? = null,
  status: RestStatus? = null,
  successfulShards: Int? = null,
  totalShards: Int? = null): RefreshResponse = fr.myprysm.vertx.elasticsearch.action.admin.refresh.RefreshResponse().apply {

  if (failedShards != null) {
    this.setFailedShards(failedShards)
  }
  if (shardFailures != null) {
    this.setShardFailures(shardFailures.toList())
  }
  if (status != null) {
    this.setStatus(status)
  }
  if (successfulShards != null) {
    this.setSuccessfulShards(successfulShards)
  }
  if (totalShards != null) {
    this.setTotalShards(totalShards)
  }
}

