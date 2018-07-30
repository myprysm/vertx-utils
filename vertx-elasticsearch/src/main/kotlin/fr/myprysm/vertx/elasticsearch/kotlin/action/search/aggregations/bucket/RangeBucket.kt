package fr.myprysm.vertx.elasticsearch.kotlin.action.search.aggregations.bucket

import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.RangeBucket
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation

fun RangeBucket(
        aggregations: Map<String, fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation>? = null,
        docCount: Long? = null,
        fromAsString: String? = null,
        key: String? = null,
        toAsString: String? = null): RangeBucket = fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.RangeBucket().apply {

    if (aggregations != null) {
        this.setAggregations(aggregations)
    }
    if (docCount != null) {
        this.setDocCount(docCount)
    }
    if (fromAsString != null) {
        this.setFromAsString(fromAsString)
    }
    if (key != null) {
        this.setKey(key)
    }
    if (toAsString != null) {
        this.setToAsString(toAsString)
    }
}

