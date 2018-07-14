package fr.myprysm.vertx.elasticsearch.kotlin.action.support

import fr.myprysm.vertx.elasticsearch.action.support.Script
import org.elasticsearch.script.ScriptType

fun Script(
        idOrCode: String? = null,
        lang: String? = null,
        options: Map<String, String>? = null,
        params: io.vertx.core.json.JsonObject? = null,
        type: ScriptType? = null): Script = fr.myprysm.vertx.elasticsearch.action.support.Script().apply {

    if (idOrCode != null) {
        this.setIdOrCode(idOrCode)
    }
    if (lang != null) {
        this.setLang(lang)
    }
    if (options != null) {
        this.setOptions(options)
    }
    if (params != null) {
        this.setParams(params)
    }
    if (type != null) {
        this.setType(type)
    }
}

