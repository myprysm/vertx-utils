package fr.myprysm.vertx.elasticsearch.kotlin.action.support

import fr.myprysm.vertx.elasticsearch.action.support.ReplicationResponse
import fr.myprysm.vertx.elasticsearch.action.support.ShardInfo

fun ReplicationResponse(
  shardInfo: fr.myprysm.vertx.elasticsearch.action.support.ShardInfo? = null): ReplicationResponse = fr.myprysm.vertx.elasticsearch.action.support.ReplicationResponse().apply {

  if (shardInfo != null) {
    this.setShardInfo(shardInfo)
  }
}

