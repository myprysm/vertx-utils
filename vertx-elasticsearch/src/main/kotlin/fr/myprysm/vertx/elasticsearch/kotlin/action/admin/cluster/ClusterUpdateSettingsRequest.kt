package fr.myprysm.vertx.elasticsearch.kotlin.action.admin.cluster

import fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest

/**
 * A function providing a DSL for building [fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest] objects.
 *
 * ClusterUpdateSettingsRequest DataObject.
 *
 * @param headers  Add a header to the request.
 *
 * <p/>
 * NOTE: This function has been automatically generated from the [fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest original] using Vert.x codegen.
 */
fun ClusterUpdateSettingsRequest(
        headers: Map<String, String>? = null): ClusterUpdateSettingsRequest = fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest(io.vertx.core.json.JsonObject()).apply {

  if (headers != null) {
      for (item in headers) {
          this.addHeader(item.key, item.value)
      }
  }
}

