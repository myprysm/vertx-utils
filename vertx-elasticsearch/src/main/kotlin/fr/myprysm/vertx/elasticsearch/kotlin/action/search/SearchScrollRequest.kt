package fr.myprysm.vertx.elasticsearch.kotlin.action.search

import fr.myprysm.vertx.elasticsearch.action.search.SearchScrollRequest

fun SearchScrollRequest(
        headers: Map<String, String>? = null,
        keepAlive: Long? = null,
        scrollId: String? = null): SearchScrollRequest = fr.myprysm.vertx.elasticsearch.action.search.SearchScrollRequest().apply {

    if (headers != null) {
        this.setHeaders(headers)
    }
    if (keepAlive != null) {
        this.setKeepAlive(keepAlive)
    }
    if (scrollId != null) {
        this.setScrollId(scrollId)
    }
}

