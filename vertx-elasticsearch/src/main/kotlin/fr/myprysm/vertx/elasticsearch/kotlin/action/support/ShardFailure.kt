package fr.myprysm.vertx.elasticsearch.kotlin.action.support

import fr.myprysm.vertx.elasticsearch.action.support.ShardFailure
import org.elasticsearch.rest.RestStatus

fun ShardFailure(
  cause: String? = null,
  index: String? = null,
  shardId: Int? = null,
  status: RestStatus? = null): ShardFailure = fr.myprysm.vertx.elasticsearch.action.support.ShardFailure().apply {

  if (cause != null) {
    this.setCause(cause)
  }
  if (index != null) {
    this.setIndex(index)
  }
  if (shardId != null) {
    this.setShardId(shardId)
  }
  if (status != null) {
    this.setStatus(status)
  }
}

