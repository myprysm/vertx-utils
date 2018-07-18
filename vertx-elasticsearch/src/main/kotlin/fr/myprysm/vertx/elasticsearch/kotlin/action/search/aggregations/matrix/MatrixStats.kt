package fr.myprysm.vertx.elasticsearch.kotlin.action.search.aggregations.matrix

import fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixStats

fun MatrixStats(
  data: io.vertx.core.json.JsonObject? = null,
  metaData: io.vertx.core.json.JsonObject? = null,
  name: String? = null,
  type: String? = null): MatrixStats = fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixStats(io.vertx.core.json.JsonObject()).apply {

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

