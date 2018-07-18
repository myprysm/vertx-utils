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
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixStatsResult}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixStatsResult} original class using Vert.x codegen.
 */
public class MatrixStatsResultConverter {

  public static void fromJson(JsonObject json, MatrixStatsResult obj) {
    if (json.getValue("correlation") instanceof JsonObject) {
      java.util.Map<String, java.lang.Double> map = new java.util.LinkedHashMap<>();
      json.getJsonObject("correlation").forEach(entry -> {
        if (entry.getValue() instanceof Number)
          map.put(entry.getKey(), ((Number)entry.getValue()).doubleValue());
      });
      obj.setCorrelation(map);
    }
    if (json.getValue("count") instanceof Number) {
      obj.setCount(((Number)json.getValue("count")).longValue());
    }
    if (json.getValue("covariance") instanceof JsonObject) {
      java.util.Map<String, java.lang.Double> map = new java.util.LinkedHashMap<>();
      json.getJsonObject("covariance").forEach(entry -> {
        if (entry.getValue() instanceof Number)
          map.put(entry.getKey(), ((Number)entry.getValue()).doubleValue());
      });
      obj.setCovariance(map);
    }
    if (json.getValue("kurtosis") instanceof Number) {
      obj.setKurtosis(((Number)json.getValue("kurtosis")).doubleValue());
    }
    if (json.getValue("mean") instanceof Number) {
      obj.setMean(((Number)json.getValue("mean")).doubleValue());
    }
    if (json.getValue("name") instanceof String) {
      obj.setName((String)json.getValue("name"));
    }
    if (json.getValue("skewness") instanceof Number) {
      obj.setSkewness(((Number)json.getValue("skewness")).doubleValue());
    }
    if (json.getValue("variance") instanceof Number) {
      obj.setVariance(((Number)json.getValue("variance")).doubleValue());
    }
  }

  public static void toJson(MatrixStatsResult obj, JsonObject json) {
    if (obj.getCorrelation() != null) {
      JsonObject map = new JsonObject();
      obj.getCorrelation().forEach((key,value) -> map.put(key, value));
      json.put("correlation", map);
    }
    if (obj.getCount() != null) {
      json.put("count", obj.getCount());
    }
    if (obj.getCovariance() != null) {
      JsonObject map = new JsonObject();
      obj.getCovariance().forEach((key,value) -> map.put(key, value));
      json.put("covariance", map);
    }
    if (obj.getKurtosis() != null) {
      json.put("kurtosis", obj.getKurtosis());
    }
    if (obj.getMean() != null) {
      json.put("mean", obj.getMean());
    }
    if (obj.getName() != null) {
      json.put("name", obj.getName());
    }
    if (obj.getSkewness() != null) {
      json.put("skewness", obj.getSkewness());
    }
    if (obj.getVariance() != null) {
      json.put("variance", obj.getVariance());
    }
  }
}