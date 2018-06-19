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

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHandler;
import io.vertx.serviceproxy.ServiceException;
import io.vertx.serviceproxy.ServiceExceptionMessageCodec;

import java.util.*;

/*
  Generated Proxy code - DO NOT EDIT
  @author Roger the Robot
*/
@SuppressWarnings({"unchecked", "rawtypes"})
public class SimpleServiceVertxProxyHandler extends ProxyHandler {

    public static final long DEFAULT_CONNECTION_TIMEOUT = 5 * 60; // 5 minutes

    private final Vertx vertx;
    private final SimpleService service;
    private final long timerID;
    private final long timeoutSeconds;
    private long lastAccessed;

    public SimpleServiceVertxProxyHandler(Vertx vertx, SimpleService service) {
        this(vertx, service, DEFAULT_CONNECTION_TIMEOUT);
    }

    public SimpleServiceVertxProxyHandler(Vertx vertx, SimpleService service, long timeoutInSecond) {
        this(vertx, service, true, timeoutInSecond);
    }

    public SimpleServiceVertxProxyHandler(Vertx vertx, SimpleService service, boolean topLevel, long timeoutSeconds) {
        this.vertx = vertx;
        this.service = service;
        this.timeoutSeconds = timeoutSeconds;
        try {
            this.vertx.eventBus().registerDefaultCodec(ServiceException.class,
                    new ServiceExceptionMessageCodec());
        } catch (IllegalStateException ex) {
        }
        if (timeoutSeconds != -1 && !topLevel) {
            long period = timeoutSeconds * 1000 / 2;
            if (period > 10000) {
                period = 10000;
            }
            this.timerID = vertx.setPeriodic(period, this::checkTimedOut);
        } else {
            this.timerID = -1;
        }
        accessed();
    }

    private void checkTimedOut(long id) {
        long now = System.nanoTime();
        if (now - lastAccessed > timeoutSeconds * 1000000000) {
            close();
        }
    }

    @Override
    public void close() {
        if (timerID != -1) {
            vertx.cancelTimer(timerID);
        }
        super.close();
    }

    private void accessed() {
        this.lastAccessed = System.nanoTime();
    }

    public void handle(Message<JsonObject> msg) {
        try {
            JsonObject json = msg.body();
            String action = msg.headers().get("action");
            if (action == null) {
                throw new IllegalStateException("action not specified");
            }
            accessed();
            switch (action) {
                case "asyncOperation": {
                    service.asyncOperation(createHandler(msg));
                    break;
                }
                default: {
                    throw new IllegalStateException("Invalid action: " + action);
                }
            }
        } catch (Throwable t) {
            msg.reply(new ServiceException(500, t.getMessage()));
            throw t;
        }
    }

    private <T> Handler<AsyncResult<T>> createHandler(Message msg) {
        return res -> {
            if (res.failed()) {
                if (res.cause() instanceof ServiceException) {
                    msg.reply(res.cause());
                } else {
                    msg.reply(new ServiceException(-1, res.cause().getMessage()));
                }
            } else {
                if (res.result() != null && res.result().getClass().isEnum()) {
                    msg.reply(((Enum) res.result()).name());
                } else {
                    msg.reply(res.result());
                }
            }
        };
    }

    private <T> Handler<AsyncResult<List<T>>> createListHandler(Message msg) {
        return res -> {
            if (res.failed()) {
                if (res.cause() instanceof ServiceException) {
                    msg.reply(res.cause());
                } else {
                    msg.reply(new ServiceException(-1, res.cause().getMessage()));
                }
            } else {
                msg.reply(new JsonArray(res.result()));
            }
        };
    }

    private <T> Handler<AsyncResult<Set<T>>> createSetHandler(Message msg) {
        return res -> {
            if (res.failed()) {
                if (res.cause() instanceof ServiceException) {
                    msg.reply(res.cause());
                } else {
                    msg.reply(new ServiceException(-1, res.cause().getMessage()));
                }
            } else {
                msg.reply(new JsonArray(new ArrayList<>(res.result())));
            }
        };
    }

    private Handler<AsyncResult<List<Character>>> createListCharHandler(Message msg) {
        return res -> {
            if (res.failed()) {
                if (res.cause() instanceof ServiceException) {
                    msg.reply(res.cause());
                } else {
                    msg.reply(new ServiceException(-1, res.cause().getMessage()));
                }
            } else {
                JsonArray arr = new JsonArray();
                for (Character chr : res.result()) {
                    arr.add((int) chr);
                }
                msg.reply(arr);
            }
        };
    }

    private Handler<AsyncResult<Set<Character>>> createSetCharHandler(Message msg) {
        return res -> {
            if (res.failed()) {
                if (res.cause() instanceof ServiceException) {
                    msg.reply(res.cause());
                } else {
                    msg.reply(new ServiceException(-1, res.cause().getMessage()));
                }
            } else {
                JsonArray arr = new JsonArray();
                for (Character chr : res.result()) {
                    arr.add((int) chr);
                }
                msg.reply(arr);
            }
        };
    }

    private <T> Map<String, T> convertMap(Map map) {
        return (Map<String, T>) map;
    }

    private <T> List<T> convertList(List list) {
        return (List<T>) list;
    }

    private <T> Set<T> convertSet(List list) {
        return new HashSet<T>((List<T>) list);
    }
}