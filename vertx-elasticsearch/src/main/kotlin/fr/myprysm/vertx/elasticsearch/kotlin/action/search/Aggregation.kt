package fr.myprysm.vertx.elasticsearch.kotlin.action.search

import fr.myprysm.vertx.elasticsearch.action.search.Aggregation

fun Aggregation(
        metaData: io.vertx.core.json.JsonObject? = null,
        name: String? = null,
        type: String? = null): Aggregation = fr.myprysm.vertx.elasticsearch.action.search.Aggregation().apply {

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

