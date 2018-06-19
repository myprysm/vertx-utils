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

package fr.myprysm.vertx.web;

import fr.myprysm.vertx.core.reactivex.config.ConfigService;
import fr.myprysm.vertx.core.utils.Utils;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.RoutingContext;

/**
 * Basic {@link Endpoint} that does its registration by itself.
 */
public abstract class AbstractEndpoint implements Endpoint {

    private Vertx vertx;
    private ConfigService configService;

    @Override
    public void init(Vertx vertx) {
        this.vertx = vertx;
        this.configService = Utils.configServiceProxy(vertx);
    }

    /**
     * A proxy to the {@link ConfigService}
     *
     * @return the proxy
     */
    protected ConfigService configService() {
        return configService;
    }

    /**
     * The current Vert.x instance
     *
     * @return the instance
     */
    protected Vertx vertx() {
        return vertx;
    }

    /**
     * Set response type as json on the current event request
     *
     * @param event the request
     */
    protected void setContentJson(RoutingContext event) {
        event.response()
                .putHeader("content-type", "application/json")
                .putHeader("content-encoding", "UTF-8");
    }
}
