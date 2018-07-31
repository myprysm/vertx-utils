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
import fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest;
import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse;

/**
 * Vertx Elasticsearch cluster client.
 * <p>
 * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster.html">Cluster API on elastic.co</a>
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.ClusterClient original} non RX-ified interface using Vert.x codegen.
 */

@io.vertx.lang.reactivex.RxGen(fr.myprysm.vertx.elasticsearch.ClusterClient.class)
public class ClusterClient {

  @Override
  public String toString() {
    return delegate.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ClusterClient that = (ClusterClient) o;
    return delegate.equals(that.delegate);
  }
  
  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  public static final io.vertx.lang.reactivex.TypeArg<ClusterClient> __TYPE_ARG = new io.vertx.lang.reactivex.TypeArg<>(
    obj -> new ClusterClient((fr.myprysm.vertx.elasticsearch.ClusterClient) obj),
    ClusterClient::getDelegate
  );

  private final fr.myprysm.vertx.elasticsearch.ClusterClient delegate;
  
  public ClusterClient(fr.myprysm.vertx.elasticsearch.ClusterClient delegate) {
    this.delegate = delegate;
  }

  public fr.myprysm.vertx.elasticsearch.ClusterClient getDelegate() {
    return delegate;
  }

  /**
   * Asynchronously updates cluster wide specific settings using the Cluster Update Settings API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-update-settings.html"> Cluster Update Settings
   * API on elastic.co</a>
   * @param request the request
   * @param handler the handler
   */
  public void putSettings(ClusterUpdateSettingsRequest request, Handler<AsyncResult<ClusterUpdateSettingsResponse>> handler) { 
    delegate.putSettings(request, handler);
  }

  /**
   * Asynchronously updates cluster wide specific settings using the Cluster Update Settings API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-update-settings.html"> Cluster Update Settings
   * API on elastic.co</a>
   * @param request the request
   * @return 
   */
  public Single<ClusterUpdateSettingsResponse> rxPutSettings(ClusterUpdateSettingsRequest request) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<ClusterUpdateSettingsResponse>(handler -> {
      putSettings(request, handler);
    });
  }

  /**
   * Asynchronously get cluster wide specific settings using the Cluster Update Settings API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-update-settings.html"> Cluster Update Settings
   * API on elastic.co</a>
   * @param request the request
   * @param handler the handler
   */
  public void getSettings(BaseRequest request, Handler<AsyncResult<ClusterUpdateSettingsResponse>> handler) { 
    delegate.getSettings(request, handler);
  }

  /**
   * Asynchronously get cluster wide specific settings using the Cluster Update Settings API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-update-settings.html"> Cluster Update Settings
   * API on elastic.co</a>
   * @param request the request
   * @return 
   */
  public Single<ClusterUpdateSettingsResponse> rxGetSettings(BaseRequest request) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<ClusterUpdateSettingsResponse>(handler -> {
      getSettings(request, handler);
    });
  }

  /**
   * Asynchronously get cluster wide specific settings using the Cluster Update Settings API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-update-settings.html"> Cluster Update Settings
   * API on elastic.co</a>
   * @param handler the handler
   */
  public void getSettings(Handler<AsyncResult<ClusterUpdateSettingsResponse>> handler) { 
    delegate.getSettings(handler);
  }

  /**
   * Asynchronously get cluster wide specific settings using the Cluster Update Settings API.
   * <p>
   * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-update-settings.html"> Cluster Update Settings
   * API on elastic.co</a>
   * @return 
   */
  public Single<ClusterUpdateSettingsResponse> rxGetSettings() { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<ClusterUpdateSettingsResponse>(handler -> {
      getSettings(handler);
    });
  }


  public static  ClusterClient newInstance(fr.myprysm.vertx.elasticsearch.ClusterClient arg) {
    return arg != null ? new ClusterClient(arg) : null;
  }
}
