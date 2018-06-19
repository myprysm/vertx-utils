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
import io.vertx.reactivex.ext.web.RoutingContext;

/**
 * Public interface describing an OpenAPI 3.0 Endpoint.
 * <p>
 * It must provide an operation ID to bind to the specs and can be configurable.
 */
interface OpenAPIEndpoint extends Handler<RoutingContext>, Endpoint {

    /**
     * Get the OpenAPI 3.0 operation id to map the handler to the path
     *
     * @return the operationId matching one in the specs
     */
    String operationId();

    /**
     * Triggered when component is instanciated.
     * <p>
     * Allows to setup/configure all the services the endpoint may need for its lifecycle.
     * <p>
     * Use it in combination with {@link #unregister()} to tear down the endpoint.
     *
     * @return complete when the endpoint is configured.
     */
    Completable configure();
}
