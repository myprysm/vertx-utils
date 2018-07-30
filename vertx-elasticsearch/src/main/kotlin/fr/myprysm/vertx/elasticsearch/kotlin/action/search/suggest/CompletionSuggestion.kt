package fr.myprysm.vertx.elasticsearch.kotlin.action.search.suggest

import fr.myprysm.vertx.elasticsearch.action.search.suggest.CompletionSuggestion
import fr.myprysm.vertx.elasticsearch.action.search.suggest.CompletionEntry

fun CompletionSuggestion(
        entries: Iterable<fr.myprysm.vertx.elasticsearch.action.search.suggest.CompletionEntry>? = null,
        name: String? = null,
        type: String? = null): CompletionSuggestion = fr.myprysm.vertx.elasticsearch.action.search.suggest.CompletionSuggestion().apply {

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

