package fr.myprysm.vertx.elasticsearch.kotlin.action.search.aggregations.bucket

import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Bucket
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation

fun Bucket(
        aggregations: Map<String, fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation>? = null,
        docCount: Long? = null,
        key: String? = null): Bucket = fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Bucket().apply {

    if (aggregations != null) {
        this.setAggregations(aggregations)
    }
    if (docCount != null) {
        this.setDocCount(docCount)
    }
    if (key != null) {
        this.setKey(key)
    }
}

