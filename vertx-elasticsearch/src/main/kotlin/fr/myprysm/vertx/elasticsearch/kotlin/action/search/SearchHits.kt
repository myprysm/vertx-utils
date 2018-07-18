package fr.myprysm.vertx.elasticsearch.kotlin.action.search

import fr.myprysm.vertx.elasticsearch.action.search.SearchHits
import fr.myprysm.vertx.elasticsearch.action.search.SearchHit

fun SearchHits(
  hits: Iterable<fr.myprysm.vertx.elasticsearch.action.search.SearchHit>? = null,
  maxScore: Float? = null,
  totalHits: Long? = null): SearchHits = fr.myprysm.vertx.elasticsearch.action.search.SearchHits().apply {

  if (hits != null) {
    this.setHits(hits.toList())
  }
  if (maxScore != null) {
    this.setMaxScore(maxScore)
  }
  if (totalHits != null) {
    this.setTotalHits(totalHits)
  }
}

