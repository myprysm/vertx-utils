package fr.myprysm.vertx.elasticsearch.kotlin.action.search.suggest

import fr.myprysm.vertx.elasticsearch.action.search.suggest.CompletionEntry
import fr.myprysm.vertx.elasticsearch.action.search.suggest.CompletionOption

fun CompletionEntry(
        length: Int? = null,
        offset: Int? = null,
        options: Iterable<fr.myprysm.vertx.elasticsearch.action.search.suggest.CompletionOption>? = null,
        text: String? = null): CompletionEntry = fr.myprysm.vertx.elasticsearch.action.search.suggest.CompletionEntry().apply {

    if (length != null) {
        this.setLength(length)
    }
    if (offset != null) {
        this.setOffset(offset)
    }
    if (options != null) {
        this.setOptions(options.toList())
    }
    if (text != null) {
        this.setText(text)
    }
}

