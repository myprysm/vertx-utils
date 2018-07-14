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
@DocAnnotation$annotation$(description = " Vertx Elasticsearch indices client.\n <p>\n See <a href=\"https://www.elastic.co/guide/en/elasticsearch/reference/current/indices.html\">Indices API on elastic.co</a>\n")
public class IndicesClient implements ReifiedType {

  @Ignore
  public static final io.vertx.lang.ceylon.ConverterFactory<fr.myprysm.vertx.elasticsearch.IndicesClient, IndicesClient> TO_CEYLON = new io.vertx.lang.ceylon.ConverterFactory<fr.myprysm.vertx.elasticsearch.IndicesClient, IndicesClient>() {
    public io.vertx.lang.ceylon.Converter<fr.myprysm.vertx.elasticsearch.IndicesClient, IndicesClient> converter(final TypeDescriptor... descriptors) {
      return new io.vertx.lang.ceylon.Converter<fr.myprysm.vertx.elasticsearch.IndicesClient, IndicesClient>() {
        public IndicesClient convert(fr.myprysm.vertx.elasticsearch.IndicesClient src) {
          return new IndicesClient(src);
        }
      };
    }
  };

  @Ignore
  public static final io.vertx.lang.ceylon.Converter<IndicesClient, fr.myprysm.vertx.elasticsearch.IndicesClient> TO_JAVA = new io.vertx.lang.ceylon.Converter<IndicesClient, fr.myprysm.vertx.elasticsearch.IndicesClient>() {
    public fr.myprysm.vertx.elasticsearch.IndicesClient convert(IndicesClient src) {
      return src.delegate;
    }
  };

  @Ignore public static final TypeDescriptor $TypeDescriptor$ = new io.vertx.lang.ceylon.VertxTypeDescriptor(TypeDescriptor.klass(IndicesClient.class), fr.myprysm.vertx.elasticsearch.IndicesClient.class, TO_JAVA, TO_CEYLON);
  @Ignore private final fr.myprysm.vertx.elasticsearch.IndicesClient delegate;

  public IndicesClient(fr.myprysm.vertx.elasticsearch.IndicesClient delegate) {
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

  @DocAnnotation$annotation$(description = " Asynchronously deletes an index using the Delete Index API.\n <p>\n See <a href=\"https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-delete-index.html\">\n Delete Index API on elastic.co</a>\n")
  @TypeInfo("ceylon.language::Anything")
  public void delete(
    final @TypeInfo("fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.admin.indices.delete::DeleteIndexRequest") @Name("request")@DocAnnotation$annotation$(description = "the request\n") fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.admin.indices.delete.DeleteIndexRequest request, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.admin.indices.delete::DeleteIndexResponse)") @Name("handler")@DocAnnotation$annotation$(description = "the handler\n") Callable<?> handler) {
    fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexRequest arg_0 = request == null ? null : new fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexRequest(io.vertx.lang.ceylon.ToJava.JsonObject.convert(request.toJson()));
    io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexResponse>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexResponse>(handler) {
      public Object toCeylon(fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexResponse event) {
        return fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.admin.indices.delete.deleteIndexResponse_.get_().getToCeylon().safeConvert(event);
      }
    };
    delegate.delete(arg_0, arg_1);
  }

  @DocAnnotation$annotation$(description = " Asynchronously checks if one or more aliases exist using the Aliases Exist API.\n <p>\n See <a href=\"https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-aliases.html\">\n Indices Aliases API on elastic.co</a>\n")
  @TypeInfo("ceylon.language::Anything")
  public void exists(
    final @TypeInfo("fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.admin.indices.get::GetIndexRequest") @Name("request")@DocAnnotation$annotation$(description = "the request\n") fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.admin.indices.get.GetIndexRequest request, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::Boolean)") @Name("handler")@DocAnnotation$annotation$(description = "the handler\n") Callable<?> handler) {
    fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest arg_0 = request == null ? null : new fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest(io.vertx.lang.ceylon.ToJava.JsonObject.convert(request.toJson()));
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Boolean>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Boolean>(handler) {
      public Object toCeylon(java.lang.Boolean event) {
        return io.vertx.lang.ceylon.ToCeylon.Boolean.safeConvert(event);
      }
    };
    delegate.exists(arg_0, arg_1);
  }

}
