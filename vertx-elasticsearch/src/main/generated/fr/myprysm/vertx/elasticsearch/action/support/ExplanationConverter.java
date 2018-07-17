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
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.support.Explanation}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.support.Explanation} original class using Vert.x codegen.
 */
public class ExplanationConverter {

  public static void fromJson(JsonObject json, Explanation obj) {
    if (json.getValue("description") instanceof String) {
      obj.setDescription((String)json.getValue("description"));
    }
    if (json.getValue("details") instanceof JsonArray) {
      java.util.ArrayList<fr.myprysm.vertx.elasticsearch.action.support.Explanation> list = new java.util.ArrayList<>();
      json.getJsonArray("details").forEach( item -> {
        if (item instanceof JsonObject)
          list.add(new fr.myprysm.vertx.elasticsearch.action.support.Explanation((JsonObject)item));
      });
      obj.setDetails(list);
    }
    if (json.getValue("match") instanceof Boolean) {
      obj.setMatch((Boolean)json.getValue("match"));
    }
    if (json.getValue("value") instanceof Number) {
      obj.setValue(((Number)json.getValue("value")).floatValue());
    }
  }

  public static void toJson(Explanation obj, JsonObject json) {
    if (obj.getDescription() != null) {
      json.put("description", obj.getDescription());
    }
    if (obj.getDetails() != null) {
      JsonArray array = new JsonArray();
      obj.getDetails().forEach(item -> array.add(item.toJson()));
      json.put("details", array);
    }
    json.put("match", obj.isMatch());
    json.put("value", obj.getValue());
  }
}