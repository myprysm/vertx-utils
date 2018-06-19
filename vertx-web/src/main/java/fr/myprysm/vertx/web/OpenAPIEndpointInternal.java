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

import io.vertx.core.Handler;
import io.vertx.reactivex.ext.web.RoutingContext;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Internal interface to avoid to expose the operation.
 */
interface OpenAPIEndpointInternal {

    /**
     * Return the pair to associate an OpenAPI 3.0 operation to a web handler.
     *
     * @return the &lt;operation id, handler&gt; pair for OpenAPI3.0 Vert.x support
     */
    Pair<String, Handler<RoutingContext>> operationIdToHandler();
}
