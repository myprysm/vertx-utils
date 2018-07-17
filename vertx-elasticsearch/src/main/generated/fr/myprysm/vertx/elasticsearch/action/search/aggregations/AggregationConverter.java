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

package fr.myprysm.vertx.elasticsearch.action.search.aggregations;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation} original class using Vert.x codegen.
 */
public class AggregationConverter {

  public static void fromJson(JsonObject json, Aggregation obj) {
    if (json.getValue("data") instanceof JsonObject) {
      obj.setData(((JsonObject)json.getValue("data")).copy());
    }
    if (json.getValue("metaData") instanceof JsonObject) {
      obj.setMetaData(((JsonObject)json.getValue("metaData")).copy());
    }
    if (json.getValue("name") instanceof String) {
      obj.setName((String)json.getValue("name"));
    }
    if (json.getValue("type") instanceof String) {
      obj.setType((String)json.getValue("type"));
    }
  }

  public static void toJson(Aggregation obj, JsonObject json) {
    if (obj.getData() != null) {
      json.put("data", obj.getData());
    }
    if (obj.getMetaData() != null) {
      json.put("metaData", obj.getMetaData());
    }
    if (obj.getName() != null) {
      json.put("name", obj.getName());
    }
    if (obj.getType() != null) {
      json.put("type", obj.getType());
    }
  }
}