package fr.myprysm.vertx.elasticsearch.kotlin.action.get

import fr.myprysm.vertx.elasticsearch.action.get.GetResponse
import fr.myprysm.vertx.elasticsearch.action.support.DocumentField

fun GetResponse(
        exists: Boolean? = null,
        fields: Map<String, fr.myprysm.vertx.elasticsearch.action.support.DocumentField>? = null,
        id: String? = null,
        index: String? = null,
        source: io.vertx.core.json.JsonObject? = null,
        type: String? = null,
        version: Long? = null): GetResponse = fr.myprysm.vertx.elasticsearch.action.get.GetResponse().apply {

    if (exists != null) {
        this.setExists(exists)
    }
    if (fields != null) {
        this.setFields(fields)
    }
    if (id != null) {
        this.setId(id)
    }
    if (index != null) {
        this.setIndex(index)
    }
    if (source != null) {
        this.setSource(source)
    }
    if (type != null) {
        this.setType(type)
    }
    if (version != null) {
        this.setVersion(version)
    }
}

