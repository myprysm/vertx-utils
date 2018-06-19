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
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.reactivex.core.Vertx;
import io.vertx.serviceproxy.ServiceException;

/**
 * Abstract service that can be automatically instanciated by the {@link ServiceVerticle}.
 */
public class AbstractService implements Service {
    private Vertx vertx;

    public AbstractService() {

    }

    @Override
    public Vertx vertx() {
        return vertx;
    }

    @Override
    public void init(Vertx vertx) {
        this.vertx = vertx;
    }

    /**
     * Registers a codec for a specific exception if not already registered.
     * <p>
     * Fails silently.
     *
     * @param exceptionClass the exception class
     * @param codec          the codec for the exception
     * @param <E>            the exception type
     */
    protected <E extends ServiceException> void tryRegisterCodec(Class<E> exceptionClass, MessageCodec<E, E> codec) {
        try {
            vertx.eventBus().getDelegate().registerDefaultCodec(exceptionClass, codec);
        } catch (IllegalStateException exc) {
            // Registration fails silently if already done.
        }
    }


    @Override
    public Completable configure() {
        return Completable.complete();
    }

    @Override
    public Completable close() {
        return Completable.complete();
    }

    /**
     * Throws a "not implemented|900" ServiceException to the caller handler.
     *
     * @param handler the handler
     * @param <T>     the handler accepted type
     */
    protected <T> void notImplemented(Handler<AsyncResult<T>> handler) {
        handler.handle(ServiceException.fail(900, "Method not implemented"));
    }
}
