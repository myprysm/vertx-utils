package fr.myprysm.vertx.elasticsearch.kotlin.action.admin.indices.create

import fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexResponse

fun CreateIndexResponse(
  acknowledged: Boolean? = null,
  index: String? = null,
  shardsAcknowledged: Boolean? = null): CreateIndexResponse = fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexResponse().apply {

  if (acknowledged != null) {
    this.setAcknowledged(acknowledged)
  }
  if (index != null) {
    this.setIndex(index)
  }
  if (shardsAcknowledged != null) {
    this.setShardsAcknowledged(shardsAcknowledged)
  }
}

