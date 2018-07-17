package fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch;

import com.redhat.ceylon.compiler.java.metadata.Ceylon;
import com.redhat.ceylon.compiler.java.metadata.TypeInfo;
import com.redhat.ceylon.compiler.java.metadata.TypeParameter;
import com.redhat.ceylon.compiler.java.metadata.TypeParameters;
import com.redhat.ceylon.compiler.java.metadata.Variance;
import com.redhat.ceylon.compiler.java.metadata.Ignore;
import com.redhat.ceylon.compiler.java.metadata.Name;
import com.redhat.ceylon.compiler.java.runtime.model.TypeDescriptor;
import com.redhat.ceylon.compiler.java.runtime.model.ReifiedType;
import ceylon.language.Callable;
import ceylon.language.DocAnnotation$annotation$;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AsyncResult;
import io.vertx.ceylon.core.Vertx;
import io.vertx.core.Handler;

@Ceylon(major = 8)
@Name("elasticsearchClient")
@com.redhat.ceylon.compiler.java.metadata.Object
public class elasticsearchClient_ implements ReifiedType {

  private static final elasticsearchClient_ instance = new elasticsearchClient_();
  public static final TypeDescriptor $TypeDescriptor$ = TypeDescriptor.klass(elasticsearchClient_.class);

  @Ignore
  public TypeDescriptor $getType$() {
    return $TypeDescriptor$;
  }

  @Ignore
  @TypeInfo("fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch::elasticsearchClient")
  public static elasticsearchClient_ get_() {
    return instance;
  }


  @DocAnnotation$annotation$(description = " Create an ElasticSearch client which maintains its own data source.\n")
  @TypeInfo("fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch::ElasticsearchClient")
  public ElasticsearchClient createNonShared(
    final @TypeInfo("io.vertx.ceylon.core::Vertx") @Name("vertx")@DocAnnotation$annotation$(description = "the Vert.x instance\n") Vertx vertx, 
    final @TypeInfo("ceylon.json::Object") @Name("config")@DocAnnotation$annotation$(description = "the configuration\n") ceylon.json.Object config) {
    io.vertx.core.Vertx arg_0 = io.vertx.ceylon.core.Vertx.TO_JAVA.safeConvert(vertx);
    io.vertx.core.json.JsonObject arg_1 = io.vertx.lang.ceylon.ToJava.JsonObject.safeConvert(config);
    ElasticsearchClient ret = fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.ElasticsearchClient.TO_CEYLON.converter().safeConvert(fr.myprysm.vertx.elasticsearch.ElasticsearchClient.createNonShared(arg_0, arg_1));
    return ret;
  }

  @DocAnnotation$annotation$(description = " Like [createShared](elasticsearchClient.type.html#createShared) but with the default data source name.\n")
  @TypeInfo("fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch::ElasticsearchClient")
  public ElasticsearchClient createShared(
    final @TypeInfo("io.vertx.ceylon.core::Vertx") @Name("vertx")@DocAnnotation$annotation$(description = "the Vert.x instance\n") Vertx vertx, 
    final @TypeInfo("ceylon.json::Object") @Name("config")@DocAnnotation$annotation$(description = "the configuration\n") ceylon.json.Object config) {
    io.vertx.core.Vertx arg_0 = io.vertx.ceylon.core.Vertx.TO_JAVA.safeConvert(vertx);
    io.vertx.core.json.JsonObject arg_1 = io.vertx.lang.ceylon.ToJava.JsonObject.safeConvert(config);
    ElasticsearchClient ret = fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.ElasticsearchClient.TO_CEYLON.converter().safeConvert(fr.myprysm.vertx.elasticsearch.ElasticsearchClient.createShared(arg_0, arg_1));
    return ret;
  }

  @DocAnnotation$annotation$(description = " Create an ElasticSearch client which shares its data source with any other Mongo clients created with the same\n client name.\n")
  @TypeInfo("fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch::ElasticsearchClient")
  public ElasticsearchClient createShared(
    final @TypeInfo("io.vertx.ceylon.core::Vertx") @Name("vertx")@DocAnnotation$annotation$(description = "the Vert.x instance\n") Vertx vertx, 
    final @TypeInfo("ceylon.json::Object") @Name("config")@DocAnnotation$annotation$(description = "the configuration\n") ceylon.json.Object config, 
    final @TypeInfo("ceylon.language::String") @Name("clientName")@DocAnnotation$annotation$(description = "the client name\n") ceylon.language.String clientName) {
    io.vertx.core.Vertx arg_0 = io.vertx.ceylon.core.Vertx.TO_JAVA.safeConvert(vertx);
    io.vertx.core.json.JsonObject arg_1 = io.vertx.lang.ceylon.ToJava.JsonObject.safeConvert(config);
    java.lang.String arg_2 = io.vertx.lang.ceylon.ToJava.String.safeConvert(clientName);
    ElasticsearchClient ret = fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.ElasticsearchClient.TO_CEYLON.converter().safeConvert(fr.myprysm.vertx.elasticsearch.ElasticsearchClient.createShared(arg_0, arg_1, arg_2));
    return ret;
  }

}
