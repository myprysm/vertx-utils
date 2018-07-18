package fr.myprysm.vertx.elasticsearch.kotlin.action.update

import fr.myprysm.vertx.elasticsearch.action.update.UpdateRequest
import fr.myprysm.vertx.elasticsearch.action.support.FetchSourceContext
import fr.myprysm.vertx.elasticsearch.action.support.Script
import org.elasticsearch.action.DocWriteRequest.OpType
import org.elasticsearch.action.support.WriteRequest.RefreshPolicy
import org.elasticsearch.index.VersionType

fun UpdateRequest(
  detectNoop: Boolean? = null,
  doc: io.vertx.core.json.JsonObject? = null,
  docAsUpsert: Boolean? = null,
  fetchSourceContext: fr.myprysm.vertx.elasticsearch.action.support.FetchSourceContext? = null,
  fields: Iterable<String>? = null,
  headers: Map<String, String>? = null,
  id: String? = null,
  index: String? = null,
  opType: OpType? = null,
  parent: String? = null,
  refreshPolicy: RefreshPolicy? = null,
  retryOnConflict: Int? = null,
  routing: String? = null,
  script: fr.myprysm.vertx.elasticsearch.action.support.Script? = null,
  scriptedUpsert: Boolean? = null,
  type: String? = null,
  upsert: io.vertx.core.json.JsonObject? = null,
  version: Long? = null,
  versionType: VersionType? = null,
  waitForActiveShards: Int? = null): UpdateRequest = fr.myprysm.vertx.elasticsearch.action.update.UpdateRequest().apply {

  if (detectNoop != null) {
    this.setDetectNoop(detectNoop)
  }
  if (doc != null) {
    this.setDoc(doc)
  }
  if (docAsUpsert != null) {
    this.setDocAsUpsert(docAsUpsert)
  }
  if (fetchSourceContext != null) {
    this.setFetchSourceContext(fetchSourceContext)
  }
  if (fields != null) {
    this.setFields(fields.toList())
  }
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
  if (retryOnConflict != null) {
    this.setRetryOnConflict(retryOnConflict)
  }
  if (routing != null) {
    this.setRouting(routing)
  }
  if (script != null) {
    this.setScript(script)
  }
  if (scriptedUpsert != null) {
    this.setScriptedUpsert(scriptedUpsert)
  }
  if (type != null) {
    this.setType(type)
  }
  if (upsert != null) {
    this.setUpsert(upsert)
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

