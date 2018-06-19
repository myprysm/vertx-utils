/*
 * Copyright 2018 the original author or the original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.myprysm.vertx.core.reactivex.config;

import java.util.Map;
import io.reactivex.Observable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import fr.myprysm.vertx.core.VerticleOptions;
import io.vertx.core.json.JsonArray;
import java.util.List;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/**
 * Configuration Service that provides all the configuration items for the application
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.core.config.ConfigService original} non RX-ified interface using Vert.x codegen.
 */

@io.vertx.lang.reactivex.RxGen(fr.myprysm.vertx.core.config.ConfigService.class)
public class ConfigService {

  @Override
  public String toString() {
    return delegate.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ConfigService that = (ConfigService) o;
    return delegate.equals(that.delegate);
  }
  
  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  public static final io.vertx.lang.reactivex.TypeArg<ConfigService> __TYPE_ARG = new io.vertx.lang.reactivex.TypeArg<>(
    obj -> new ConfigService((fr.myprysm.vertx.core.config.ConfigService) obj),
    ConfigService::getDelegate
  );

  private final fr.myprysm.vertx.core.config.ConfigService delegate;
  
  public ConfigService(fr.myprysm.vertx.core.config.ConfigService delegate) {
    this.delegate = delegate;
  }

  public fr.myprysm.vertx.core.config.ConfigService getDelegate() {
    return delegate;
  }

  /**
   * Get the configured verticles as a <code>JsonArray</code>.
   * @param handler the result handler
   * @return this
   */
  public ConfigService getVerticles(Handler<AsyncResult<List<VerticleOptions>>> handler) { 
    delegate.getVerticles(handler);
    return this;
  }

  /**
   * Get the configured verticles as a <code>JsonArray</code>.
   * @return 
   */
  public Single<List<VerticleOptions>> rxGetVerticles() { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<List<VerticleOptions>>(handler -> {
      getVerticles(handler);
    });
  }

  /**
   * Get the whole configuration as a <code>JsonObject</code>.
   * @param handler the result handler
   * @return this
   */
  public ConfigService getConfig(Handler<AsyncResult<JsonObject>> handler) { 
    delegate.getConfig(handler);
    return this;
  }

  /**
   * Get the whole configuration as a <code>JsonObject</code>.
   * @return 
   */
  public Single<JsonObject> rxGetConfig() { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<JsonObject>(handler -> {
      getConfig(handler);
    });
  }

  /**
   * Get the configuration item identified by the <code>key</code> as a <code>String</code>.
   * @param key the key to search
   * @param handler the result handler
   * @return this
   */
  public ConfigService getString(String key, Handler<AsyncResult<String>> handler) { 
    delegate.getString(key, handler);
    return this;
  }

  /**
   * Get the configuration item identified by the <code>key</code> as a <code>String</code>.
   * @param key the key to search
   * @return 
   */
  public Single<String> rxGetString(String key) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<String>(handler -> {
      getString(key, handler);
    });
  }

  /**
   * Get the configuration item identified by the <code>key</code> as a <code>Boolean</code>.
   * @param key the key to search
   * @param handler the result handler
   * @return this
   */
  public ConfigService getBoolean(String key, Handler<AsyncResult<Boolean>> handler) { 
    delegate.getBoolean(key, handler);
    return this;
  }

  /**
   * Get the configuration item identified by the <code>key</code> as a <code>Boolean</code>.
   * @param key the key to search
   * @return 
   */
  public Single<Boolean> rxGetBoolean(String key) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<Boolean>(handler -> {
      getBoolean(key, handler);
    });
  }

  /**
   * Get the configuration item identified by the <code>key</code> as an <code>Integer</code>.
   * @param key the key to search
   * @param handler the result handler
   * @return this
   */
  public ConfigService getInteger(String key, Handler<AsyncResult<Integer>> handler) { 
    delegate.getInteger(key, handler);
    return this;
  }

  /**
   * Get the configuration item identified by the <code>key</code> as an <code>Integer</code>.
   * @param key the key to search
   * @return 
   */
  public Single<Integer> rxGetInteger(String key) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<Integer>(handler -> {
      getInteger(key, handler);
    });
  }

  /**
   * Get the configuration item identified by the <code>key</code> as a <code>Long</code>.
   * @param key the key to search
   * @param handler the result handler
   * @return this
   */
  public ConfigService getLong(String key, Handler<AsyncResult<Long>> handler) { 
    delegate.getLong(key, handler);
    return this;
  }

  /**
   * Get the configuration item identified by the <code>key</code> as a <code>Long</code>.
   * @param key the key to search
   * @return 
   */
  public Single<Long> rxGetLong(String key) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<Long>(handler -> {
      getLong(key, handler);
    });
  }

  /**
   * Get the configuration item identified by the <code>key</code> as a <code>Float</code>.
   * @param key the key to search
   * @param handler the result handler
   * @return this
   */
  public ConfigService getFloat(String key, Handler<AsyncResult<Float>> handler) { 
    delegate.getFloat(key, handler);
    return this;
  }

  /**
   * Get the configuration item identified by the <code>key</code> as a <code>Float</code>.
   * @param key the key to search
   * @return 
   */
  public Single<Float> rxGetFloat(String key) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<Float>(handler -> {
      getFloat(key, handler);
    });
  }

  /**
   * Get the configuration item identified by the <code>key</code> as a <code>Double</code>.
   * @param key the key to search
   * @param handler the result handler
   * @return this
   */
  public ConfigService getDouble(String key, Handler<AsyncResult<Double>> handler) { 
    delegate.getDouble(key, handler);
    return this;
  }

  /**
   * Get the configuration item identified by the <code>key</code> as a <code>Double</code>.
   * @param key the key to search
   * @return 
   */
  public Single<Double> rxGetDouble(String key) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<Double>(handler -> {
      getDouble(key, handler);
    });
  }

  /**
   * Get the configuration item identified by the <code>key</code> as a <code>JsonArray</code>.
   * @param key the key to search
   * @param handler the result handler
   * @return this
   */
  public ConfigService getArray(String key, Handler<AsyncResult<JsonArray>> handler) { 
    delegate.getArray(key, handler);
    return this;
  }

  /**
   * Get the configuration item identified by the <code>key</code> as a <code>JsonArray</code>.
   * @param key the key to search
   * @return 
   */
  public Single<JsonArray> rxGetArray(String key) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<JsonArray>(handler -> {
      getArray(key, handler);
    });
  }

  /**
   * Get the configuration item identified by the <code>key</code> as a <code>JsonObject</code>.
   * @param key the key to search
   * @param handler the result handler
   * @return this
   */
  public ConfigService getObject(String key, Handler<AsyncResult<JsonObject>> handler) { 
    delegate.getObject(key, handler);
    return this;
  }

  /**
   * Get the configuration item identified by the <code>key</code> as a <code>JsonObject</code>.
   * @param key the key to search
   * @return 
   */
  public Single<JsonObject> rxGetObject(String key) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<JsonObject>(handler -> {
      getObject(key, handler);
    });
  }


  public static  ConfigService newInstance(fr.myprysm.vertx.core.config.ConfigService arg) {
    return arg != null ? new ConfigService(arg) : null;
  }
}
