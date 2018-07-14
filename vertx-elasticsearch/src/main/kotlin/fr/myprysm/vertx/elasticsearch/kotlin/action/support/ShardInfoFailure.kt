package fr.myprysm.vertx.elasticsearch.kotlin.action.support

import fr.myprysm.vertx.elasticsearch.action.support.ShardInfoFailure
import org.elasticsearch.rest.RestStatus

fun ShardInfoFailure(
        cause: String? = null,
        nodeId: String? = null,
        primary: Boolean? = null,
        shardId: String? = null,
        status: RestStatus? = null): ShardInfoFailure = fr.myprysm.vertx.elasticsearch.action.support.ShardInfoFailure().apply {

    if (cause != null) {
        this.setCause(cause)
    }
    if (nodeId != null) {
        this.setNodeId(nodeId)
    }
    if (primary != null) {
        this.setPrimary(primary)
    }
    if (shardId != null) {
        this.setShardId(shardId)
    }
    if (status != null) {
        this.setStatus(status)
    }
}

