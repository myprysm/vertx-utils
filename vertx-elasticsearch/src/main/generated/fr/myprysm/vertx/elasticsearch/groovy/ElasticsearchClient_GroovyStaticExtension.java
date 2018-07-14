package fr.myprysm.vertx.elasticsearch.groovy;
public class ElasticsearchClient_GroovyStaticExtension {
  public static fr.myprysm.vertx.elasticsearch.ElasticsearchClient createNonShared(fr.myprysm.vertx.elasticsearch.ElasticsearchClient j_receiver, io.vertx.core.Vertx vertx, java.util.Map<String, Object> config) {
    return io.vertx.core.impl.ConversionHelper.fromObject(fr.myprysm.vertx.elasticsearch.ElasticsearchClient.createNonShared(vertx,
      config != null ? io.vertx.core.impl.ConversionHelper.toJsonObject(config) : null));
  }
  public static fr.myprysm.vertx.elasticsearch.ElasticsearchClient createShared(fr.myprysm.vertx.elasticsearch.ElasticsearchClient j_receiver, io.vertx.core.Vertx vertx, java.util.Map<String, Object> config) {
    return io.vertx.core.impl.ConversionHelper.fromObject(fr.myprysm.vertx.elasticsearch.ElasticsearchClient.createShared(vertx,
      config != null ? io.vertx.core.impl.ConversionHelper.toJsonObject(config) : null));
  }
  public static fr.myprysm.vertx.elasticsearch.ElasticsearchClient createShared(fr.myprysm.vertx.elasticsearch.ElasticsearchClient j_receiver, io.vertx.core.Vertx vertx, java.util.Map<String, Object> config, java.lang.String clientName) {
    return io.vertx.core.impl.ConversionHelper.fromObject(fr.myprysm.vertx.elasticsearch.ElasticsearchClient.createShared(vertx,
      config != null ? io.vertx.core.impl.ConversionHelper.toJsonObject(config) : null,
      clientName));
  }
}
