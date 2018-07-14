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

package fr.myprysm.vertx.elasticsearch.action.support;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.support.Script}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.support.Script} original class using Vert.x codegen.
 */
public class ScriptConverter {

  public static void fromJson(JsonObject json, Script obj) {
    if (json.getValue("idOrCode") instanceof String) {
      obj.setIdOrCode((String)json.getValue("idOrCode"));
    }
    if (json.getValue("lang") instanceof String) {
      obj.setLang((String)json.getValue("lang"));
    }
    if (json.getValue("options") instanceof JsonObject) {
      java.util.Map<String, java.lang.String> map = new java.util.LinkedHashMap<>();
      json.getJsonObject("options").forEach(entry -> {
        if (entry.getValue() instanceof String)
          map.put(entry.getKey(), (String)entry.getValue());
      });
      obj.setOptions(map);
    }
    if (json.getValue("params") instanceof JsonObject) {
      obj.setParams(((JsonObject)json.getValue("params")).copy());
    }
    if (json.getValue("type") instanceof String) {
      obj.setType(org.elasticsearch.script.ScriptType.valueOf((String)json.getValue("type")));
    }
  }

  public static void toJson(Script obj, JsonObject json) {
    if (obj.getIdOrCode() != null) {
      json.put("idOrCode", obj.getIdOrCode());
    }
    if (obj.getLang() != null) {
      json.put("lang", obj.getLang());
    }
    if (obj.getOptions() != null) {
      JsonObject map = new JsonObject();
      obj.getOptions().forEach((key,value) -> map.put(key, value));
      json.put("options", map);
    }
    if (obj.getParams() != null) {
      json.put("params", obj.getParams());
    }
    if (obj.getType() != null) {
      json.put("type", obj.getType().name());
    }
  }
}