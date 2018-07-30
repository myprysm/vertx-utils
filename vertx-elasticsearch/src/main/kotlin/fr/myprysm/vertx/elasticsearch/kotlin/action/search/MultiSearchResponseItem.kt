package fr.myprysm.vertx.elasticsearch.kotlin.action.search

import fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponseItem
import fr.myprysm.vertx.elasticsearch.action.search.SearchResponse
import fr.myprysm.vertx.elasticsearch.action.support.Failure

fun MultiSearchResponseItem(
        failure: fr.myprysm.vertx.elasticsearch.action.support.Failure? = null,
        response: fr.myprysm.vertx.elasticsearch.action.search.SearchResponse? = null): MultiSearchResponseItem = fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponseItem().apply {

    if (failure != null) {
        this.setFailure(failure)
    }
    if (response != null) {
        this.setResponse(response)
    }
}

