package fr.myprysm.vertx.elasticsearch.kotlin.action.admin.indices.get

import fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest
import org.elasticsearch.action.admin.indices.get.GetIndexRequest.Feature

fun GetIndexRequest(
        features: Iterable<Feature>? = null,
        headers: Map<String, String>? = null,
        includeDefaults: Boolean? = null,
        indices: Iterable<String>? = null,
        local: Boolean? = null,
        masterNodeTimeout: Long? = null,
        types: Iterable<String>? = null): GetIndexRequest = fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest().apply {

    if (features != null) {
        this.setFeatures(features.toList())
    }
    if (headers != null) {
        this.setHeaders(headers)
    }
    if (includeDefaults != null) {
        this.setIncludeDefaults(includeDefaults)
    }
    if (indices != null) {
        this.setIndices(indices.toList())
    }
    if (local != null) {
        this.setLocal(local)
    }
    if (masterNodeTimeout != null) {
        this.setMasterNodeTimeout(masterNodeTimeout)
    }
    if (types != null) {
        this.setTypes(types.toList())
    }
}

