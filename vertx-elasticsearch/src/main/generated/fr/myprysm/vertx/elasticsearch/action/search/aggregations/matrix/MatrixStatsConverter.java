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

package fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixStats}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixStats} original class using Vert.x codegen.
 */
public class MatrixStatsConverter {

  public static void fromJson(JsonObject json, MatrixStats obj) {
    if (json.getValue("docCount") instanceof Number) {
      obj.setDocCount(((Number)json.getValue("docCount")).longValue());
    }
    if (json.getValue("results") instanceof JsonObject) {
      java.util.Map<String, fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixStatsResult> map = new java.util.LinkedHashMap<>();
      json.getJsonObject("results").forEach(entry -> {
        if (entry.getValue() instanceof JsonObject)
          map.put(entry.getKey(), new fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixStatsResult((JsonObject)entry.getValue()));
      });
      obj.setResults(map);
    }
  }

  public static void toJson(MatrixStats obj, JsonObject json) {
    json.put("docCount", obj.getDocCount());
    if (obj.getResults() != null) {
      JsonObject map = new JsonObject();
      obj.getResults().forEach((key,value) -> map.put(key, value.toJson()));
      json.put("results", map);
    }
  }
}