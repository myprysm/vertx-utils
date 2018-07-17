package fr.myprysm.vertx.elasticsearch.kotlin.action.admin.indices.mapping.put

import fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest

fun PutMappingRequest(
        headers: Map<String, String>? = null,
        indices: Iterable<String>? = null,
        source: io.vertx.core.json.JsonObject? = null,
        timeout: Long? = null,
        type: String? = null): PutMappingRequest = fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest().apply {

    if (headers != null) {
        this.setHeaders(headers)
    }
    if (indices != null) {
        this.setIndices(indices.toList())
    }
    if (source != null) {
        this.setSource(source)
    }
    if (timeout != null) {
        this.setTimeout(timeout)
    }
    if (type != null) {
        this.setType(type)
    }
}

