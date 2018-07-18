package fr.myprysm.vertx.elasticsearch.kotlin.action.support

import fr.myprysm.vertx.elasticsearch.action.support.Explanation

fun Explanation(
  description: String? = null,
  details: Iterable<fr.myprysm.vertx.elasticsearch.action.support.Explanation>? = null,
  match: Boolean? = null,
  value: Float? = null): Explanation = fr.myprysm.vertx.elasticsearch.action.support.Explanation().apply {

  if (description != null) {
    this.setDescription(description)
  }
  if (details != null) {
    this.setDetails(details.toList())
  }
  if (match != null) {
    this.setMatch(match)
  }
  if (value != null) {
    this.setValue(value)
  }
}

