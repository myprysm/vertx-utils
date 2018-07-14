package fr.myprysm.vertx.elasticsearch.groovy;
public class ClusterClient_GroovyExtension {
  public static void putSettings(fr.myprysm.vertx.elasticsearch.ClusterClient j_receiver, java.util.Map<String, Object> request, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.Map<String, Object>>> handler) {
    j_receiver.putSettings(request != null ? new fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest(io.vertx.core.impl.ConversionHelper.toJsonObject(request)) : null,
      handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse>>() {
      public void handle(io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse> ar) {
        handler.handle(ar.map(event -> event != null ? io.vertx.core.impl.ConversionHelper.fromJsonObject(event.toJson()) : null));
      }
    } : null);
  }
}
