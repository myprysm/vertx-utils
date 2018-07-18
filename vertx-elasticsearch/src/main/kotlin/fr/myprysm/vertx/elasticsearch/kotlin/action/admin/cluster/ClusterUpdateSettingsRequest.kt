package fr.myprysm.vertx.elasticsearch.kotlin.action.admin.cluster

import fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest

/**
 * A function providing a DSL for building [fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest] objects.
 *
 * ClusterUpdateSettingsRequest DataObject.
 *
 * @param headers  Add a header to the request.
 * @param persistentSettings 
 * @param transientSettings 
 *
 * <p/>
 * NOTE: This function has been automatically generated from the [fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest original] using Vert.x codegen.
 */
fun ClusterUpdateSettingsRequest(
  headers: Map<String, String>? = null,
  persistentSettings: io.vertx.core.json.JsonObject? = null,
  transientSettings: io.vertx.core.json.JsonObject? = null): ClusterUpdateSettingsRequest = fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest().apply {

  if (headers != null) {
    this.setHeaders(headers)
  }
  if (persistentSettings != null) {
    this.setPersistentSettings(persistentSettings)
  }
  if (transientSettings != null) {
    this.setTransientSettings(transientSettings)
  }
}

