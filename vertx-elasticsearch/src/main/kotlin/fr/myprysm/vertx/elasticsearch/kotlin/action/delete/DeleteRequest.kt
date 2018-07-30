package fr.myprysm.vertx.elasticsearch.kotlin.action.delete

import fr.myprysm.vertx.elasticsearch.action.delete.DeleteRequest
import org.elasticsearch.action.DocWriteRequest.OpType
import org.elasticsearch.action.support.WriteRequest.RefreshPolicy
import org.elasticsearch.index.VersionType

fun DeleteRequest(
        headers: Map<String, String>? = null,
        id: String? = null,
        index: String? = null,
        opType: OpType? = null,
        parent: String? = null,
        refreshPolicy: RefreshPolicy? = null,
        routing: String? = null,
        timeout: Long? = null,
        type: String? = null,
        version: Long? = null,
        versionType: VersionType? = null,
        waitForActiveShards: Int? = null): DeleteRequest = fr.myprysm.vertx.elasticsearch.action.delete.DeleteRequest().apply {

  if (headers != null) {
      this.setHeaders(headers)
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
  if (parent != null) {
    this.setParent(parent)
  }
  if (refreshPolicy != null) {
    this.setRefreshPolicy(refreshPolicy)
  }
  if (routing != null) {
    this.setRouting(routing)
  }
    if (timeout != null) {
        this.setTimeout(timeout)
    }
  if (type != null) {
    this.setType(type)
  }
  if (version != null) {
    this.setVersion(version)
  }
  if (versionType != null) {
    this.setVersionType(versionType)
  }
    if (waitForActiveShards != null) {
        this.setWaitForActiveShards(waitForActiveShards)
    }
}

