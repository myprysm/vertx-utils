package fr.myprysm.vertx.elasticsearch.kotlin.action.support

import fr.myprysm.vertx.elasticsearch.action.support.DocWriteResponse
import fr.myprysm.vertx.elasticsearch.action.support.ShardId
import fr.myprysm.vertx.elasticsearch.action.support.ShardInfo
import org.elasticsearch.action.DocWriteResponse.Result

fun DocWriteResponse(
        forcedRefresh: Boolean? = null,
        id: String? = null,
        index: String? = null,
        primaryTerm: Long? = null,
        result: Result? = null,
        seqNo: Long? = null,
        shardId: fr.myprysm.vertx.elasticsearch.action.support.ShardId? = null,
        shardInfo: fr.myprysm.vertx.elasticsearch.action.support.ShardInfo? = null,
        type: String? = null,
        version: Long? = null): DocWriteResponse = fr.myprysm.vertx.elasticsearch.action.support.DocWriteResponse().apply {

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
    if (type != null) {
        this.setType(type)
    }
    if (version != null) {
        this.setVersion(version)
    }
}
