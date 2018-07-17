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

package fr.myprysm.vertx.elasticsearch.action.search;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.search.MultiSearchRequest}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.search.MultiSearchRequest} original class using Vert.x codegen.
 */
public class MultiSearchRequestConverter {

  public static void fromJson(JsonObject json, MultiSearchRequest obj) {
    if (json.getValue("maxConcurrentSearchRequests") instanceof Number) {
      obj.setMaxConcurrentSearchRequests(((Number)json.getValue("maxConcurrentSearchRequests")).intValue());
    }
    if (json.getValue("requests") instanceof JsonArray) {
      java.util.ArrayList<fr.myprysm.vertx.elasticsearch.action.search.SearchRequest> list = new java.util.ArrayList<>();
      json.getJsonArray("requests").forEach( item -> {
        if (item instanceof JsonObject)
          list.add(new fr.myprysm.vertx.elasticsearch.action.search.SearchRequest((JsonObject)item));
      });
      obj.setRequests(list);
    }
  }

  public static void toJson(MultiSearchRequest obj, JsonObject json) {
    if (obj.getMaxConcurrentSearchRequests() != null) {
      json.put("maxConcurrentSearchRequests", obj.getMaxConcurrentSearchRequests());
    }
    if (obj.getRequests() != null) {
      JsonArray array = new JsonArray();
      obj.getRequests().forEach(item -> array.add(item.toJson()));
      json.put("requests", array);
    }
  }
}