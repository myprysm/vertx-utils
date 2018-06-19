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

package fr.myprysm.vertx.core.groovy.config;
public class ConfigService_GroovyExtension {
  public static fr.myprysm.vertx.core.config.ConfigService getVerticles(fr.myprysm.vertx.core.config.ConfigService j_receiver, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.List<java.util.Map<String, Object>>>> handler) {
    io.vertx.core.impl.ConversionHelper.fromObject(j_receiver.getVerticles(handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.List<fr.myprysm.vertx.core.VerticleOptions>>>() {
      public void handle(io.vertx.core.AsyncResult<java.util.List<fr.myprysm.vertx.core.VerticleOptions>> ar) {
        handler.handle(ar.map(event -> event != null ? event.stream().map(elt -> elt != null ? io.vertx.core.impl.ConversionHelper.fromJsonObject(elt.toJson()) : null).collect(java.util.stream.Collectors.toList()) : null));
      }
    } : null));
    return j_receiver;
  }
  public static fr.myprysm.vertx.core.config.ConfigService getConfig(fr.myprysm.vertx.core.config.ConfigService j_receiver, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.Map<String, Object>>> handler) {
    io.vertx.core.impl.ConversionHelper.fromObject(j_receiver.getConfig(handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.json.JsonObject>>() {
      public void handle(io.vertx.core.AsyncResult<io.vertx.core.json.JsonObject> ar) {
        handler.handle(ar.map(event -> io.vertx.core.impl.ConversionHelper.fromJsonObject(event)));
      }
    } : null));
    return j_receiver;
  }
  public static fr.myprysm.vertx.core.config.ConfigService getArray(fr.myprysm.vertx.core.config.ConfigService j_receiver, java.lang.String key, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.List<Object>>> handler) {
    io.vertx.core.impl.ConversionHelper.fromObject(j_receiver.getArray(key,
      handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.json.JsonArray>>() {
      public void handle(io.vertx.core.AsyncResult<io.vertx.core.json.JsonArray> ar) {
        handler.handle(ar.map(event -> io.vertx.core.impl.ConversionHelper.fromJsonArray(event)));
      }
    } : null));
    return j_receiver;
  }
  public static fr.myprysm.vertx.core.config.ConfigService getObject(fr.myprysm.vertx.core.config.ConfigService j_receiver, java.lang.String key, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.Map<String, Object>>> handler) {
    io.vertx.core.impl.ConversionHelper.fromObject(j_receiver.getObject(key,
      handler != null ? new io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.json.JsonObject>>() {
      public void handle(io.vertx.core.AsyncResult<io.vertx.core.json.JsonObject> ar) {
        handler.handle(ar.map(event -> io.vertx.core.impl.ConversionHelper.fromJsonObject(event)));
      }
    } : null));
    return j_receiver;
  }
}
