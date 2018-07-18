package fr.myprysm.vertx.elasticsearch.kotlin.action.bulk

import fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponseItem
import org.elasticsearch.action.DocWriteRequest.OpType
import org.elasticsearch.rest.RestStatus

fun BulkResponseItem(
  failed: Boolean? = null,
  failure: String? = null,
  id: String? = null,
  index: String? = null,
  opType: OpType? = null,
  status: RestStatus? = null,
  type: String? = null,
  version: Long? = null): BulkResponseItem = fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponseItem().apply {

  if (failed != null) {
    this.setFailed(failed)
  }
  if (failure != null) {
    this.setFailure(failure)
  }
  if (id != null) {
    this.setId(id)
  }
  if (index != null) {
    this.setIndex(index)
  }
  if (opType != null) {
    this.setOpType(opType)
  }
  if (status != null) {
    this.setStatus(status)
  }
  if (type != null) {
    this.setType(type)
  }
  if (version != null) {
    this.setVersion(version)
  }
}

