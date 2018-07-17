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

package fr.myprysm.vertx.elasticsearch.action.admin.indices.create;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexRequest}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexRequest} original class using Vert.x codegen.
 */
public class CreateIndexRequestConverter {

  public static void fromJson(JsonObject json, CreateIndexRequest obj) {
    if (json.getValue("aliases") instanceof JsonArray) {
      java.util.LinkedHashSet<fr.myprysm.vertx.elasticsearch.action.admin.indices.create.Alias> list = new java.util.LinkedHashSet<>();
      json.getJsonArray("aliases").forEach( item -> {
        if (item instanceof JsonObject)
          list.add(new fr.myprysm.vertx.elasticsearch.action.admin.indices.create.Alias((JsonObject)item));
      });
      obj.setAliases(list);
    }
    if (json.getValue("cause") instanceof String) {
      obj.setCause((String)json.getValue("cause"));
    }
    if (json.getValue("index") instanceof String) {
      obj.setIndex((String)json.getValue("index"));
    }
    if (json.getValue("mappings") instanceof JsonObject) {
      json.getJsonObject("mappings").forEach(entry -> {
        if (entry.getValue() instanceof JsonObject)
          obj.addMapping(entry.getKey(), ((JsonObject)entry.getValue()).copy());
      });
    }
    if (json.getValue("settings") instanceof JsonObject) {
      obj.setSettings(((JsonObject)json.getValue("settings")).copy());
    }
    if (json.getValue("timeout") instanceof Number) {
      obj.setTimeout(((Number)json.getValue("timeout")).longValue());
    }
    if (json.getValue("waitForActiveShards") instanceof Number) {
      obj.setWaitForActiveShards(((Number)json.getValue("waitForActiveShards")).intValue());
    }
  }

  public static void toJson(CreateIndexRequest obj, JsonObject json) {
    if (obj.getAliases() != null) {
      JsonArray array = new JsonArray();
      obj.getAliases().forEach(item -> array.add(item.toJson()));
      json.put("aliases", array);
    }
    if (obj.getCause() != null) {
      json.put("cause", obj.getCause());
    }
    if (obj.getIndex() != null) {
      json.put("index", obj.getIndex());
    }
    if (obj.getMappings() != null) {
      JsonObject map = new JsonObject();
      obj.getMappings().forEach((key,value) -> map.put(key, value));
      json.put("mappings", map);
    }
    if (obj.getSettings() != null) {
      json.put("settings", obj.getSettings());
    }
    json.put("timeout", obj.getTimeout());
    if (obj.getWaitForActiveShards() != null) {
      json.put("waitForActiveShards", obj.getWaitForActiveShards());
    }
  }
}