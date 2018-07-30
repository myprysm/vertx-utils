package fr.myprysm.vertx.elasticsearch.kotlin.action.search.aggregations.bucket

import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.GeoHashGrid
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Bucket

fun GeoHashGrid(
        buckets: Map<String, fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Bucket>? = null,
        data: io.vertx.core.json.JsonObject? = null,
        metaData: io.vertx.core.json.JsonObject? = null,
        name: String? = null,
        type: String? = null): GeoHashGrid = fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.GeoHashGrid().apply {

    if (buckets != null) {
        this.setBuckets(buckets)
    }
    if (data != null) {
        this.setData(data)
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

