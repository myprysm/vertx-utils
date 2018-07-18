package fr.myprysm.vertx.elasticsearch.kotlin.action.bulk

import fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponse
import fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponseItem
import org.elasticsearch.rest.RestStatus

fun BulkResponse(
  errors: Boolean? = null,
  items: Iterable<fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponseItem>? = null,
  status: RestStatus? = null,
  took: Long? = null): BulkResponse = fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponse().apply {

  if (errors != null) {
    this.setErrors(errors)
  }
  if (items != null) {
    this.setItems(items.toList())
  }
  if (status != null) {
    this.setStatus(status)
  }
  if (took != null) {
    this.setTook(took)
  }
}

