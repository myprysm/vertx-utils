package fr.myprysm.vertx.elasticsearch.kotlin.action.search.suggest

import fr.myprysm.vertx.elasticsearch.action.search.suggest.TermEntry
import fr.myprysm.vertx.elasticsearch.action.search.suggest.TermOption

fun TermEntry(
        length: Int? = null,
        offset: Int? = null,
        options: Iterable<fr.myprysm.vertx.elasticsearch.action.search.suggest.TermOption>? = null,
        text: String? = null): TermEntry = fr.myprysm.vertx.elasticsearch.action.search.suggest.TermEntry().apply {

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

