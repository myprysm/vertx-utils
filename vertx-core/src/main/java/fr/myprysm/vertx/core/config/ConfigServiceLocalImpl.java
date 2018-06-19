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
import fr.myprysm.vertx.json.JsonHelpers;
import fr.myprysm.vertx.validation.ValidationResult;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.config.ConfigRetriever;
import io.vertx.reactivex.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

import static fr.myprysm.vertx.json.JsonHelpers.extractObject;

@Slf4j
public class ConfigServiceLocalImpl implements ConfigService {
    public static final String VERTICLES = "verticles";
    public static final String VERTICLES_PREFIX = VERTICLES + ".";

    public static final String CONFIGURATION = "configuration";
    public static final String CONFIGURATION_PREFIX = CONFIGURATION + ".";

    private static final Pattern BOOLEAN_PATTERN = Pattern.compile("^(true|false|yes|no|on|off|y|n|t|f)$", Pattern.CASE_INSENSITIVE);
    private final String path;
    private final Vertx vertx;
    private List<VerticleOptions> verticles;
    private JsonObject config;

    public ConfigServiceLocalImpl(String path, Vertx vertx) {
        this.path = path;
        this.vertx = vertx;
    }

    public Completable configure() {
        log.info("Configuring local configuration service");
        try {
            vertx.eventBus().getDelegate().registerDefaultCodec(ConfigServiceException.class, new ConfigServiceExceptionMessageCodec());
        } catch (IllegalStateException exc) {
            // Registration fails silently if already done.
        }

        ConfigStoreOptions store = new ConfigStoreOptions()
                .setType("file")
                .setFormat("yaml")
                .setConfig(new JsonObject()
                        .put("path", path)
                );

        ConfigRetriever retriever = ConfigRetriever.create(vertx, new ConfigRetrieverOptions()
                .addStore(store)
                .setIncludeDefaultStores(true)
        );

        return retriever.rxGetConfig()
                .flatMapCompletable(this::loadConfig);
    }


    private Completable loadConfig(JsonObject config) {
        if (log.isDebugEnabled()) {
            log.debug("Loaded configuration: ");
            config.fieldNames().stream().sorted().forEach(field -> log.debug("[{}]: {}", field, config.getValue(field)));
        }

        return Completable.concatArray(
                prepareVerticles(config.getJsonObject(VERTICLES), this.extractEnvConfig(config, VERTICLES_PREFIX)),
                prepareConfig(config.getJsonObject(CONFIGURATION, new JsonObject()), this.extractEnvConfig(config, CONFIGURATION_PREFIX))
        );
    }

    /**
     * Merges configuration with environment variables
     *
     * @param config the configuration from file
     * @param env    the configuration from environment
     * @return the action
     */
    private Completable prepareConfig(JsonObject config, JsonObject env) {
        return Completable.fromAction(() -> {
            this.config = config;
            this.config = this.config.mergeIn(env, true);
        });
    }

    /**
     * Merges verticle conf with environment variables
     * and validate each individual verticle property.
     *
     * @param verticles the verticles as a named map
     * @param env       the verticle configuration from environment
     * @return the action
     */
    private Completable prepareVerticles(JsonObject verticles, JsonObject env) {
        return Observable.fromIterable(verticles.mergeIn(env, true).fieldNames())
                // Set the name of the verticle from the key for logging purpose
                .map(name -> verticles.getJsonObject(name).copy().put("name", name))
                .flatMap(this::validateVerticle)
                .reduce(new ArrayList<>(), this::prepareVerticleOptions)
                .doOnSuccess(this::setVerticles)
                .toCompletable();
    }

    private void setVerticles(List<VerticleOptions> verticles) {
        this.verticles = verticles;
    }

    private ArrayList<VerticleOptions> prepareVerticleOptions(ArrayList<VerticleOptions> acc, JsonObject json) {
        acc.add(new VerticleOptions(json));
        return acc;
    }

    /**
     * Extract environment & system properties in both forms "configuration.key.some.path"
     * and "configuration_key_some_path" where "configuration." is the prefix.
     * Keeps both name references for a proper extraction
     *
     * @param config the configuration
     * @param prefix the prefix to filter
     * @return the environment configuration extracted
     */
    private JsonObject extractEnvConfig(JsonObject config, String prefix) {
        JsonObject json = new JsonObject();
        config.fieldNames().stream()
                //
                .map(s -> Pair.of(s, s))
                .map(tuple -> Pair.of(tuple.getKey(), tuple.getValue().replaceAll("_", "."))) // .toLowerCase()
                .filter(tuple -> tuple.getValue().startsWith(prefix))
                .map(tuple -> Pair.of(tuple.getValue().substring(prefix.length()), parsePrimitive(config.getValue(tuple.getKey()))))
                .forEach(param -> JsonHelpers.writeObject(json, param.getKey(), param.getValue()));

        if (log.isDebugEnabled()) {
            log.debug("Extracted environment: {}", json.encodePrettily());
        }

        return json;
    }

    private Object parsePrimitive(Object value) {
        if (value != null) {
            if (value instanceof String) {
                String str = (String) value;
                if (BOOLEAN_PATTERN.matcher(str).find()) {
                    return BooleanUtils.toBooleanObject(str);
                }
                return str;
            }
        }
        return value;
    }

    @Override
    public ConfigService getVerticles(Handler<AsyncResult<List<VerticleOptions>>> handler) {
        handler.handle(Future.succeededFuture(verticles));
        return this;
    }

    private Observable<JsonObject> validateVerticle(JsonObject verticle) {
        ValidationResult validation = VerticleOptions.validate(verticle);
        if (!validation.isValid()) {
            log.error("Verticle [{}] is not valid. Message: {}.", verticle.getString("name"), validation.getReason());
            if (log.isDebugEnabled()) {
                log.debug("Data: {}", verticle);
            }
            return Observable.error(validation.toException());
        }
        return Observable.just(verticle);
    }

    @Override
    public ConfigService getConfig(Handler<AsyncResult<JsonObject>> handler) {
        handler.handle(Future.succeededFuture(config));
        return this;
    }

    @Override
    public ConfigService getString(String key, Handler<AsyncResult<String>> handler) {
        handler.handle(getConfig(key, val -> val instanceof String ? (String) val : val.toString()));
        return this;
    }

    @Override
    public ConfigService getBoolean(String key, Handler<AsyncResult<Boolean>> handler) {
        handler.handle(getConfig(key, val -> {
            if (val instanceof Boolean) {
                return (Boolean) val;
            } else if (val instanceof Number) {
                return BooleanUtils.toBoolean(((Number) val).intValue());
            } else if (val instanceof String) {
                Boolean bool = BooleanUtils.toBooleanObject((String) val);
                if (bool != null) {
                    return bool;
                }
            }

            throw new ConfigServiceException(
                    ConfigServiceException.CONFIG_TYPE_MISMATCH,
                    key + " is not a boolean (" + val.getClass().getName() + ")"
            );
        }));
        return this;
    }

    @Override
    public ConfigService getInteger(String key, Handler<AsyncResult<Integer>> handler) {
        handler.handle(getConfig(key, extractNumber(key, Number::intValue)));
        return this;
    }

    @Override
    public ConfigService getLong(String key, Handler<AsyncResult<Long>> handler) {
        handler.handle(getConfig(key, extractNumber(key, Number::longValue)));
        return this;
    }

    @Override
    public ConfigService getFloat(String key, Handler<AsyncResult<Float>> handler) {
        handler.handle(getConfig(key, extractNumber(key, Number::floatValue)));
        return this;
    }

    @Override
    public ConfigService getDouble(String key, Handler<AsyncResult<Double>> handler) {
        handler.handle(getConfig(key, extractNumber(key, Number::doubleValue)));
        return this;
    }

    private <T extends Number> Function<Object, T> extractNumber(String key, Function<Number, T> extractor) {
        return val -> {
            if (val instanceof Number) {
                return extractor.apply((Number) val);
            }

            throw new ConfigServiceException(
                    ConfigServiceException.CONFIG_TYPE_MISMATCH,
                    key + " is not a number (" + val.getClass().getName() + ")"
            );
        };
    }

    @Override
    public ConfigService getArray(String key, Handler<AsyncResult<JsonArray>> handler) {
        handler.handle(getConfig(key, JsonArray.class));
        return this;
    }

    @Override
    public ConfigService getObject(String key, Handler<AsyncResult<JsonObject>> handler) {
        handler.handle(getConfig(key, JsonObject.class));
        return this;
    }

    private <T> AsyncResult<T> getConfig(String key, Function<Object, T> extractor) {
        try {
            return Future.succeededFuture(
                    extractObject(config, key)
                            .map(extractor)
                            .orElseThrow(() -> new ConfigServiceException(ConfigServiceException.CONFIG_NOT_FOUND, "Could not find " + key))
            );
        } catch (ConfigServiceException exc) {
            return Future.failedFuture(exc);
        }

    }

    private <T> AsyncResult<T> getConfig(String key, Class<T> clazz) {
        try {
            return Future.succeededFuture(
                    extractObject(config, key)
                            .map(clazz::cast)
                            .orElseThrow(() -> new ConfigServiceException(ConfigServiceException.CONFIG_NOT_FOUND, "Could not find " + key))
            );
        } catch (ConfigServiceException exc) {
            return Future.failedFuture(exc);
        } catch (ClassCastException exc) {
            return Future.failedFuture(new ConfigServiceException(ConfigServiceException.CONFIG_TYPE_MISMATCH, exc.getMessage()));
        }

    }

}