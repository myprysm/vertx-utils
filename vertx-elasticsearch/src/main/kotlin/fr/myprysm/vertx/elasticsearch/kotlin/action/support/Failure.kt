package fr.myprysm.vertx.elasticsearch.kotlin.action.support

import fr.myprysm.vertx.elasticsearch.action.support.Failure

fun Failure(
): Failure = fr.myprysm.vertx.elasticsearch.action.support.Failure(io.vertx.core.json.JsonObject()).apply {

}

