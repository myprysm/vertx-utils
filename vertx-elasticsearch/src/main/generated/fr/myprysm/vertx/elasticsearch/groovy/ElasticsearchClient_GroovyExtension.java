package fr.myprysm.vertx.elasticsearch.groovy;
public class ElasticsearchClient_GroovyExtension {
  public static void ping(fr.myprysm.vertx.elasticsearch.ElasticsearchClient j_receiver, java.util.Map<String, Object> request, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Boolean>> handler) {
    j_receiver.ping(request != null ? new fr.myprysm.vertx.elasticsearch.action.BaseRequest(io.vertx.core.impl.ConversionHelper.toJsonObject(request)) : null,
      handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Boolean>>() {
      public void handle(io.vertx.core.AsyncResult<java.lang.Boolean> ar) {
        handler.handle(ar.map(event -> event));
      }
    } : null);
  }
  public static void info(fr.myprysm.vertx.elasticsearch.ElasticsearchClient j_receiver, java.util.Map<String, Object> request, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.Map<String, Object>>> handler) {
    j_receiver.info(request != null ? new fr.myprysm.vertx.elasticsearch.action.BaseRequest(io.vertx.core.impl.ConversionHelper.toJsonObject(request)) : null,
      handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.main.MainResponse>>() {
      public void handle(io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.main.MainResponse> ar) {
        handler.handle(ar.map(event -> event != null ? io.vertx.core.impl.ConversionHelper.fromJsonObject(event.toJson()) : null));
      }
    } : null);
  }
  public static void info(fr.myprysm.vertx.elasticsearch.ElasticsearchClient j_receiver, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.Map<String, Object>>> handler) {
    j_receiver.info(handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.main.MainResponse>>() {
      public void handle(io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.main.MainResponse> ar) {
        handler.handle(ar.map(event -> event != null ? io.vertx.core.impl.ConversionHelper.fromJsonObject(event.toJson()) : null));
      }
    } : null);
  }
  public static void get(fr.myprysm.vertx.elasticsearch.ElasticsearchClient j_receiver, java.util.Map<String, Object> request, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.Map<String, Object>>> handler) {
    j_receiver.get(request != null ? new fr.myprysm.vertx.elasticsearch.action.get.GetRequest(io.vertx.core.impl.ConversionHelper.toJsonObject(request)) : null,
      handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.get.GetResponse>>() {
      public void handle(io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.get.GetResponse> ar) {
        handler.handle(ar.map(event -> event != null ? io.vertx.core.impl.ConversionHelper.fromJsonObject(event.toJson()) : null));
      }
    } : null);
  }
  public static void exists(fr.myprysm.vertx.elasticsearch.ElasticsearchClient j_receiver, java.util.Map<String, Object> request, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Boolean>> handler) {
    j_receiver.exists(request != null ? new fr.myprysm.vertx.elasticsearch.action.get.GetRequest(io.vertx.core.impl.ConversionHelper.toJsonObject(request)) : null,
      handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Boolean>>() {
      public void handle(io.vertx.core.AsyncResult<java.lang.Boolean> ar) {
        handler.handle(ar.map(event -> event));
      }
    } : null);
  }
  public static void multiGet(fr.myprysm.vertx.elasticsearch.ElasticsearchClient j_receiver, java.util.Map<String, Object> request, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.Map<String, Object>>> handler) {
    j_receiver.multiGet(request != null ? new fr.myprysm.vertx.elasticsearch.action.get.MultiGetRequest(io.vertx.core.impl.ConversionHelper.toJsonObject(request)) : null,
      handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.get.MultiGetResponse>>() {
      public void handle(io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.get.MultiGetResponse> ar) {
        handler.handle(ar.map(event -> event != null ? io.vertx.core.impl.ConversionHelper.fromJsonObject(event.toJson()) : null));
      }
    } : null);
  }
  public static void index(fr.myprysm.vertx.elasticsearch.ElasticsearchClient j_receiver, java.util.Map<String, Object> request, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.Map<String, Object>>> handler) {
    j_receiver.index(request != null ? new fr.myprysm.vertx.elasticsearch.action.index.IndexRequest(io.vertx.core.impl.ConversionHelper.toJsonObject(request)) : null,
      handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.index.IndexResponse>>() {
      public void handle(io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.index.IndexResponse> ar) {
        handler.handle(ar.map(event -> event != null ? io.vertx.core.impl.ConversionHelper.fromJsonObject(event.toJson()) : null));
      }
    } : null);
  }
  public static void update(fr.myprysm.vertx.elasticsearch.ElasticsearchClient j_receiver, java.util.Map<String, Object> request, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.Map<String, Object>>> handler) {
    j_receiver.update(request != null ? new fr.myprysm.vertx.elasticsearch.action.update.UpdateRequest(io.vertx.core.impl.ConversionHelper.toJsonObject(request)) : null,
      handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.update.UpdateResponse>>() {
      public void handle(io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.update.UpdateResponse> ar) {
        handler.handle(ar.map(event -> event != null ? io.vertx.core.impl.ConversionHelper.fromJsonObject(event.toJson()) : null));
      }
    } : null);
  }
  public static void delete(fr.myprysm.vertx.elasticsearch.ElasticsearchClient j_receiver, java.util.Map<String, Object> request, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.Map<String, Object>>> handler) {
    j_receiver.delete(request != null ? new fr.myprysm.vertx.elasticsearch.action.delete.DeleteRequest(io.vertx.core.impl.ConversionHelper.toJsonObject(request)) : null,
      handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.delete.DeleteResponse>>() {
      public void handle(io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.delete.DeleteResponse> ar) {
        handler.handle(ar.map(event -> event != null ? io.vertx.core.impl.ConversionHelper.fromJsonObject(event.toJson()) : null));
      }
    } : null);
  }
  public static void search(fr.myprysm.vertx.elasticsearch.ElasticsearchClient j_receiver, java.util.Map<String, Object> request, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.Map<String, Object>>> handler) {
    j_receiver.search(request != null ? new fr.myprysm.vertx.elasticsearch.action.search.SearchRequest(io.vertx.core.impl.ConversionHelper.toJsonObject(request)) : null,
      handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.search.SearchResponse>>() {
      public void handle(io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.search.SearchResponse> ar) {
        handler.handle(ar.map(event -> event != null ? io.vertx.core.impl.ConversionHelper.fromJsonObject(event.toJson()) : null));
      }
    } : null);
  }
  public static void multiSearch(fr.myprysm.vertx.elasticsearch.ElasticsearchClient j_receiver, java.util.Map<String, Object> request, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.Map<String, Object>>> handler) {
    j_receiver.multiSearch(request != null ? new fr.myprysm.vertx.elasticsearch.action.search.MultiSearchRequest(io.vertx.core.impl.ConversionHelper.toJsonObject(request)) : null,
      handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponse>>() {
      public void handle(io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponse> ar) {
        handler.handle(ar.map(event -> event != null ? io.vertx.core.impl.ConversionHelper.fromJsonObject(event.toJson()) : null));
      }
    } : null);
  }
  public static void searchScroll(fr.myprysm.vertx.elasticsearch.ElasticsearchClient j_receiver, java.util.Map<String, Object> request, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.Map<String, Object>>> handler) {
    j_receiver.searchScroll(request != null ? new fr.myprysm.vertx.elasticsearch.action.search.SearchScrollRequest(io.vertx.core.impl.ConversionHelper.toJsonObject(request)) : null,
      handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.search.SearchResponse>>() {
      public void handle(io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.search.SearchResponse> ar) {
        handler.handle(ar.map(event -> event != null ? io.vertx.core.impl.ConversionHelper.fromJsonObject(event.toJson()) : null));
      }
    } : null);
  }
  public static void clearScroll(fr.myprysm.vertx.elasticsearch.ElasticsearchClient j_receiver, java.util.Map<String, Object> request, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.Map<String, Object>>> handler) {
    j_receiver.clearScroll(request != null ? new fr.myprysm.vertx.elasticsearch.action.search.ClearScrollRequest(io.vertx.core.impl.ConversionHelper.toJsonObject(request)) : null,
      handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.search.ClearScrollResponse>>() {
      public void handle(io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.search.ClearScrollResponse> ar) {
        handler.handle(ar.map(event -> event != null ? io.vertx.core.impl.ConversionHelper.fromJsonObject(event.toJson()) : null));
      }
    } : null);
  }
  public static void bulk(fr.myprysm.vertx.elasticsearch.ElasticsearchClient j_receiver, java.util.Map<String, Object> request, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.Map<String, Object>>> handler) {
    j_receiver.bulk(request != null ? new fr.myprysm.vertx.elasticsearch.action.bulk.BulkRequest(io.vertx.core.impl.ConversionHelper.toJsonObject(request)) : null,
      handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponse>>() {
      public void handle(io.vertx.core.AsyncResult<fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponse> ar) {
        handler.handle(ar.map(event -> event != null ? io.vertx.core.impl.ConversionHelper.fromJsonObject(event.toJson()) : null));
      }
    } : null);
  }
}
