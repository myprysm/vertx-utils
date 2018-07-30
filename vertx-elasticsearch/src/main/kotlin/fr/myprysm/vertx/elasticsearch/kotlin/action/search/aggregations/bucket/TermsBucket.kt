package fr.myprysm.vertx.elasticsearch.kotlin.action.search.aggregations.bucket

import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.TermsBucket
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation

fun TermsBucket(
        aggregations: Map<String, fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation>? = null,
        docCount: Long? = null,
        docCountError: Long? = null,
        key: String? = null): TermsBucket = fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.TermsBucket().apply {

    if (aggregations != null) {
        this.setAggregations(aggregations)
    }
    if (docCount != null) {
        this.setDocCount(docCount)
    }
    if (docCountError != null) {
        this.setDocCountError(docCountError)
    }
    if (key != null) {
        this.setKey(key)
    }
}

