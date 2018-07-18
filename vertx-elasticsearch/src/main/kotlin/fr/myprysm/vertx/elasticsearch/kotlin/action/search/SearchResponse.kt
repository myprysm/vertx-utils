package fr.myprysm.vertx.elasticsearch.kotlin.action.search

import fr.myprysm.vertx.elasticsearch.action.search.SearchResponse
import fr.myprysm.vertx.elasticsearch.action.search.Clusters
import fr.myprysm.vertx.elasticsearch.action.search.SearchHits
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation
import fr.myprysm.vertx.elasticsearch.action.search.suggest.Suggestion
import fr.myprysm.vertx.elasticsearch.action.support.ShardFailure

fun SearchResponse(
  aggregations: Map<String, fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation>? = null,
  clusters: fr.myprysm.vertx.elasticsearch.action.search.Clusters? = null,
  failedShards: Int? = null,
  hits: fr.myprysm.vertx.elasticsearch.action.search.SearchHits? = null,
  numReducePhases: Int? = null,
  profileResults: Map<String, io.vertx.core.json.JsonObject>? = null,
  scrollId: String? = null,
  shardFailures: Iterable<fr.myprysm.vertx.elasticsearch.action.support.ShardFailure>? = null,
  skippedShards: Int? = null,
  successfulShards: Int? = null,
  suggest: Map<String, fr.myprysm.vertx.elasticsearch.action.search.suggest.Suggestion>? = null,
  terminatedEarly: Boolean? = null,
  timedOut: Boolean? = null,
  tookInMillis: Long? = null,
  totalShards: Int? = null): SearchResponse = fr.myprysm.vertx.elasticsearch.action.search.SearchResponse().apply {

  if (aggregations != null) {
    this.setAggregations(aggregations)
  }
  if (clusters != null) {
    this.setClusters(clusters)
  }
  if (failedShards != null) {
    this.setFailedShards(failedShards)
  }
  if (hits != null) {
    this.setHits(hits)
  }
  if (numReducePhases != null) {
    this.setNumReducePhases(numReducePhases)
  }
  if (profileResults != null) {
    this.setProfileResults(profileResults)
  }
  if (scrollId != null) {
    this.setScrollId(scrollId)
  }
  if (shardFailures != null) {
    this.setShardFailures(shardFailures.toList())
  }
  if (skippedShards != null) {
    this.setSkippedShards(skippedShards)
  }
  if (successfulShards != null) {
    this.setSuccessfulShards(successfulShards)
  }
  if (suggest != null) {
    this.setSuggest(suggest)
  }
  if (terminatedEarly != null) {
    this.setTerminatedEarly(terminatedEarly)
  }
  if (timedOut != null) {
    this.setTimedOut(timedOut)
  }
  if (tookInMillis != null) {
    this.setTookInMillis(tookInMillis)
  }
  if (totalShards != null) {
    this.setTotalShards(totalShards)
  }
}

