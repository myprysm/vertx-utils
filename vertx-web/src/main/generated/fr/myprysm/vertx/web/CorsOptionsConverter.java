/*
 * Copyright (c) 2014 Red Hat, Inc. and others
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package fr.myprysm.vertx.web;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.web.CorsOptions}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.web.CorsOptions} original class using Vert.x codegen.
 */
public class CorsOptionsConverter {

  public static void fromJson(JsonObject json, CorsOptions obj) {
    if (json.getValue("allowCredentials") instanceof Boolean) {
      obj.setAllowCredentials((Boolean)json.getValue("allowCredentials"));
    }
    if (json.getValue("allowedHeaders") instanceof JsonArray) {
      java.util.LinkedHashSet<java.lang.String> list = new java.util.LinkedHashSet<>();
      json.getJsonArray("allowedHeaders").forEach( item -> {
        if (item instanceof String)
          list.add((String)item);
      });
      obj.setAllowedHeaders(list);
    }
    if (json.getValue("allowedMethods") instanceof JsonArray) {
      java.util.LinkedHashSet<io.vertx.core.http.HttpMethod> list = new java.util.LinkedHashSet<>();
      json.getJsonArray("allowedMethods").forEach( item -> {
        if (item instanceof String)
          list.add(io.vertx.core.http.HttpMethod.valueOf((String)item));
      });
      obj.setAllowedMethods(list);
    }
    if (json.getValue("allowedOrigins") instanceof String) {
      obj.setAllowedOrigins((String)json.getValue("allowedOrigins"));
    }
    if (json.getValue("exposedHeaders") instanceof JsonArray) {
      java.util.LinkedHashSet<java.lang.String> list = new java.util.LinkedHashSet<>();
      json.getJsonArray("exposedHeaders").forEach( item -> {
        if (item instanceof String)
          list.add((String)item);
      });
      obj.setExposedHeaders(list);
    }
    if (json.getValue("maxAgeSeconds") instanceof Number) {
      obj.setMaxAgeSeconds(((Number)json.getValue("maxAgeSeconds")).intValue());
    }
  }

  public static void toJson(CorsOptions obj, JsonObject json) {
    if (obj.getAllowCredentials() != null) {
      json.put("allowCredentials", obj.getAllowCredentials());
    }
    if (obj.getAllowedHeaders() != null) {
      JsonArray array = new JsonArray();
      obj.getAllowedHeaders().forEach(item -> array.add(item));
      json.put("allowedHeaders", array);
    }
    if (obj.getAllowedMethods() != null) {
      JsonArray array = new JsonArray();
      obj.getAllowedMethods().forEach(item -> array.add(item.name()));
      json.put("allowedMethods", array);
    }
    if (obj.getAllowedOrigins() != null) {
      json.put("allowedOrigins", obj.getAllowedOrigins());
    }
    if (obj.getExposedHeaders() != null) {
      JsonArray array = new JsonArray();
      obj.getExposedHeaders().forEach(item -> array.add(item));
      json.put("exposedHeaders", array);
    }
    if (obj.getMaxAgeSeconds() != null) {
      json.put("maxAgeSeconds", obj.getMaxAgeSeconds());
    }
  }
}