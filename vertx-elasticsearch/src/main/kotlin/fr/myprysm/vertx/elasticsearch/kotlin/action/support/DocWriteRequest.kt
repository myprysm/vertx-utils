package fr.myprysm.vertx.elasticsearch.kotlin.action.support

import fr.myprysm.vertx.elasticsearch.action.support.DocWriteRequest

fun DocWriteRequest(
        headers: Map<String, String>? = null): DocWriteRequest = fr.myprysm.vertx.elasticsearch.action.support.DocWriteRequest(io.vertx.core.json.JsonObject()).apply {

  if (headers != null) {
      for (item in headers) {
          this.addHeader(item.key, item.value)
      }
  }
}

