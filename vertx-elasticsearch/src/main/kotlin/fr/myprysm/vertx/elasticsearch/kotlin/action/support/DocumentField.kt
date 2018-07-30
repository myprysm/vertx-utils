package fr.myprysm.vertx.elasticsearch.kotlin.action.support

import fr.myprysm.vertx.elasticsearch.action.support.DocumentField

fun DocumentField(
        name: String? = null,
        values: io.vertx.core.json.JsonArray? = null): DocumentField = fr.myprysm.vertx.elasticsearch.action.support.DocumentField().apply {

    if (name != null) {
        this.setName(name)
    }
    if (values != null) {
        this.setValues(values)
    }
}

