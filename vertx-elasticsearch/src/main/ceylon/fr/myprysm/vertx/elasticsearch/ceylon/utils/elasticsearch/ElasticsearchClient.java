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
import io.vertx.ceylon.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Ceylon(major = 8)
@DocAnnotation$annotation$(description = " Vertx Elasticsearch client.\n <p>\n See Elasticsearch documentation for precise usage (either\n java client\n or\n <a href=\"https://www.elastic.co/guide/en/elasticsearch/client/java-rest/master/\">rest client</a>\n ).\n")
public class ElasticsearchClient implements ReifiedType {

  @Ignore
  public static final io.vertx.lang.ceylon.ConverterFactory<fr.myprysm.vertx.elasticsearch.ElasticsearchClient, ElasticsearchClient> TO_CEYLON = new io.vertx.lang.ceylon.ConverterFactory<fr.myprysm.vertx.elasticsearch.ElasticsearchClient, ElasticsearchClient>() {
    public io.vertx.lang.ceylon.Converter<fr.myprysm.vertx.elasticsearch.ElasticsearchClient, ElasticsearchClient> converter(final TypeDescriptor... descriptors) {
      return new io.vertx.lang.ceylon.Converter<fr.myprysm.vertx.elasticsearch.ElasticsearchClient, ElasticsearchClient>() {
        public ElasticsearchClient convert(fr.myprysm.vertx.elasticsearch.ElasticsearchClient src) {
          return new ElasticsearchClient(src);
        }
      };
    }
  };

  @Ignore
  public static final io.vertx.lang.ceylon.Converter<ElasticsearchClient, fr.myprysm.vertx.elasticsearch.ElasticsearchClient> TO_JAVA = new io.vertx.lang.ceylon.Converter<ElasticsearchClient, fr.myprysm.vertx.elasticsearch.ElasticsearchClient>() {
    public fr.myprysm.vertx.elasticsearch.ElasticsearchClient convert(ElasticsearchClient src) {
      return src.delegate;
    }
  };

  @Ignore public static final TypeDescriptor $TypeDescriptor$ = new io.vertx.lang.ceylon.VertxTypeDescriptor(TypeDescriptor.klass(ElasticsearchClient.class), fr.myprysm.vertx.elasticsearch.ElasticsearchClient.class, TO_JAVA, TO_CEYLON);
  @Ignore private final fr.myprysm.vertx.elasticsearch.ElasticsearchClient delegate;

  public ElasticsearchClient(fr.myprysm.vertx.elasticsearch.ElasticsearchClient delegate) {
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

  @DocAnnotation$annotation$(description = " Close the client.\n")
  @TypeInfo("ceylon.language::Anything")
  public void close() {
    delegate.close();
  }

  @DocAnnotation$annotation$(description = " Provides an [IndicesClient](IndicesClient.type.html) which can be used to access the Indices API.\n <p>\n See <a href=\"https://www.elastic.co/guide/en/elasticsearch/reference/current/indices.html\">Indices API on elastic.co</a>\n")
  @TypeInfo("fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch::IndicesClient")
  public IndicesClient indices() {
    IndicesClient ret = fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.IndicesClient.TO_CEYLON.converter().safeConvert(delegate.indices());
    return ret;
  }

  @DocAnnotation$annotation$(description = " Provides a [ClusterClient](ClusterClient.type.html) which can be used to access the Cluster API.\n <p>\n See <a href=\"https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster.html\">Cluster API on elastic.co</a>\n")
  @TypeInfo("fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch::ClusterClient")
  public ClusterClient cluster() {
    ClusterClient ret = fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.ClusterClient.TO_CEYLON.converter().safeConvert(delegate.cluster());
    return ret;
  }

  @DocAnnotation$annotation$(description = " Asynchronously pings the remote Elasticsearch cluster and returns true if the ping succeeded, false otherwise.\n")
  @TypeInfo("ceylon.language::Anything")
  public void ping(
    final @TypeInfo("fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action::BaseRequest") @Name("request")@DocAnnotation$annotation$(description = "the optional request\n") fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.BaseRequest request, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::Boolean)") @Name("handler")@DocAnnotation$annotation$(description = "the handler\n") Callable<?> handler) {
    fr.myprysm.vertx.elasticsearch.action.BaseRequest arg_0 = request == null ? null : new fr.myprysm.vertx.elasticsearch.action.BaseRequest(io.vertx.lang.ceylon.ToJava.JsonObject.convert(request.toJson()));
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Boolean>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Boolean>(handler) {
      public Object toCeylon(java.lang.Boolean event) {
        return io.vertx.lang.ceylon.ToCeylon.Boolean.safeConvert(event);
      }
    };
    delegate.ping(arg_0, arg_1);
  }

  @DocAnnotation$annotation$(description = " Asynchronously pings the remote Elasticsearch cluster and returns true if the ping succeeded, false otherwise.\n")
  @TypeInfo("ceylon.language::Anything")
  public void ping(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::Boolean)") @Name("handler")@DocAnnotation$annotation$(description = "the handler\n") Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Boolean>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Boolean>(handler) {
      public Object toCeylon(java.lang.Boolean event) {
        return io.vertx.lang.ceylon.ToCeylon.Boolean.safeConvert(event);
      }
    };
    delegate.ping(arg_0);
  }

  @DocAnnotation$annotation$(description = " Asynchronously get the cluster info otherwise provided when sending an HTTP request to port 9200.\n")
  @TypeInfo("ceylon.language::Anything")
  public void info(
    final @TypeInfo("fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action::BaseRequest") @Name("request")@DocAnnotation$annotation$(description = "the optional request\n") fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.BaseRequest request, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.main::MainResponse)") @Name("handler")@DocAnnotation$annotation$(description = "the handler\n") Callable<?> handler) {
    fr.myprysm.vertx.elasticsearch.action.BaseRequest arg_0 = request == null ? null : new fr.myprysm.vertx.elasticsearch.action.BaseRequest(io.vertx.lang.ceylon.ToJava.JsonObject.convert(request.toJson()));
    io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.main.MainResponse>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<fr.myprysm.vertx.elasticsearch.action.main.MainResponse>(handler) {
      public Object toCeylon(fr.myprysm.vertx.elasticsearch.action.main.MainResponse event) {
        return fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.main.mainResponse_.get_().getToCeylon().safeConvert(event);
      }
    };
    delegate.info(arg_0, arg_1);
  }

  @DocAnnotation$annotation$(description = " Asynchronously get the cluster info otherwise provided when sending an HTTP request to port 9200.\n")
  @TypeInfo("ceylon.language::Anything")
  public void info(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.main::MainResponse)") @Name("handler")@DocAnnotation$annotation$(description = "the handler\n") Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.main.MainResponse>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<fr.myprysm.vertx.elasticsearch.action.main.MainResponse>(handler) {
      public Object toCeylon(fr.myprysm.vertx.elasticsearch.action.main.MainResponse event) {
        return fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.main.mainResponse_.get_().getToCeylon().safeConvert(event);
      }
    };
    delegate.info(arg_0);
  }

  @DocAnnotation$annotation$(description = " Asynchronously retrieves a document by id using the Get API.\n <p>\n See <a href=\"https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html\">Get API on elastic.co</a>\n")
  @TypeInfo("ceylon.language::Anything")
  public void get(
    final @TypeInfo("fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.get::GetRequest") @Name("request")@DocAnnotation$annotation$(description = "the request\n") fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.get.GetRequest request, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.get::GetResponse)") @Name("handler")@DocAnnotation$annotation$(description = "the handler\n") Callable<?> handler) {
    fr.myprysm.vertx.elasticsearch.action.get.GetRequest arg_0 = request == null ? null : new fr.myprysm.vertx.elasticsearch.action.get.GetRequest(io.vertx.lang.ceylon.ToJava.JsonObject.convert(request.toJson()));
    io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.get.GetResponse>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<fr.myprysm.vertx.elasticsearch.action.get.GetResponse>(handler) {
      public Object toCeylon(fr.myprysm.vertx.elasticsearch.action.get.GetResponse event) {
        return fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.get.getResponse_.get_().getToCeylon().safeConvert(event);
      }
    };
    delegate.get(arg_0, arg_1);
  }

  @DocAnnotation$annotation$(description = " Asynchronously checks for the existence of a document. Returns true if it exists, false otherwise.\n <p>\n See <a href=\"https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html\">Get API on elastic.co</a>\n")
  @TypeInfo("ceylon.language::Anything")
  public void exists(
    final @TypeInfo("fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.get::GetRequest") @Name("request")@DocAnnotation$annotation$(description = "the request\n") fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.get.GetRequest request, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::Boolean)") @Name("handler")@DocAnnotation$annotation$(description = "the handler\n") Callable<?> handler) {
    fr.myprysm.vertx.elasticsearch.action.get.GetRequest arg_0 = request == null ? null : new fr.myprysm.vertx.elasticsearch.action.get.GetRequest(io.vertx.lang.ceylon.ToJava.JsonObject.convert(request.toJson()));
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Boolean>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Boolean>(handler) {
      public Object toCeylon(java.lang.Boolean event) {
        return io.vertx.lang.ceylon.ToCeylon.Boolean.safeConvert(event);
      }
    };
    delegate.exists(arg_0, arg_1);
  }

  @DocAnnotation$annotation$(description = " Asynchronously retrieves multiple documents by id using the Multi Get API.\n <p>\n See <a href=\"https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-multi-get.html\">Multi Get API on elastic.co</a>\n")
  @TypeInfo("ceylon.language::Anything")
  public void multiGet(
    final @TypeInfo("fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.get::MultiGetRequest") @Name("request")@DocAnnotation$annotation$(description = "the request\n") fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.get.MultiGetRequest request, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.get::MultiGetResponse)") @Name("handler")@DocAnnotation$annotation$(description = "the handler\n") Callable<?> handler) {
    fr.myprysm.vertx.elasticsearch.action.get.MultiGetRequest arg_0 = request == null ? null : new fr.myprysm.vertx.elasticsearch.action.get.MultiGetRequest(io.vertx.lang.ceylon.ToJava.JsonObject.convert(request.toJson()));
    io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.get.MultiGetResponse>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<fr.myprysm.vertx.elasticsearch.action.get.MultiGetResponse>(handler) {
      public Object toCeylon(fr.myprysm.vertx.elasticsearch.action.get.MultiGetResponse event) {
        return fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.get.multiGetResponse_.get_().getToCeylon().safeConvert(event);
      }
    };
    delegate.multiGet(arg_0, arg_1);
  }

  @DocAnnotation$annotation$(description = " Asynchronously index a document using the Index API.\n <p>\n See <a href=\"https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-index_.html\">Index API on elastic.co</a>\n")
  @TypeInfo("ceylon.language::Anything")
  public void index(
    final @TypeInfo("fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.index::IndexRequest") @Name("request")@DocAnnotation$annotation$(description = "the request\n") fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.index.IndexRequest request, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.index::IndexResponse)") @Name("handler")@DocAnnotation$annotation$(description = "the handler\n") Callable<?> handler) {
    fr.myprysm.vertx.elasticsearch.action.index.IndexRequest arg_0 = request == null ? null : new fr.myprysm.vertx.elasticsearch.action.index.IndexRequest(io.vertx.lang.ceylon.ToJava.JsonObject.convert(request.toJson()));
    io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.index.IndexResponse>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<fr.myprysm.vertx.elasticsearch.action.index.IndexResponse>(handler) {
      public Object toCeylon(fr.myprysm.vertx.elasticsearch.action.index.IndexResponse event) {
        return fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.index.indexResponse_.get_().getToCeylon().safeConvert(event);
      }
    };
    delegate.index(arg_0, arg_1);
  }

  @DocAnnotation$annotation$(description = " Asynchronously updates a document using the Update API.\n <p>\n See <a href=\"https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update.html\">Update API on elastic.co</a>\n")
  @TypeInfo("ceylon.language::Anything")
  public void update(
    final @TypeInfo("fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.update::UpdateRequest") @Name("request")@DocAnnotation$annotation$(description = "the request\n") fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.update.UpdateRequest request, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.update::UpdateResponse)") @Name("handler")@DocAnnotation$annotation$(description = "the handler\n") Callable<?> handler) {
    fr.myprysm.vertx.elasticsearch.action.update.UpdateRequest arg_0 = request == null ? null : new fr.myprysm.vertx.elasticsearch.action.update.UpdateRequest(io.vertx.lang.ceylon.ToJava.JsonObject.convert(request.toJson()));
    io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.update.UpdateResponse>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<fr.myprysm.vertx.elasticsearch.action.update.UpdateResponse>(handler) {
      public Object toCeylon(fr.myprysm.vertx.elasticsearch.action.update.UpdateResponse event) {
        return fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.update.updateResponse_.get_().getToCeylon().safeConvert(event);
      }
    };
    delegate.update(arg_0, arg_1);
  }

  @DocAnnotation$annotation$(description = " Asynchronously deletes a document by id using the Delete API.\n <p>\n See <a href=\"https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-delete.html\">Delete API on elastic.co</a>\n")
  @TypeInfo("ceylon.language::Anything")
  public void delete(
    final @TypeInfo("fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.delete::DeleteRequest") @Name("request")@DocAnnotation$annotation$(description = "the request\n") fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.delete.DeleteRequest request, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.delete::DeleteResponse)") @Name("handler")@DocAnnotation$annotation$(description = "the handler\n") Callable<?> handler) {
    fr.myprysm.vertx.elasticsearch.action.delete.DeleteRequest arg_0 = request == null ? null : new fr.myprysm.vertx.elasticsearch.action.delete.DeleteRequest(io.vertx.lang.ceylon.ToJava.JsonObject.convert(request.toJson()));
    io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.delete.DeleteResponse>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<fr.myprysm.vertx.elasticsearch.action.delete.DeleteResponse>(handler) {
      public Object toCeylon(fr.myprysm.vertx.elasticsearch.action.delete.DeleteResponse event) {
        return fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.delete.deleteResponse_.get_().getToCeylon().safeConvert(event);
      }
    };
    delegate.delete(arg_0, arg_1);
  }

  @DocAnnotation$annotation$(description = " Asynchronously executes a bulk request using the Bulk API\n\n See <a href=\"https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-bulk.html\">Bulk API on elastic.co</a>\n")
  @TypeInfo("ceylon.language::Anything")
  public void bulk(
    final @TypeInfo("fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.bulk::BulkRequest") @Name("request") fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.bulk.BulkRequest request, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.bulk::BulkResponse)") @Name("handler") Callable<?> handler) {
    fr.myprysm.vertx.elasticsearch.action.bulk.BulkRequest arg_0 = request == null ? null : new fr.myprysm.vertx.elasticsearch.action.bulk.BulkRequest(io.vertx.lang.ceylon.ToJava.JsonObject.convert(request.toJson()));
    io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponse>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponse>(handler) {
      public Object toCeylon(fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponse event) {
        return fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.bulk.bulkResponse_.get_().getToCeylon().safeConvert(event);
      }
    };
    delegate.bulk(arg_0, arg_1);
  }

}
