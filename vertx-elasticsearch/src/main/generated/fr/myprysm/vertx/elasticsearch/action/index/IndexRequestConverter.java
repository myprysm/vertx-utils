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

package fr.myprysm.vertx.elasticsearch.action.index;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.index.IndexRequest}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.index.IndexRequest} original class using Vert.x codegen.
 */
public class IndexRequestConverter {

  public static void fromJson(JsonObject json, IndexRequest obj) {
    if (json.getValue("pipeline") instanceof String) {
      obj.setPipeline((String)json.getValue("pipeline"));
    }
    if (json.getValue("source") instanceof JsonObject) {
      obj.setSource(((JsonObject)json.getValue("source")).copy());
    }
    if (json.getValue("timeout") instanceof Number) {
      obj.setTimeout(((Number)json.getValue("timeout")).longValue());
    }
  }

  public static void toJson(IndexRequest obj, JsonObject json) {
    if (obj.getPipeline() != null) {
      json.put("pipeline", obj.getPipeline());
    }
    if (obj.getSource() != null) {
      json.put("source", obj.getSource());
    }
    json.put("timeout", obj.getTimeout());
  }
}