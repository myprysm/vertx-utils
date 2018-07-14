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
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.get.MultiGetRequest}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.get.MultiGetRequest} original class using Vert.x codegen.
 */
public class MultiGetRequestConverter {

  public static void fromJson(JsonObject json, MultiGetRequest obj) {
    if (json.getValue("items") instanceof JsonArray) {
      java.util.ArrayList<fr.myprysm.vertx.elasticsearch.action.get.GetRequestItem> list = new java.util.ArrayList<>();
      json.getJsonArray("items").forEach( item -> {
        if (item instanceof JsonObject)
          list.add(new fr.myprysm.vertx.elasticsearch.action.get.GetRequestItem((JsonObject)item));
      });
      obj.setItems(list);
    }
    if (json.getValue("preference") instanceof String) {
      obj.setPreference((String)json.getValue("preference"));
    }
    if (json.getValue("realTime") instanceof Boolean) {
      obj.setRealTime((Boolean)json.getValue("realTime"));
    }
    if (json.getValue("refresh") instanceof Boolean) {
      obj.setRefresh((Boolean)json.getValue("refresh"));
    }
  }

  public static void toJson(MultiGetRequest obj, JsonObject json) {
    if (obj.getItems() != null) {
      JsonArray array = new JsonArray();
      obj.getItems().forEach(item -> array.add(item.toJson()));
      json.put("items", array);
    }
    if (obj.getPreference() != null) {
      json.put("preference", obj.getPreference());
    }
    json.put("realTime", obj.isRealTime());
    json.put("refresh", obj.isRefresh());
  }
}