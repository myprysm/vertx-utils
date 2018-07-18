package fr.myprysm.vertx.elasticsearch.kotlin.action.search.suggest

import fr.myprysm.vertx.elasticsearch.action.search.suggest.CompletionEntry

fun CompletionEntry(
        length: Int? = null,
        offset: Int? = null,
        text: String? = null): CompletionEntry = fr.myprysm.vertx.elasticsearch.action.search.suggest.CompletionEntry(io.vertx.core.json.JsonObject()).apply {

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

