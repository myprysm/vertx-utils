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

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.service.ServiceVerticleOptions}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.service.ServiceVerticleOptions} original class using Vert.x codegen.
 */
public class ServiceVerticleOptionsConverter {

  public static void fromJson(JsonObject json, ServiceVerticleOptions obj) {
    if (json.getValue("services") instanceof JsonObject) {
      java.util.Map<String, fr.myprysm.vertx.service.ServiceOptions> map = new java.util.LinkedHashMap<>();
      json.getJsonObject("services").forEach(entry -> {
        if (entry.getValue() instanceof JsonObject)
          map.put(entry.getKey(), new fr.myprysm.vertx.service.ServiceOptions((JsonObject)entry.getValue()));
      });
      obj.setServices(map);
    }
  }

  public static void toJson(ServiceVerticleOptions obj, JsonObject json) {
    if (obj.getServices() != null) {
      JsonObject map = new JsonObject();
      obj.getServices().forEach((key,value) -> map.put(key, value.toJson()));
      json.put("services", map);
    }
  }
}