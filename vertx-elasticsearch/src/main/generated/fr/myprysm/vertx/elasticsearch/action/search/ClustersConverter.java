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
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.search.Clusters}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.search.Clusters} original class using Vert.x codegen.
 */
public class ClustersConverter {

  public static void fromJson(JsonObject json, Clusters obj) {
    if (json.getValue("skipped") instanceof Number) {
      obj.setSkipped(((Number)json.getValue("skipped")).intValue());
    }
    if (json.getValue("successful") instanceof Number) {
      obj.setSuccessful(((Number)json.getValue("successful")).intValue());
    }
    if (json.getValue("total") instanceof Number) {
      obj.setTotal(((Number)json.getValue("total")).intValue());
    }
  }

  public static void toJson(Clusters obj, JsonObject json) {
    json.put("skipped", obj.getSkipped());
    json.put("successful", obj.getSuccessful());
    json.put("total", obj.getTotal());
  }
}