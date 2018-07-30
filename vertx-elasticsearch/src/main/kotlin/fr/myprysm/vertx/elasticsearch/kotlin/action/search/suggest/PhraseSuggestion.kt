package fr.myprysm.vertx.elasticsearch.kotlin.action.search.suggest

import fr.myprysm.vertx.elasticsearch.action.search.suggest.PhraseSuggestion
import fr.myprysm.vertx.elasticsearch.action.search.suggest.PhraseEntry

fun PhraseSuggestion(
        entries: Iterable<fr.myprysm.vertx.elasticsearch.action.search.suggest.PhraseEntry>? = null,
        name: String? = null,
        type: String? = null): PhraseSuggestion = fr.myprysm.vertx.elasticsearch.action.search.suggest.PhraseSuggestion().apply {

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

