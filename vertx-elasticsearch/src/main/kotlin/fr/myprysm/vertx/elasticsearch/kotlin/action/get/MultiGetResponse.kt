package fr.myprysm.vertx.elasticsearch.kotlin.action.get

import fr.myprysm.vertx.elasticsearch.action.get.MultiGetResponse
import fr.myprysm.vertx.elasticsearch.action.get.GetFailure
import fr.myprysm.vertx.elasticsearch.action.get.GetResponse

fun MultiGetResponse(
        failures: Iterable<fr.myprysm.vertx.elasticsearch.action.get.GetFailure>? = null,
        responses: Iterable<fr.myprysm.vertx.elasticsearch.action.get.GetResponse>? = null): MultiGetResponse = fr.myprysm.vertx.elasticsearch.action.get.MultiGetResponse().apply {

    if (failures != null) {
        this.setFailures(failures.toList())
    }
    if (responses != null) {
        this.setResponses(responses.toList())
    }
}

