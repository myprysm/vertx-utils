package fr.myprysm.vertx.elasticsearch.kotlin.action.get

import fr.myprysm.vertx.elasticsearch.action.get.GetRequestItem
import fr.myprysm.vertx.elasticsearch.action.support.FetchSourceContext
import org.elasticsearch.index.VersionType

fun GetRequestItem(
  fetchSourceContext: fr.myprysm.vertx.elasticsearch.action.support.FetchSourceContext? = null,
  id: String? = null,
  index: String? = null,
  parent: String? = null,
  routing: String? = null,
  storedFields: Iterable<String>? = null,
  type: String? = null,
  version: Long? = null,
  versionType: VersionType? = null): GetRequestItem = fr.myprysm.vertx.elasticsearch.action.get.GetRequestItem().apply {

  if (fetchSourceContext != null) {
    this.setFetchSourceContext(fetchSourceContext)
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

