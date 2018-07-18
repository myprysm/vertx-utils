package fr.myprysm.vertx.elasticsearch.kotlin.action.search

import fr.myprysm.vertx.elasticsearch.action.search.Clusters

fun Clusters(
  skipped: Int? = null,
  successful: Int? = null,
  total: Int? = null): Clusters = fr.myprysm.vertx.elasticsearch.action.search.Clusters().apply {

  if (skipped != null) {
    this.setSkipped(skipped)
  }
  if (successful != null) {
    this.setSuccessful(successful)
  }
  if (total != null) {
    this.setTotal(total)
  }
}

