package fr.myprysm.vertx.elasticsearch.kotlin.action.search.suggest

import fr.myprysm.vertx.elasticsearch.action.search.suggest.PhraseEntry

fun PhraseEntry(
        length: Int? = null,
        offset: Int? = null,
        text: String? = null): PhraseEntry = fr.myprysm.vertx.elasticsearch.action.search.suggest.PhraseEntry(io.vertx.core.json.JsonObject()).apply {

  if (length != null) {
    this.setLength(length)
  }
  if (offset != null) {
    this.setOffset(offset)
  }
  if (text != null) {
    this.setText(text)
  }
}

