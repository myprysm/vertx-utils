package fr.myprysm.vertx.elasticsearch.kotlin.action.get

import fr.myprysm.vertx.elasticsearch.action.get.MultiGetRequest
import fr.myprysm.vertx.elasticsearch.action.get.GetRequestItem

fun MultiGetRequest(
        headers: Map<String, String>? = null,
        items: Iterable<fr.myprysm.vertx.elasticsearch.action.get.GetRequestItem>? = null): MultiGetRequest = fr.myprysm.vertx.elasticsearch.action.get.MultiGetRequest(io.vertx.core.json.JsonObject()).apply {

  if (headers != null) {
      for (item in headers) {
          this.addHeader(item.key, item.value)
      }
  }
  if (items != null) {
      for (item in items) {
          this.addItem(item)
      }
  }
}

