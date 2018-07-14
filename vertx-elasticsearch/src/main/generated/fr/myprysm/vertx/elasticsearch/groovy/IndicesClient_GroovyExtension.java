package fr.myprysm.vertx.elasticsearch.groovy;
public class IndicesClient_GroovyExtension {
  public static void delete(fr.myprysm.vertx.elasticsearch.IndicesClient j_receiver, java.util.Map<String, Object> request, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.Map<String, Object>>> handler) {
    j_receiver.delete(request != null ? new fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexRequest(io.vertx.core.impl.ConversionHelper.toJsonObject(request)) : null,
      handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexResponse>>() {
      public void handle(io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexResponse> ar) {
        handler.handle(ar.map(event -> event != null ? io.vertx.core.impl.ConversionHelper.fromJsonObject(event.toJson()) : null));
      }
    } : null);
  }
  public static void exists(fr.myprysm.vertx.elasticsearch.IndicesClient j_receiver, java.util.Map<String, Object> request, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Boolean>> handler) {
    j_receiver.exists(request != null ? new fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest(io.vertx.core.impl.ConversionHelper.toJsonObject(request)) : null,
      handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Boolean>>() {
      public void handle(io.vertx.core.AsyncResult<java.lang.Boolean> ar) {
        handler.handle(ar.map(event -> event));
      }
    } : null);
  }
}
