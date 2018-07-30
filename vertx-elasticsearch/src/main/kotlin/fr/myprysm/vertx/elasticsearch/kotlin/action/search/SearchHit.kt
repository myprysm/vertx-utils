package fr.myprysm.vertx.elasticsearch.kotlin.action.search

import fr.myprysm.vertx.elasticsearch.action.search.SearchHit
import fr.myprysm.vertx.elasticsearch.action.search.NestedIdentity
import fr.myprysm.vertx.elasticsearch.action.search.SearchHits
import fr.myprysm.vertx.elasticsearch.action.support.DocumentField
import fr.myprysm.vertx.elasticsearch.action.support.Explanation

fun SearchHit(
        clusterAlias: String? = null,
        explanation: fr.myprysm.vertx.elasticsearch.action.support.Explanation? = null,
        fields: Map<String, fr.myprysm.vertx.elasticsearch.action.support.DocumentField>? = null,
        id: String? = null,
        index: String? = null,
        innerHits: Map<String, fr.myprysm.vertx.elasticsearch.action.search.SearchHits>? = null,
        matchedQueries: Iterable<String>? = null,
        nestedIdentity: fr.myprysm.vertx.elasticsearch.action.search.NestedIdentity? = null,
        score: Float? = null,
        sortValues: io.vertx.core.json.JsonArray? = null,
        source: io.vertx.core.json.JsonObject? = null,
        type: String? = null,
        version: Long? = null): SearchHit = fr.myprysm.vertx.elasticsearch.action.search.SearchHit().apply {

    if (clusterAlias != null) {
        this.setClusterAlias(clusterAlias)
    }
    if (explanation != null) {
        this.setExplanation(explanation)
    }
    if (fields != null) {
        this.setFields(fields)
    }
    if (id != null) {
        this.setId(id)
    }
    if (index != null) {
        this.setIndex(index)
    }
    if (innerHits != null) {
        this.setInnerHits(innerHits)
    }
    if (matchedQueries != null) {
        this.setMatchedQueries(matchedQueries.toList())
    }
    if (nestedIdentity != null) {
        this.setNestedIdentity(nestedIdentity)
    }
    if (score != null) {
        this.setScore(score)
    }
    if (sortValues != null) {
        this.setSortValues(sortValues)
    }
    if (source != null) {
        this.setSource(source)
    }
    if (type != null) {
        this.setType(type)
    }
    if (version != null) {
        this.setVersion(version)
    }
}

