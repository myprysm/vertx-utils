package fr.myprysm.vertx.elasticsearch.kotlin.action.search

import fr.myprysm.vertx.elasticsearch.action.search.ShardSearchFailure
import fr.myprysm.vertx.elasticsearch.action.support.ShardId
import org.elasticsearch.rest.RestStatus

fun ShardSearchFailure(
        cause: String? = null,
        nodeId: String? = null,
        shardId: fr.myprysm.vertx.elasticsearch.action.support.ShardId? = null,
        status: RestStatus? = null): ShardSearchFailure = fr.myprysm.vertx.elasticsearch.action.search.ShardSearchFailure().apply {

    if (cause != null) {
        this.setCause(cause)
    }
    if (nodeId != null) {
        this.setNodeId(nodeId)
    }
    if (shardId != null) {
        this.setShardId(shardId)
    }
    if (status != null) {
        this.setStatus(status)
    }
}

