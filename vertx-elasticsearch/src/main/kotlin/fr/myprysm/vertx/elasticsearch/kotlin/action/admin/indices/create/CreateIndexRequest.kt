package fr.myprysm.vertx.elasticsearch.kotlin.action.admin.indices.create

import fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexRequest

fun CreateIndexRequest(
        headers: Map<String, String>? = null,
        mappings: Map<String, io.vertx.core.json.JsonObject>? = null): CreateIndexRequest = fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexRequest(io.vertx.core.json.JsonObject()).apply {

  if (headers != null) {
      for (item in headers) {
          this.addHeader(item.key, item.value)
      }
  }
  if (mappings != null) {
      for (item in mappings) {
          this.addMapping(item.key, item.value)
      }
  }
}

