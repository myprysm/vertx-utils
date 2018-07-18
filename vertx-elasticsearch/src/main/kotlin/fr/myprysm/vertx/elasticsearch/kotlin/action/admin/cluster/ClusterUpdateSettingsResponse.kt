package fr.myprysm.vertx.elasticsearch.kotlin.action.admin.cluster

import fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse

/**
 * A function providing a DSL for building [fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse] objects.
 *
 * ClusterUpdateSettingsResponse.
 *
 * @param acknowledged 
 * @param persistentSettings 
 * @param transientSettings 
 *
 * <p/>
 * NOTE: This function has been automatically generated from the [fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse original] using Vert.x codegen.
 */
fun ClusterUpdateSettingsResponse(
  acknowledged: Boolean? = null,
  persistentSettings: io.vertx.core.json.JsonObject? = null,
  transientSettings: io.vertx.core.json.JsonObject? = null): ClusterUpdateSettingsResponse = fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse().apply {

  if (acknowledged != null) {
    this.setAcknowledged(acknowledged)
  }
  if (persistentSettings != null) {
    this.setPersistentSettings(persistentSettings)
  }
  if (transientSettings != null) {
    this.setTransientSettings(transientSettings)
  }
}

