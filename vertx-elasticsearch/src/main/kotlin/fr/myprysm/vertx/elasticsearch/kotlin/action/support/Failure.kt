package fr.myprysm.vertx.elasticsearch.kotlin.action.support

import fr.myprysm.vertx.elasticsearch.action.support.Failure

fun Failure(
  cause: String? = null): Failure = fr.myprysm.vertx.elasticsearch.action.support.Failure().apply {

  if (cause != null) {
    this.setCause(cause)
  }
}

