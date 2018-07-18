package fr.myprysm.vertx.elasticsearch.kotlin.action.admin.indices.get

import fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest

fun GetIndexRequest(
        headers: Map<String, String>? = null): GetIndexRequest = fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest(io.vertx.core.json.JsonObject()).apply {

  if (headers != null) {
      for (item in headers) {
          this.addHeader(item.key, item.value)
      }
  }
}

