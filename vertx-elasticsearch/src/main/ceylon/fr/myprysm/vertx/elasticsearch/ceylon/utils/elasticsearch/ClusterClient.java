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
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Ceylon(major = 8)
@DocAnnotation$annotation$(description = " Vertx Elasticsearch cluster client.\n <p>\n See <a href=\"https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster.html\">Cluster API on elastic.co</a>\n")
public class ClusterClient implements ReifiedType {

  @Ignore
  public static final io.vertx.lang.ceylon.ConverterFactory<fr.myprysm.vertx.elasticsearch.ClusterClient, ClusterClient> TO_CEYLON = new io.vertx.lang.ceylon.ConverterFactory<fr.myprysm.vertx.elasticsearch.ClusterClient, ClusterClient>() {
    public io.vertx.lang.ceylon.Converter<fr.myprysm.vertx.elasticsearch.ClusterClient, ClusterClient> converter(final TypeDescriptor... descriptors) {
      return new io.vertx.lang.ceylon.Converter<fr.myprysm.vertx.elasticsearch.ClusterClient, ClusterClient>() {
        public ClusterClient convert(fr.myprysm.vertx.elasticsearch.ClusterClient src) {
          return new ClusterClient(src);
        }
      };
    }
  };

  @Ignore
  public static final io.vertx.lang.ceylon.Converter<ClusterClient, fr.myprysm.vertx.elasticsearch.ClusterClient> TO_JAVA = new io.vertx.lang.ceylon.Converter<ClusterClient, fr.myprysm.vertx.elasticsearch.ClusterClient>() {
    public fr.myprysm.vertx.elasticsearch.ClusterClient convert(ClusterClient src) {
      return src.delegate;
    }
  };

  @Ignore public static final TypeDescriptor $TypeDescriptor$ = new io.vertx.lang.ceylon.VertxTypeDescriptor(TypeDescriptor.klass(ClusterClient.class), fr.myprysm.vertx.elasticsearch.ClusterClient.class, TO_JAVA, TO_CEYLON);
  @Ignore private final fr.myprysm.vertx.elasticsearch.ClusterClient delegate;

  public ClusterClient(fr.myprysm.vertx.elasticsearch.ClusterClient delegate) {
    this.delegate = delegate;
  }

  @Ignore 
  public TypeDescriptor $getType$() {
    return $TypeDescriptor$;
  }

  @Ignore
  public Object getDelegate() {
    return delegate;
  }

  @DocAnnotation$annotation$(description = " Asynchronously updates cluster wide specific settings using the Cluster Update Settings API.\n <p>\n See <a href=\"https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-update-settings.html\"> Cluster Update Settings\n API on elastic.co</a>\n")
  @TypeInfo("ceylon.language::Anything")
  public void putSettings(
    final @TypeInfo("fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.admin.cluster::ClusterUpdateSettingsRequest") @Name("request")@DocAnnotation$annotation$(description = "the request\n") fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest request, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.admin.cluster::ClusterUpdateSettingsResponse)") @Name("handler")@DocAnnotation$annotation$(description = "the handler\n") Callable<?> handler) {
    fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest arg_0 = request == null ? null : new fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest(io.vertx.lang.ceylon.ToJava.JsonObject.convert(request.toJson()));
    io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse>(handler) {
      public Object toCeylon(fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse event) {
        return fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.admin.cluster.clusterUpdateSettingsResponse_.get_().getToCeylon().safeConvert(event);
      }
    };
    delegate.putSettings(arg_0, arg_1);
  }

}
