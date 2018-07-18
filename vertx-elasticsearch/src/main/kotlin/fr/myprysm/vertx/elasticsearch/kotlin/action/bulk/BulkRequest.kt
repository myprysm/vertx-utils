package fr.myprysm.vertx.elasticsearch.kotlin.action.bulk

import fr.myprysm.vertx.elasticsearch.action.bulk.BulkRequest
import fr.myprysm.vertx.elasticsearch.action.support.DocWriteRequest

fun BulkRequest(
        headers: Map<String, String>? = null): BulkRequest = fr.myprysm.vertx.elasticsearch.action.bulk.BulkRequest(io.vertx.core.json.JsonObject()).apply {

  if (headers != null) {
      for (item in headers) {
          this.addHeader(item.key, item.value)
      }
  }
}

