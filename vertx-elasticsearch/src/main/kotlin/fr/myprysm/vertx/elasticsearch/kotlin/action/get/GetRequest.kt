package fr.myprysm.vertx.elasticsearch.kotlin.action.get

import fr.myprysm.vertx.elasticsearch.action.get.GetRequest

fun GetRequest(
        headers: Map<String, String>? = null): GetRequest = fr.myprysm.vertx.elasticsearch.action.get.GetRequest(io.vertx.core.json.JsonObject()).apply {

  if (headers != null) {
      for (item in headers) {
          this.addHeader(item.key, item.value)
      }
  }
}

