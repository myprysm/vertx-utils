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

import io.reactivex.Completable;
import io.vertx.core.Handler;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import org.apache.commons.lang3.tuple.Pair;

/**
 * OpenAPI 3.0 Vert.x endpoint.
 * <p>
 * Classic {@link Endpoint} registration is automatically completed when requested as no route can be bound.
 * <p>
 * Provides the mapping from the OpenAPI 3.0 operation ID to the request {@link Handler}
 */
public abstract class AbstractOpenAPIEndpoint extends AbstractEndpoint implements OpenAPIEndpoint, OpenAPIEndpointInternal {
    @Override
    public Completable register(Router router) {
        return Completable.complete();
    }

    @Override
    public Completable configure() {
        return Completable.complete();
    }

    @Override
    public Pair<String, Handler<RoutingContext>> operationIdToHandler() {
        return Pair.of(operationId(), this);
    }

}
