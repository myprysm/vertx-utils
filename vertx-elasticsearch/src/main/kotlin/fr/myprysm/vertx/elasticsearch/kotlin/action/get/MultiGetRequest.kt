package fr.myprysm.vertx.elasticsearch.kotlin.action.get

import fr.myprysm.vertx.elasticsearch.action.get.MultiGetRequest
import fr.myprysm.vertx.elasticsearch.action.get.GetRequestItem

fun MultiGetRequest(
        headers: Map<String, String>? = null,
        items: Iterable<fr.myprysm.vertx.elasticsearch.action.get.GetRequestItem>? = null,
        preference: String? = null,
        realTime: Boolean? = null,
        refresh: Boolean? = null): MultiGetRequest = fr.myprysm.vertx.elasticsearch.action.get.MultiGetRequest().apply {

    if (headers != null) {
        this.setHeaders(headers)
    }
    if (items != null) {
        this.setItems(items.toList())
    }
    if (preference != null) {
        this.setPreference(preference)
    }
    if (realTime != null) {
        this.setRealTime(realTime)
    }
    if (refresh != null) {
        this.setRefresh(refresh)
    }
}

