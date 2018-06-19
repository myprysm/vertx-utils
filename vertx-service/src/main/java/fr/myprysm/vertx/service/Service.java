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

import io.reactivex.Completable;
import io.vertx.reactivex.core.Vertx;

/**
 * Represents an instanciable service for {@link ServiceVerticle}.
 * <p>
 * Provides {@link #configure()} and {@link #close()} hooks for startup/shutdown.
 */
public interface Service {

    /**
     * The vertx instance.
     *
     * @return the vertx instance
     */
    Vertx vertx();

    /**
     * Initialize the service with the current vertx instance.
     * <p>
     * This is automatically called during {@link Service} startup.
     * <p>
     * Please do not call it by yourself.
     *
     * @param vertx the current Vert.x instance
     */
    void init(Vertx vertx);

    /**
     * Configuration hook for service startup.
     * <p>
     * Indicates its completion.
     *
     * @return a completable that finished when the service is fully configured
     */
    Completable configure();

    /**
     * Close hook for service shutdown.
     * <p>
     * Indicates its completion.
     *
     * @return a completable that finished when the service is fully closed
     */
    Completable close();

}
