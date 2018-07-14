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

package fr.myprysm.vertx.elasticsearch.action.get;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.get.GetResponse}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.get.GetResponse} original class using Vert.x codegen.
 */
public class GetResponseConverter {

  public static void fromJson(JsonObject json, GetResponse obj) {
    if (json.getValue("exists") instanceof Boolean) {
      obj.setExists((Boolean)json.getValue("exists"));
    }
    if (json.getValue("fields") instanceof JsonObject) {
      java.util.Map<String, fr.myprysm.vertx.elasticsearch.action.support.DocumentField> map = new java.util.LinkedHashMap<>();
      json.getJsonObject("fields").forEach(entry -> {
        if (entry.getValue() instanceof JsonObject)
          map.put(entry.getKey(), new fr.myprysm.vertx.elasticsearch.action.support.DocumentField((JsonObject)entry.getValue()));
      });
      obj.setFields(map);
    }
    if (json.getValue("id") instanceof String) {
      obj.setId((String)json.getValue("id"));
    }
    if (json.getValue("index") instanceof String) {
      obj.setIndex((String)json.getValue("index"));
    }
    if (json.getValue("source") instanceof JsonObject) {
      obj.setSource(((JsonObject)json.getValue("source")).copy());
    }
    if (json.getValue("type") instanceof String) {
      obj.setType((String)json.getValue("type"));
    }
    if (json.getValue("version") instanceof Number) {
      obj.setVersion(((Number)json.getValue("version")).longValue());
    }
  }

  public static void toJson(GetResponse obj, JsonObject json) {
    json.put("exists", obj.isExists());
    if (obj.getFields() != null) {
      JsonObject map = new JsonObject();
      obj.getFields().forEach((key,value) -> map.put(key, value.toJson()));
      json.put("fields", map);
    }
    if (obj.getId() != null) {
      json.put("id", obj.getId());
    }
    if (obj.getIndex() != null) {
      json.put("index", obj.getIndex());
    }
    if (obj.getSource() != null) {
      json.put("source", obj.getSource());
    }
    if (obj.getType() != null) {
      json.put("type", obj.getType());
    }
    json.put("version", obj.getVersion());
  }
}