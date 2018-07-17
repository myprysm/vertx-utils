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
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponse}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponse} original class using Vert.x codegen.
 */
public class MultiSearchResponseConverter {

  public static void fromJson(JsonObject json, MultiSearchResponse obj) {
    if (json.getValue("responses") instanceof JsonArray) {
      java.util.ArrayList<fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponseItem> list = new java.util.ArrayList<>();
      json.getJsonArray("responses").forEach( item -> {
        if (item instanceof JsonObject)
          list.add(new fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponseItem((JsonObject)item));
      });
      obj.setResponses(list);
    }
  }

  public static void toJson(MultiSearchResponse obj, JsonObject json) {
    if (obj.getResponses() != null) {
      JsonArray array = new JsonArray();
      obj.getResponses().forEach(item -> array.add(item.toJson()));
      json.put("responses", array);
    }
  }
}