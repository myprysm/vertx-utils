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

package fr.myprysm.vertx.core.config;

import fr.myprysm.vertx.core.VerticleOptions;
import fr.myprysm.vertx.core.utils.Utils;
import io.reactivex.Single;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.SingleHelper;

import java.util.List;

/**
 * Configuration Service that provides all the configuration items for the application
 */
@ProxyGen
@VertxGen
public interface ConfigService {
    /**
     * Generate a random address to avoid some ConfigService collisions in cluster
     */
    String ADDRESS = "fr.myprysm.vertx.config-service:" + Utils.instanceId;

    /**
     * Create a new local configuration service
     *
     * @param vertx   the current vertx instance
     * @param path    the monitoringPath to load configuration as a YAML file
     * @param handler the result handler
     * @return the new configuration service (not yet initialized
     */
    @GenIgnore
    static ConfigService create(Vertx vertx, String path, Handler<AsyncResult<ConfigService>> handler) {
        ConfigServiceLocalImpl service = new ConfigServiceLocalImpl(path, new io.vertx.reactivex.core.Vertx(vertx));
        service.configure()
                .andThen(Single.just(service))
                .subscribe(SingleHelper.toObserver(handler));

        return service;
    }

    /**
     * Create a new local Rx configuration service
     *
     * @param vertx   the current vertx instance
     * @param path    the monitoringPath to load configuration as a YAML file
     * @param handler the result handler
     * @return the new configuration service (not yet initialized
     */
    @GenIgnore
    static fr.myprysm.vertx.core.reactivex.config.ConfigService createRx(Vertx vertx, String path, Handler<AsyncResult<ConfigService>> handler) {
        return new fr.myprysm.vertx.core.reactivex.config.ConfigService(ConfigService.create(vertx, path, handler));
    }

    /**
     * Create a new proxy configuration service
     *
     * @param vertx the current vertx instance
     * @return the new configuration service proxy
     */
    @GenIgnore
    static ConfigService createProxy(Vertx vertx) {
        return new ConfigServiceVertxEBProxy(vertx, ADDRESS);
    }

    /**
     * Create a new Rx proxy configuration service
     *
     * @param vertx the current vertx instance
     * @return the new configuration service proxy
     */
    @GenIgnore
    static fr.myprysm.vertx.core.reactivex.config.ConfigService createRxProxy(Vertx vertx) {
        return new fr.myprysm.vertx.core.reactivex.config.ConfigService(ConfigService.createProxy(vertx));
    }

    /**
     * Get the configured verticles as a <code>JsonArray</code>.
     *
     * @param handler the result handler
     * @return this
     */
    @Fluent
    ConfigService getVerticles(Handler<AsyncResult<List<VerticleOptions>>> handler);

    /**
     * Get the whole configuration as a <code>JsonObject</code>.
     *
     * @param handler the result handler
     * @return this
     */
    @Fluent
    ConfigService getConfig(Handler<AsyncResult<JsonObject>> handler);

    /**
     * Get the configuration item identified by the <code>key</code> as a <code>String</code>.
     *
     * @param key     the key to search
     * @param handler the result handler
     * @return this
     */
    @Fluent
    ConfigService getString(String key, Handler<AsyncResult<String>> handler);

    /**
     * Get the configuration item identified by the <code>key</code> as a <code>Boolean</code>.
     *
     * @param key     the key to search
     * @param handler the result handler
     * @return this
     */
    @Fluent
    ConfigService getBoolean(String key, Handler<AsyncResult<Boolean>> handler);

    /**
     * Get the configuration item identified by the <code>key</code> as an <code>Integer</code>.
     *
     * @param key     the key to search
     * @param handler the result handler
     * @return this
     */
    @Fluent
    ConfigService getInteger(String key, Handler<AsyncResult<Integer>> handler);

    /**
     * Get the configuration item identified by the <code>key</code> as a <code>Long</code>.
     *
     * @param key     the key to search
     * @param handler the result handler
     * @return this
     */
    @Fluent
    ConfigService getLong(String key, Handler<AsyncResult<Long>> handler);

    /**
     * Get the configuration item identified by the <code>key</code> as a <code>Float</code>.
     *
     * @param key     the key to search
     * @param handler the result handler
     * @return this
     */
    @Fluent
    ConfigService getFloat(String key, Handler<AsyncResult<Float>> handler);

    /**
     * Get the configuration item identified by the <code>key</code> as a <code>Double</code>.
     *
     * @param key     the key to search
     * @param handler the result handler
     * @return this
     */
    @Fluent
    ConfigService getDouble(String key, Handler<AsyncResult<Double>> handler);

    /**
     * Get the configuration item identified by the <code>key</code> as a <code>JsonArray</code>.
     *
     * @param key     the key to search
     * @param handler the result handler
     * @return this
     */
    @Fluent
    ConfigService getArray(String key, Handler<AsyncResult<JsonArray>> handler);

    /**
     * Get the configuration item identified by the <code>key</code> as a <code>JsonObject</code>.
     *
     * @param key     the key to search
     * @param handler the result handler
     * @return this
     */
    @Fluent
    ConfigService getObject(String key, Handler<AsyncResult<JsonObject>> handler);
}
