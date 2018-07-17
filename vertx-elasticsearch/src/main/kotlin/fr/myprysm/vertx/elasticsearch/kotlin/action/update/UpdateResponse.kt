package fr.myprysm.vertx.elasticsearch.kotlin.action.update

import fr.myprysm.vertx.elasticsearch.action.update.UpdateResponse
import fr.myprysm.vertx.elasticsearch.action.get.GetResponse
import fr.myprysm.vertx.elasticsearch.action.support.ShardId
import fr.myprysm.vertx.elasticsearch.action.support.ShardInfo
import org.elasticsearch.action.DocWriteResponse.Result
import org.elasticsearch.rest.RestStatus

/**
 * A function providing a DSL for building [fr.myprysm.vertx.elasticsearch.action.update.UpdateResponse] objects.
 *
 * Update response from Elasticsearch.
 *
 * @param forcedRefresh
 * @param getResult
 * @param id
 * @param index
 * @param primaryTerm
 * @param result
 * @param seqNo
 * @param shardId
 * @param shardInfo
 * @param status
 * @param type
 * @param version 
 *
 * <p/>
 * NOTE: This function has been automatically generated from the [fr.myprysm.vertx.elasticsearch.action.update.UpdateResponse original] using Vert.x codegen.
 */
fun UpdateResponse(
        forcedRefresh: Boolean? = null,
        getResult: fr.myprysm.vertx.elasticsearch.action.get.GetResponse? = null,
        id: String? = null,
        index: String? = null,
        primaryTerm: Long? = null,
        result: Result? = null,
        seqNo: Long? = null,
        shardId: fr.myprysm.vertx.elasticsearch.action.support.ShardId? = null,
        shardInfo: fr.myprysm.vertx.elasticsearch.action.support.ShardInfo? = null,
        status: RestStatus? = null,
        type: String? = null,
        version: Long? = null): UpdateResponse = fr.myprysm.vertx.elasticsearch.action.update.UpdateResponse().apply {

    if (forcedRefresh != null) {
        this.setForcedRefresh(forcedRefresh)
    }
    if (getResult != null) {
        this.setGetResult(getResult)
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

