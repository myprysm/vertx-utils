package fr.myprysm.vertx.elasticsearch.kotlin.action.search.suggest

import fr.myprysm.vertx.elasticsearch.action.search.suggest.TermSuggestion
import fr.myprysm.vertx.elasticsearch.action.search.suggest.TermEntry

fun TermSuggestion(
        entries: Iterable<fr.myprysm.vertx.elasticsearch.action.search.suggest.TermEntry>? = null,
        name: String? = null,
        type: String? = null): TermSuggestion = fr.myprysm.vertx.elasticsearch.action.search.suggest.TermSuggestion().apply {

    if (entries != null) {
        this.setEntries(entries.toList())
    }
    if (name != null) {
        this.setName(name)
    }
    if (type != null) {
        this.setType(type)
    }
}

