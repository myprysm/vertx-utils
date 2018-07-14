/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package fr.myprysm.vertx.elasticsearch.reactivex;

import java.util.Map;
import io.reactivex.Observable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import fr.myprysm.vertx.elasticsearch.action.index.IndexRequest;
import fr.myprysm.vertx.elasticsearch.action.get.MultiGetResponse;
import fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponse;
import fr.myprysm.vertx.elasticsearch.action.get.GetRequest;
import io.vertx.reactivex.core.Vertx;
import fr.myprysm.vertx.elasticsearch.action.delete.DeleteResponse;
import fr.myprysm.vertx.elasticsearch.action.update.UpdateResponse;
import fr.myprysm.vertx.elasticsearch.action.update.UpdateRequest;
import fr.myprysm.vertx.elasticsearch.action.bulk.BulkRequest;
import fr.myprysm.vertx.elasticsearch.action.get.GetResponse;
import fr.myprysm.vertx.elasticsearch.action.get.MultiGetRequest;
import fr.myprysm.vertx.elasticsearch.action.delete.DeleteRequest;
import fr.myprysm.vertx.elasticsearch.action.main.MainResponse;
import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import fr.myprysm.vertx.elasticsearch.action.index.IndexResponse;

/**
 * Vertx Elasticsearch client.
 * <p>
 * See Elasticsearch documentation for precise usage (either
 * java client
 * or
 * <a href="https://www.elastic.co/guide/en/elasticsearch/client/java-rest/master/">rest client</a>
 * ).
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.ElasticsearchClient original} non RX-ified interface using Vert.x codegen.
 */

@io.vertx.lang.reactivex.RxGen(fr.myprysm.vertx.elasticsearch.ElasticsearchClient.class)
public class ElasticsearchClient {

  @Override
  public String toString() {
    return delegate.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ElasticsearchClient that = (ElasticsearchClient) o;
    return delegate.equals(that.delegate);
  }
  
  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  public static final io.vertx.lang.reactivex.TypeArg<ElasticsearchClient> __TYPE_ARG = new io.vertx.lang.reactivex.TypeArg<>(
    obj -> new ElasticsearchClient((fr.myprysm.vertx.elasticsearch.ElasticsearchClient) obj),
    ElasticsearchClient::getDelegate
  );

  private final fr.myprysm.vertx.elasticsearch.ElasticsearchClient delegate;
  
  public ElasticsearchClient(fr.myprysm.vertx.elasticsearch.ElasticsearchClient delegate) {
    this.delegate = delegate;
  }

  public fr.myprysm.vertx.elasticsearch.ElasticsearchClient getDelegate() {
    return delegate;
  }

  /**
   * Close the client.
   */
  public void close() { 
    delegate.close();
  }

  /**
   * Provides an {@link fr.myprysm.vertx.elasticsearch.reactivex.IndicesClient} which can be used to access the Indices API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/indices.html">Indices API on elastic.co</a>
   * @return the indices client
   */
  public IndicesClient indices() { 
    IndicesClient ret = IndicesClient.newInstance(delegate.indices());
    return ret;
  }

  /**
   * Provides a {@link fr.myprysm.vertx.elasticsearch.reactivex.ClusterClient} which can be used to access the Cluster API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster.html">Cluster API on elastic.co</a>
   * @return the cluster client
   */
  public ClusterClient cluster() { 
    ClusterClient ret = ClusterClient.newInstance(delegate.cluster());
    return ret;
  }

  /**
   * Asynchronously pings the remote Elasticsearch cluster and returns true if the ping succeeded, false otherwise.
   * @param request the optional request
   * @param handler the handler
   */
  public void ping(BaseRequest request, Handler<AsyncResult<Boolean>> handler) { 
    delegate.ping(request, handler);
  }

  /**
   * Asynchronously pings the remote Elasticsearch cluster and returns true if the ping succeeded, false otherwise.
   * @param request the optional request
   * @return 
   */
  public Single<Boolean> rxPing(BaseRequest request) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<Boolean>(handler -> {
      ping(request, handler);
    });
  }

  /**
   * Asynchronously pings the remote Elasticsearch cluster and returns true if the ping succeeded, false otherwise.
   * @param handler the handler
   */
  public void ping(Handler<AsyncResult<Boolean>> handler) { 
    delegate.ping(handler);
  }

  /**
   * Asynchronously pings the remote Elasticsearch cluster and returns true if the ping succeeded, false otherwise.
   * @return 
   */
  public Single<Boolean> rxPing() { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<Boolean>(handler -> {
      ping(handler);
    });
  }

  /**
   * Asynchronously get the cluster info otherwise provided when sending an HTTP request to port 9200.
   * @param request the optional request
   * @param handler the handler
   */
  public void info(BaseRequest request, Handler<AsyncResult<MainResponse>> handler) { 
    delegate.info(request, handler);
  }

  /**
   * Asynchronously get the cluster info otherwise provided when sending an HTTP request to port 9200.
   * @param request the optional request
   * @return 
   */
  public Single<MainResponse> rxInfo(BaseRequest request) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<MainResponse>(handler -> {
      info(request, handler);
    });
  }

  /**
   * Asynchronously get the cluster info otherwise provided when sending an HTTP request to port 9200.
   * @param handler the handler
   */
  public void info(Handler<AsyncResult<MainResponse>> handler) { 
    delegate.info(handler);
  }

  /**
   * Asynchronously get the cluster info otherwise provided when sending an HTTP request to port 9200.
   * @return 
   */
  public Single<MainResponse> rxInfo() { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<MainResponse>(handler -> {
      info(handler);
    });
  }

  /**
   * Asynchronously retrieves a document by id using the Get API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html">Get API on elastic.co</a>
   * @param request the request
   * @param handler the handler
   */
  public void get(GetRequest request, Handler<AsyncResult<GetResponse>> handler) { 
    delegate.get(request, handler);
  }

  /**
   * Asynchronously retrieves a document by id using the Get API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html">Get API on elastic.co</a>
   * @param request the request
   * @return 
   */
  public Single<GetResponse> rxGet(GetRequest request) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<GetResponse>(handler -> {
      get(request, handler);
    });
  }

  /**
   * Asynchronously checks for the existence of a document. Returns true if it exists, false otherwise.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html">Get API on elastic.co</a>
   * @param request the request
   * @param handler the handler
   */
  public void exists(GetRequest request, Handler<AsyncResult<Boolean>> handler) { 
    delegate.exists(request, handler);
  }

  /**
   * Asynchronously checks for the existence of a document. Returns true if it exists, false otherwise.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html">Get API on elastic.co</a>
   * @param request the request
   * @return 
   */
  public Single<Boolean> rxExists(GetRequest request) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<Boolean>(handler -> {
      exists(request, handler);
    });
  }

  /**
   * Asynchronously retrieves multiple documents by id using the Multi Get API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-multi-get.html">Multi Get API on elastic.co</a>
   * @param request the request
   * @param handler the handler
   */
  public void multiGet(MultiGetRequest request, Handler<AsyncResult<MultiGetResponse>> handler) { 
    delegate.multiGet(request, handler);
  }

  /**
   * Asynchronously retrieves multiple documents by id using the Multi Get API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-multi-get.html">Multi Get API on elastic.co</a>
   * @param request the request
   * @return 
   */
  public Single<MultiGetResponse> rxMultiGet(MultiGetRequest request) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<MultiGetResponse>(handler -> {
      multiGet(request, handler);
    });
  }

  /**
   * Asynchronously index a document using the Index API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-index_.html">Index API on elastic.co</a>
   * @param request the request
   * @param handler the handler
   */
  public void index(IndexRequest request, Handler<AsyncResult<IndexResponse>> handler) { 
    delegate.index(request, handler);
  }

  /**
   * Asynchronously index a document using the Index API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-index_.html">Index API on elastic.co</a>
   * @param request the request
   * @return 
   */
  public Single<IndexResponse> rxIndex(IndexRequest request) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<IndexResponse>(handler -> {
      index(request, handler);
    });
  }

  /**
   * Asynchronously updates a document using the Update API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update.html">Update API on elastic.co</a>
   * @param request the request
   * @param handler the handler
   */
  public void update(UpdateRequest request, Handler<AsyncResult<UpdateResponse>> handler) { 
    delegate.update(request, handler);
  }

  /**
   * Asynchronously updates a document using the Update API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update.html">Update API on elastic.co</a>
   * @param request the request
   * @return 
   */
  public Single<UpdateResponse> rxUpdate(UpdateRequest request) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<UpdateResponse>(handler -> {
      update(request, handler);
    });
  }

  /**
   * Asynchronously deletes a document by id using the Delete API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-delete.html">Delete API on elastic.co</a>
   * @param request the request
   * @param handler the handler
   */
  public void delete(DeleteRequest request, Handler<AsyncResult<DeleteResponse>> handler) { 
    delegate.delete(request, handler);
  }

  /**
   * Asynchronously deletes a document by id using the Delete API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-delete.html">Delete API on elastic.co</a>
   * @param request the request
   * @return 
   */
  public Single<DeleteResponse> rxDelete(DeleteRequest request) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<DeleteResponse>(handler -> {
      delete(request, handler);
    });
  }

  /**
   * Asynchronously executes a bulk request using the Bulk API
   *
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-bulk.html">Bulk API on elastic.co</a>
   * @param request 
   * @param handler 
   */
  public void bulk(BulkRequest request, Handler<AsyncResult<BulkResponse>> handler) { 
    delegate.bulk(request, handler);
  }

  /**
   * Asynchronously executes a bulk request using the Bulk API
   *
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-bulk.html">Bulk API on elastic.co</a>
   * @param request 
   * @return 
   */
  public Single<BulkResponse> rxBulk(BulkRequest request) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<BulkResponse>(handler -> {
      bulk(request, handler);
    });
  }

  /**
   * Create an ElasticSearch client which maintains its own data source.
   * @param vertx the Vert.x instance
   * @param config the configuration
   * @return the client
   */
  public static ElasticsearchClient createNonShared(Vertx vertx, JsonObject config) { 
    ElasticsearchClient ret = ElasticsearchClient.newInstance(fr.myprysm.vertx.elasticsearch.ElasticsearchClient.createNonShared(vertx.getDelegate(), config));
    return ret;
  }

  /**
   * Like {@link fr.myprysm.vertx.elasticsearch.reactivex.ElasticsearchClient#createShared} but with the default data source name.
   * @param vertx the Vert.x instance
   * @param config the configuration
   * @return the client
   */
  public static ElasticsearchClient createShared(Vertx vertx, JsonObject config) { 
    ElasticsearchClient ret = ElasticsearchClient.newInstance(fr.myprysm.vertx.elasticsearch.ElasticsearchClient.createShared(vertx.getDelegate(), config));
    return ret;
  }

  /**
   * Create an ElasticSearch client which shares its data source with any other Mongo clients created with the same
   * client name.
   * @param vertx the Vert.x instance
   * @param config the configuration
   * @param clientName the client name
   * @return the client
   */
  public static ElasticsearchClient createShared(Vertx vertx, JsonObject config, String clientName) { 
    ElasticsearchClient ret = ElasticsearchClient.newInstance(fr.myprysm.vertx.elasticsearch.ElasticsearchClient.createShared(vertx.getDelegate(), config, clientName));
    return ret;
  }


  public static  ElasticsearchClient newInstance(fr.myprysm.vertx.elasticsearch.ElasticsearchClient arg) {
    return arg != null ? new ElasticsearchClient(arg) : null;
  }
}
