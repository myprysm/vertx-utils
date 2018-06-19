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
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.Router;

/**
 * Marks a class as a web <code>Endpoint</code>.
 */
public interface Endpoint {

    /**
     * Initialise the endpoint with the Vert.x instance and the context.
     * <p>
     * This method is called by when the instance is deployed. You do not call it yourself.
     *
     * @param vertx the Vert.x instance
     */
    void init(Vertx vertx);

    /**
     * Method used for initialization and routing registration.
     * <p>
     * You can prepare your endpoint dependencies in this method to ensure that your requests will be handled.
     *
     * @param router the parent router
     * @return a completable that finished when registration is completed.
     */
    Completable register(Router router);

    /**
     * Unregisters the endpoint, when needed.
     *
     * @return a completable that finished when unregistration is complete
     */
    default Completable unregister() {
        return Completable.complete();
    }
}
