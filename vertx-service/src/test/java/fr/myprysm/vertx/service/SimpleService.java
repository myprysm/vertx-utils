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

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

@VertxGen
@ProxyGen
public interface SimpleService {

    @GenIgnore
    static SimpleService createSuccess(Vertx vertx, Handler<AsyncResult<SimpleService>> handler) {
        SimpleSuccessServiceImpl service = new SimpleSuccessServiceImpl();
        handler.handle(Future.succeededFuture(service));
        return service;
    }

    @GenIgnore
    static SimpleService createFailure(Vertx vertx, Handler<AsyncResult<SimpleService>> handler) {
        SimpleFailureServiceImpl service = new SimpleFailureServiceImpl();
        handler.handle(Future.succeededFuture(service));
        return service;
    }

    @GenIgnore
    static String someMethod(Vertx vertx) {
        return "hello, world!";
    }

    @Fluent
    SimpleService asyncOperation(Handler<AsyncResult<Void>> handler);
}
