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

package fr.myprysm.vertx.core.utils;

import fr.myprysm.vertx.core.reactivex.config.ConfigService;
import io.vertx.reactivex.core.Vertx;

import java.util.UUID;

/**
 * Utility class to quickly access commonly used resources such as DB clients, Web clients, ...
 * <p>
 * Provides classic vertx helpers as well as Rx helpers.
 */
public final class Utils {
    public static final String instanceId = UUID.randomUUID().toString();

    private Utils() {

    }

    /**
     * Get a proxy to the configuration service
     *
     * @param vertx the current vertx instance
     * @return a proxy to the configuration service
     */
    public static ConfigService configServiceProxy(Vertx vertx) {
        return fr.myprysm.vertx.core.config.ConfigService.createRxProxy(vertx.getDelegate());
    }

}
