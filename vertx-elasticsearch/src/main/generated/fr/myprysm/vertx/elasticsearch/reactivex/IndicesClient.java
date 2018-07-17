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
import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.refresh.RefreshRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.refresh.RefreshResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;

/**
 * Vertx Elasticsearch indices client.
 * <p>
 * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/indices.html">Indices API on elastic.co</a>
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.IndicesClient original} non RX-ified interface using Vert.x codegen.
 */

@io.vertx.lang.reactivex.RxGen(fr.myprysm.vertx.elasticsearch.IndicesClient.class)
public class IndicesClient {

  @Override
  public String toString() {
    return delegate.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IndicesClient that = (IndicesClient) o;
    return delegate.equals(that.delegate);
  }
  
  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  public static final io.vertx.lang.reactivex.TypeArg<IndicesClient> __TYPE_ARG = new io.vertx.lang.reactivex.TypeArg<>(
    obj -> new IndicesClient((fr.myprysm.vertx.elasticsearch.IndicesClient) obj),
    IndicesClient::getDelegate
  );

  private final fr.myprysm.vertx.elasticsearch.IndicesClient delegate;
  
  public IndicesClient(fr.myprysm.vertx.elasticsearch.IndicesClient delegate) {
    this.delegate = delegate;
  }

  public fr.myprysm.vertx.elasticsearch.IndicesClient getDelegate() {
    return delegate;
  }

  /**
   * Asynchronously deletes an index using the Delete Index API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-delete-index.html">
   * Delete Index API on elastic.co</a>
   * @param request the request
   * @param handler the handler
   */
  public void delete(DeleteIndexRequest request, Handler<AsyncResult<DeleteIndexResponse>> handler) { 
    delegate.delete(request, handler);
  }

  /**
   * Asynchronously deletes an index using the Delete Index API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-delete-index.html">
   * Delete Index API on elastic.co</a>
   * @param request the request
   * @return 
   */
  public Single<DeleteIndexResponse> rxDelete(DeleteIndexRequest request) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<DeleteIndexResponse>(handler -> {
      delete(request, handler);
    });
  }

  public void create(CreateIndexRequest request, Handler<AsyncResult<CreateIndexResponse>> handler) { 
    delegate.create(request, handler);
  }

  public Single<CreateIndexResponse> rxCreate(CreateIndexRequest request) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<CreateIndexResponse>(handler -> {
      create(request, handler);
    });
  }

  public void putMapping(PutMappingRequest request, Handler<AsyncResult<PutMappingResponse>> handler) { 
    delegate.putMapping(request, handler);
  }

  public Single<PutMappingResponse> rxPutMapping(PutMappingRequest request) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<PutMappingResponse>(handler -> {
      putMapping(request, handler);
    });
  }

  public void refresh(RefreshRequest request, Handler<AsyncResult<RefreshResponse>> handler) { 
    delegate.refresh(request, handler);
  }

  public Single<RefreshResponse> rxRefresh(RefreshRequest request) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<RefreshResponse>(handler -> {
      refresh(request, handler);
    });
  }

  /**
   * Asynchronously checks if one or more aliases exist using the Aliases Exist API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-aliases.html">
   * Indices Aliases API on elastic.co</a>
   * @param request the request
   * @param handler the handler
   */
  public void exists(GetIndexRequest request, Handler<AsyncResult<Boolean>> handler) { 
    delegate.exists(request, handler);
  }

  /**
   * Asynchronously checks if one or more aliases exist using the Aliases Exist API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-aliases.html">
   * Indices Aliases API on elastic.co</a>
   * @param request the request
   * @return 
   */
  public Single<Boolean> rxExists(GetIndexRequest request) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<Boolean>(handler -> {
      exists(request, handler);
    });
  }


  public static  IndicesClient newInstance(fr.myprysm.vertx.elasticsearch.IndicesClient arg) {
    return arg != null ? new IndicesClient(arg) : null;
  }
}
