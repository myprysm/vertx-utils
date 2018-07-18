package fr.myprysm.vertx.elasticsearch.kotlin.action.support

import fr.myprysm.vertx.elasticsearch.action.support.FetchSourceContext

fun FetchSourceContext(
  excludes: Iterable<String>? = null,
  fetchSource: Boolean? = null,
  includes: Iterable<String>? = null): FetchSourceContext = fr.myprysm.vertx.elasticsearch.action.support.FetchSourceContext().apply {

  if (excludes != null) {
    this.setExcludes(excludes.toList())
  }
  if (fetchSource != null) {
    this.setFetchSource(fetchSource)
  }
  if (includes != null) {
    this.setIncludes(includes.toList())
  }
}

