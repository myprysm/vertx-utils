package fr.myprysm.vertx.elasticsearch.kotlin.action.search.aggregations.bucket

import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.SingleBucketAggregation
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation

fun SingleBucketAggregation(
        aggregations: Map<String, fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation>? = null,
        data: io.vertx.core.json.JsonObject? = null,
        docCount: Long? = null,
        metaData: io.vertx.core.json.JsonObject? = null,
        name: String? = null,
        type: String? = null): SingleBucketAggregation = fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.SingleBucketAggregation().apply {

    if (aggregations != null) {
        this.setAggregations(aggregations)
    }
    if (data != null) {
        this.setData(data)
    }
    if (docCount != null) {
        this.setDocCount(docCount)
    }
    if (metaData != null) {
        this.setMetaData(metaData)
    }
    if (name != null) {
        this.setName(name)
    }
    if (type != null) {
        this.setType(type)
    }
}
