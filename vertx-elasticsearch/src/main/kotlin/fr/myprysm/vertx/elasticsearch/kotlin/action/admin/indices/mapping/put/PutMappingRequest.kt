package fr.myprysm.vertx.elasticsearch.kotlin.action.admin.indices.mapping.put

import fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest

fun PutMappingRequest(
        headers: Map<String, String>? = null): PutMappingRequest = fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest(io.vertx.core.json.JsonObject()).apply {

  if (headers != null) {
      for (item in headers) {
          this.addHeader(item.key, item.value)
      }
  }
}

