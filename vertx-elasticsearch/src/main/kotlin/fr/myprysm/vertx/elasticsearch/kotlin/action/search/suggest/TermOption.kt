package fr.myprysm.vertx.elasticsearch.kotlin.action.search.suggest

import fr.myprysm.vertx.elasticsearch.action.search.suggest.TermOption

fun TermOption(
        collateMatch: Boolean? = null,
        highlighted: String? = null,
        score: Float? = null,
        text: String? = null): TermOption = fr.myprysm.vertx.elasticsearch.action.search.suggest.TermOption(io.vertx.core.json.JsonObject()).apply {

  if (collateMatch != null) {
    this.setCollateMatch(collateMatch)
  }
  if (highlighted != null) {
    this.setHighlighted(highlighted)
  }
  if (score != null) {
    this.setScore(score)
  }
  if (text != null) {
    this.setText(text)
  }
}

