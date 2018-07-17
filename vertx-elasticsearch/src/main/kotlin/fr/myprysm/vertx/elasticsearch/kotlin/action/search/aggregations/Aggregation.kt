package fr.myprysm.vertx.elasticsearch.kotlin.action.search.aggregations

import fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation

fun Aggregation(
        data: io.vertx.core.json.JsonObject? = null,
        metaData: io.vertx.core.json.JsonObject? = null,
        name: String? = null,
        type: String? = null): Aggregation = fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation().apply {

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

