package fr.myprysm.vertx.elasticsearch.kotlin.action.admin.refresh

import fr.myprysm.vertx.elasticsearch.action.admin.refresh.RefreshRequest

fun RefreshRequest(
        headers: Map<String, String>? = null,
        indexs: Iterable<String>? = null): RefreshRequest = fr.myprysm.vertx.elasticsearch.action.admin.refresh.RefreshRequest(io.vertx.core.json.JsonObject()).apply {

  if (headers != null) {
      for (item in headers) {
          this.addHeader(item.key, item.value)
      }
  }
  if (indexs != null) {
    for (item in indexs) {
      this.addIndex(item)
    }
  }
}

