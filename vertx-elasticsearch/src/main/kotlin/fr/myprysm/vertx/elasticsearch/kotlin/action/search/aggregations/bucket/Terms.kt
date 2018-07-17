package fr.myprysm.vertx.elasticsearch.kotlin.action.search.aggregations.bucket

import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Terms
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.TermsBucket

fun Terms(
        buckets: Map<String, fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.TermsBucket>? = null,
        data: io.vertx.core.json.JsonObject? = null,
        docCountError: Long? = null,
        metaData: io.vertx.core.json.JsonObject? = null,
        name: String? = null,
        sumOfOtherDocCounts: Long? = null,
        type: String? = null): Terms = fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Terms().apply {

    if (buckets != null) {
        this.setBuckets(buckets)
    }
    if (data != null) {
        this.setData(data)
    }
    if (docCountError != null) {
        this.setDocCountError(docCountError)
    }
    if (metaData != null) {
        this.setMetaData(metaData)
    }
    if (name != null) {
        this.setName(name)
    }
    if (sumOfOtherDocCounts != null) {
        this.setSumOfOtherDocCounts(sumOfOtherDocCounts)
    }
    if (type != null) {
        this.setType(type)
    }
}

