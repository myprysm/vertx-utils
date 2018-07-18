package fr.myprysm.vertx.elasticsearch.kotlin.action.get

import fr.myprysm.vertx.elasticsearch.action.get.GetFailure

fun GetFailure(
  cause: String? = null,
  id: String? = null,
  index: String? = null,
  type: String? = null): GetFailure = fr.myprysm.vertx.elasticsearch.action.get.GetFailure().apply {

  if (cause != null) {
    this.setCause(cause)
  }
  if (id != null) {
    this.setId(id)
  }
  if (index != null) {
    this.setIndex(index)
  }
  if (type != null) {
    this.setType(type)
  }
}

