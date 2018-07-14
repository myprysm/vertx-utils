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

package fr.myprysm.vertx.elasticsearch;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.ElasticsearchClientOptions}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.ElasticsearchClientOptions} original class using Vert.x codegen.
 */
public class ElasticsearchClientOptionsConverter {

  public static void fromJson(JsonObject json, ElasticsearchClientOptions obj) {
    if (json.getValue("defaultHeaders") instanceof JsonObject) {
      java.util.Map<String, java.lang.String> map = new java.util.LinkedHashMap<>();
      json.getJsonObject("defaultHeaders").forEach(entry -> {
        if (entry.getValue() instanceof String)
          map.put(entry.getKey(), (String)entry.getValue());
      });
      obj.setDefaultHeaders(map);
    }
    if (json.getValue("hosts") instanceof JsonArray) {
      java.util.ArrayList<fr.myprysm.vertx.elasticsearch.HttpHost> list = new java.util.ArrayList<>();
      json.getJsonArray("hosts").forEach( item -> {
        if (item instanceof JsonObject)
          list.add(new fr.myprysm.vertx.elasticsearch.HttpHost((JsonObject)item));
      });
      obj.setHosts(list);
    }
    if (json.getValue("maxRetryTimeout") instanceof Number) {
      obj.setMaxRetryTimeout(((Number)json.getValue("maxRetryTimeout")).intValue());
    }
    if (json.getValue("pathPrefix") instanceof String) {
      obj.setPathPrefix((String)json.getValue("pathPrefix"));
    }
    if (json.getValue("useNativeAsyncAPI") instanceof Boolean) {
      obj.setUseNativeAsyncAPI((Boolean)json.getValue("useNativeAsyncAPI"));
    }
  }

  public static void toJson(ElasticsearchClientOptions obj, JsonObject json) {
    if (obj.getDefaultHeaders() != null) {
      JsonObject map = new JsonObject();
      obj.getDefaultHeaders().forEach((key,value) -> map.put(key, value));
      json.put("defaultHeaders", map);
    }
    if (obj.getHosts() != null) {
      JsonArray array = new JsonArray();
      obj.getHosts().forEach(item -> array.add(item.toJson()));
      json.put("hosts", array);
    }
    json.put("maxRetryTimeout", obj.getMaxRetryTimeout());
    if (obj.getPathPrefix() != null) {
      json.put("pathPrefix", obj.getPathPrefix());
    }
    json.put("useNativeAsyncAPI", obj.isUseNativeAsyncAPI());
  }
}