package fr.myprysm.vertx.elasticsearch.kotlin.action.search.aggregations.matrix

import fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixStats
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixStatsResult

fun MatrixStats(
        data: io.vertx.core.json.JsonObject? = null,
        docCount: Long? = null,
        metaData: io.vertx.core.json.JsonObject? = null,
        name: String? = null,
        results: Map<String, fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixStatsResult>? = null,
        type: String? = null): MatrixStats = fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixStats().apply {

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
    if (results != null) {
        this.setResults(results)
    }
    if (type != null) {
        this.setType(type)
    }
}

