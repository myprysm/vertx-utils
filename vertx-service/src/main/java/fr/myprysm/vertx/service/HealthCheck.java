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

package fr.myprysm.vertx.service;

import io.vertx.core.Handler;
import io.vertx.ext.healthchecks.Status;
import io.vertx.reactivex.core.Future;

/**
 * Indicates that the implementation provides
 * a health check that can be exposed.
 */
public interface HealthCheck {

    /**
     * The name of the healthcheck
     *
     * @return the name of the healthcheck
     */
    String name();

    /**
     * Beat invoked whenever a health check is requested on the item.
     *
     * @return the handler for the health check
     */
    Handler<Future<Status>> beat();
}
