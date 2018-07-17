package fr.myprysm.vertx.elasticsearch.kotlin.action.admin.refresh

import fr.myprysm.vertx.elasticsearch.action.admin.refresh.RefreshRequest

fun RefreshRequest(
        headers: Map<String, String>? = null,
        indexs: Iterable<String>? = null,
        indices: Iterable<String>? = null): RefreshRequest = fr.myprysm.vertx.elasticsearch.action.admin.refresh.RefreshRequest().apply {

    if (headers != null) {
        this.setHeaders(headers)
    }
    if (indexs != null) {
        for (item in indexs) {
            this.addIndex(item)
        }
    }
    if (indices != null) {
        this.setIndices(indices.toList())
    }
}

