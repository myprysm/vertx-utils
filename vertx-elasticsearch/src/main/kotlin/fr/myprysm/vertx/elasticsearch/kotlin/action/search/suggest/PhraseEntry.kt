package fr.myprysm.vertx.elasticsearch.kotlin.action.search.suggest

import fr.myprysm.vertx.elasticsearch.action.search.suggest.PhraseEntry
import fr.myprysm.vertx.elasticsearch.action.search.suggest.PhraseOption

fun PhraseEntry(
        length: Int? = null,
        offset: Int? = null,
        options: Iterable<fr.myprysm.vertx.elasticsearch.action.search.suggest.PhraseOption>? = null,
        text: String? = null): PhraseEntry = fr.myprysm.vertx.elasticsearch.action.search.suggest.PhraseEntry().apply {

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

