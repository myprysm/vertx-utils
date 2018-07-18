package fr.myprysm.vertx.elasticsearch.kotlin.action.get

import fr.myprysm.vertx.elasticsearch.action.get.GetRequest
import fr.myprysm.vertx.elasticsearch.action.support.FetchSourceContext
import org.elasticsearch.index.VersionType

fun GetRequest(
  fetchSourceContext: fr.myprysm.vertx.elasticsearch.action.support.FetchSourceContext? = null,
  headers: Map<String, String>? = null,
  id: String? = null,
  index: String? = null,
  parent: String? = null,
  preference: String? = null,
  realTime: Boolean? = null,
  refresh: Boolean? = null,
  routing: String? = null,
  storedFields: Iterable<String>? = null,
  type: String? = null,
  version: Long? = null,
  versionType: VersionType? = null): GetRequest = fr.myprysm.vertx.elasticsearch.action.get.GetRequest().apply {

  if (fetchSourceContext != null) {
    this.setFetchSourceContext(fetchSourceContext)
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
  if (parent != null) {
    this.setParent(parent)
  }
  if (preference != null) {
    this.setPreference(preference)
  }
  if (realTime != null) {
    this.setRealTime(realTime)
  }
  if (refresh != null) {
    this.setRefresh(refresh)
  }
  if (routing != null) {
    this.setRouting(routing)
  }
  if (storedFields != null) {
    this.setStoredFields(storedFields.toList())
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
}

