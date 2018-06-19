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
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ServiceException;
import io.vertx.serviceproxy.ServiceExceptionMessageCodec;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
  Generated Proxy code - DO NOT EDIT
  @author Roger the Robot
*/
@SuppressWarnings({"unchecked", "rawtypes"})
public class SimpleServiceVertxEBProxy implements SimpleService {

    private Vertx _vertx;
    private String _address;
    private DeliveryOptions _options;
    private boolean closed;

    public SimpleServiceVertxEBProxy(Vertx vertx, String address) {
        this(vertx, address, null);
    }

    public SimpleServiceVertxEBProxy(Vertx vertx, String address, DeliveryOptions options) {
        this._vertx = vertx;
        this._address = address;
        this._options = options;
        try {
            this._vertx.eventBus().registerDefaultCodec(ServiceException.class,
                    new ServiceExceptionMessageCodec());
        } catch (IllegalStateException ex) {
        }
    }

    @Override
    public SimpleService asyncOperation(Handler<AsyncResult<Void>> handler) {
        if (closed) {
            handler.handle(Future.failedFuture(new IllegalStateException("Proxy is closed")));
            return this;
        }
        JsonObject _json = new JsonObject();
        DeliveryOptions _deliveryOptions = (_options != null) ? new DeliveryOptions(_options) : new DeliveryOptions();
        _deliveryOptions.addHeader("action", "asyncOperation");
        _vertx.eventBus().<Void>send(_address, _json, _deliveryOptions, res -> {
            if (res.failed()) {
                handler.handle(Future.failedFuture(res.cause()));
            } else {
                handler.handle(Future.succeededFuture(res.result().body()));
            }
        });
        return this;
    }


    private List<Character> convertToListChar(JsonArray arr) {
        List<Character> list = new ArrayList<>();
        for (Object obj : arr) {
            Integer jobj = (Integer) obj;
            list.add((char) (int) jobj);
        }
        return list;
    }

    private Set<Character> convertToSetChar(JsonArray arr) {
        Set<Character> set = new HashSet<>();
        for (Object obj : arr) {
            Integer jobj = (Integer) obj;
            set.add((char) (int) jobj);
        }
        return set;
    }

    private <T> Map<String, T> convertMap(Map map) {
        if (map.isEmpty()) {
            return (Map<String, T>) map;
        }

        Object elem = map.values().stream().findFirst().get();
        if (!(elem instanceof Map) && !(elem instanceof List)) {
            return (Map<String, T>) map;
        } else {
            Function<Object, T> converter;
            if (elem instanceof List) {
                converter = object -> (T) new JsonArray((List) object);
            } else {
                converter = object -> (T) new JsonObject((Map) object);
            }
            return ((Map<String, T>) map).entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, converter::apply));
        }
    }

    private <T> List<T> convertList(List list) {
        if (list.isEmpty()) {
            return (List<T>) list;
        }

        Object elem = list.get(0);
        if (!(elem instanceof Map) && !(elem instanceof List)) {
            return (List<T>) list;
        } else {
            Function<Object, T> converter;
            if (elem instanceof List) {
                converter = object -> (T) new JsonArray((List) object);
            } else {
                converter = object -> (T) new JsonObject((Map) object);
            }
            return (List<T>) list.stream().map(converter).collect(Collectors.toList());
        }
    }

    private <T> Set<T> convertSet(List list) {
        return new HashSet<T>(convertList(list));
    }
}