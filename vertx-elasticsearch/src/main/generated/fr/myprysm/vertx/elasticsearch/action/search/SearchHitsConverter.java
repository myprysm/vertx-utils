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
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.search.SearchHits}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.search.SearchHits} original class using Vert.x codegen.
 */
public class SearchHitsConverter {

  public static void fromJson(JsonObject json, SearchHits obj) {
    if (json.getValue("hits") instanceof JsonArray) {
      java.util.ArrayList<fr.myprysm.vertx.elasticsearch.action.search.SearchHit> list = new java.util.ArrayList<>();
      json.getJsonArray("hits").forEach( item -> {
        if (item instanceof JsonObject)
          list.add(new fr.myprysm.vertx.elasticsearch.action.search.SearchHit((JsonObject)item));
      });
      obj.setHits(list);
    }
    if (json.getValue("maxScore") instanceof Number) {
      obj.setMaxScore(((Number)json.getValue("maxScore")).floatValue());
    }
    if (json.getValue("totalHits") instanceof Number) {
      obj.setTotalHits(((Number)json.getValue("totalHits")).longValue());
    }
  }

  public static void toJson(SearchHits obj, JsonObject json) {
    if (obj.getHits() != null) {
      JsonArray array = new JsonArray();
      obj.getHits().forEach(item -> array.add(item.toJson()));
      json.put("hits", array);
    }
    json.put("maxScore", obj.getMaxScore());
    json.put("totalHits", obj.getTotalHits());
  }
}