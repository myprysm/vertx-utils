package fr.myprysm.vertx.elasticsearch.kotlin.action.admin.indices.create

import fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexRequest
import fr.myprysm.vertx.elasticsearch.action.admin.indices.create.Alias

fun CreateIndexRequest(
  aliases: Iterable<fr.myprysm.vertx.elasticsearch.action.admin.indices.create.Alias>? = null,
  cause: String? = null,
  headers: Map<String, String>? = null,
  index: String? = null,
  mappings: Map<String, io.vertx.core.json.JsonObject>? = null,
  settings: io.vertx.core.json.JsonObject? = null,
  timeout: Long? = null,
  waitForActiveShards: Int? = null): CreateIndexRequest = fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexRequest().apply {

  if (aliases != null) {
    this.setAliases(aliases.toSet())
  }
  if (cause != null) {
    this.setCause(cause)
  }
  if (headers != null) {
    this.setHeaders(headers)
  }
  if (index != null) {
    this.setIndex(index)
  }
  if (mappings != null) {
    this.setMappings(mappings)
  }
  if (settings != null) {
    this.setSettings(settings)
  }
  if (timeout != null) {
    this.setTimeout(timeout)
  }
  if (waitForActiveShards != null) {
    this.setWaitForActiveShards(waitForActiveShards)
  }
}

