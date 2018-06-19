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

package fr.myprysm.vertx.core.test;

import fr.myprysm.vertx.core.config.ConfigService;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.VertxTestContext;
import io.vertx.serviceproxy.ServiceBinder;

import java.util.function.BiConsumer;

/**
 * Class intended for test purpose only.
 * This allows to load a configuration file and start a config service instance.
 */
public interface ConfigTestHelper {

    String DEFAULT_CONFIG = "config/test-config-service.yml";

    /**
     * Loads a config service with the default configuration file ("test-config.yml").
     *
     * @param vertx    vertx instance
     * @param ctx      vertx test context instance
     * @param consumer the consumer that will receive the service instance and the message consumer for the proxies
     */
    static void load(Vertx vertx, VertxTestContext ctx, BiConsumer<ConfigService, MessageConsumer<JsonObject>> consumer) {
        load(vertx, ctx, DEFAULT_CONFIG, consumer);
    }

    static void load(Vertx vertx, VertxTestContext ctx, String configFile, String address, BiConsumer<ConfigService, MessageConsumer<JsonObject>> consumer) {
        ConfigService.create(vertx, configFile, ctx.succeeding(service -> consumer.accept(service, new ServiceBinder(vertx).setAddress(address).register(ConfigService.class, service))));
    }

    static void load(Vertx vertx, VertxTestContext ctx, String configFile, BiConsumer<ConfigService, MessageConsumer<JsonObject>> consumer) {
        load(vertx, ctx, configFile, ConfigService.ADDRESS, consumer);
    }
}
