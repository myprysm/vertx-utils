package fr.myprysm.vertx.elasticsearch.kotlin.action.index

import fr.myprysm.vertx.elasticsearch.action.index.IndexResponse
import fr.myprysm.vertx.elasticsearch.action.support.ShardId
import fr.myprysm.vertx.elasticsearch.action.support.ShardInfo
import org.elasticsearch.action.DocWriteResponse.Result
import org.elasticsearch.rest.RestStatus

fun IndexResponse(
        forcedRefresh: Boolean? = null,
        id: String? = null,
        index: String? = null,
        primaryTerm: Long? = null,
        result: Result? = null,
        seqNo: Long? = null,
        shardId: fr.myprysm.vertx.elasticsearch.action.support.ShardId? = null,
        shardInfo: fr.myprysm.vertx.elasticsearch.action.support.ShardInfo? = null,
        status: RestStatus? = null,
        type: String? = null,
        version: Long? = null): IndexResponse = fr.myprysm.vertx.elasticsearch.action.index.IndexResponse().apply {

    if (forcedRefresh != null) {
        this.setForcedRefresh(forcedRefresh)
    }
    if (id != null) {
        this.setId(id)
    }
    if (index != null) {
        this.setIndex(index)
    }
    if (primaryTerm != null) {
        this.setPrimaryTerm(primaryTerm)
    }
    if (result != null) {
        this.setResult(result)
    }
    if (seqNo != null) {
        this.setSeqNo(seqNo)
    }
    if (shardId != null) {
        this.setShardId(shardId)
    }
    if (shardInfo != null) {
        this.setShardInfo(shardInfo)
    }
    if (status != null) {
        this.setStatus(status)
    }
    if (type != null) {
        this.setType(type)
    }
    if (version != null) {
        this.setVersion(version)
    }
}

