package fr.myprysm.vertx.elasticsearch.kotlin.action.main

import fr.myprysm.vertx.elasticsearch.action.main.MainResponse

fun MainResponse(
  available: Boolean? = null,
  build: String? = null,
  clusterName: String? = null,
  clusterUuid: String? = null,
  nodeName: String? = null,
  version: String? = null): MainResponse = fr.myprysm.vertx.elasticsearch.action.main.MainResponse().apply {

  if (available != null) {
    this.setAvailable(available)
  }
  if (build != null) {
    this.setBuild(build)
  }
  if (clusterName != null) {
    this.setClusterName(clusterName)
  }
  if (clusterUuid != null) {
    this.setClusterUuid(clusterUuid)
  }
  if (nodeName != null) {
    this.setNodeName(nodeName)
  }
  if (version != null) {
    this.setVersion(version)
  }
}

