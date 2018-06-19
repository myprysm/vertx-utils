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
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.ext.healthchecks.Status;

public class SimpleSuccessServiceImpl extends AbstractService implements SimpleService, HealthCheck {
    @Override
    public Completable configure() {
        tryRegisterCodec(SimpleServiceException.class, new SimpleServiceExceptionMessageCodec());
        tryRegisterCodec(SimpleServiceException.class, new SimpleServiceExceptionMessageCodec());
        return super.configure();
    }

    @Override
    public SimpleService asyncOperation(Handler<AsyncResult<Void>> handler) {
        handler.handle(Future.succeededFuture());
        return this;
    }

    @Override
    public String name() {
        return "simple-success-service";
    }

    @Override
    public Handler<io.vertx.reactivex.core.Future<Status>> beat() {
        return status -> status.complete(Status.OK());
    }
}
