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

package fr.myprysm.vertx.elasticsearch.action.admin.indices.get;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest} original class using Vert.x codegen.
 */
public class GetIndexRequestConverter {

  public static void fromJson(JsonObject json, GetIndexRequest obj) {
    if (json.getValue("features") instanceof JsonArray) {
      java.util.ArrayList<org.elasticsearch.action.admin.indices.get.GetIndexRequest.Feature> list = new java.util.ArrayList<>();
      json.getJsonArray("features").forEach( item -> {
        if (item instanceof String)
          list.add(org.elasticsearch.action.admin.indices.get.GetIndexRequest.Feature.valueOf((String)item));
      });
      obj.setFeatures(list);
    }
    if (json.getValue("includeDefaults") instanceof Boolean) {
      obj.setIncludeDefaults((Boolean)json.getValue("includeDefaults"));
    }
    if (json.getValue("indices") instanceof JsonArray) {
      java.util.ArrayList<java.lang.String> list = new java.util.ArrayList<>();
      json.getJsonArray("indices").forEach( item -> {
        if (item instanceof String)
          list.add((String)item);
      });
      obj.setIndices(list);
    }
    if (json.getValue("local") instanceof Boolean) {
      obj.setLocal((Boolean)json.getValue("local"));
    }
    if (json.getValue("masterNodeTimeout") instanceof Number) {
      obj.setMasterNodeTimeout(((Number)json.getValue("masterNodeTimeout")).longValue());
    }
    if (json.getValue("types") instanceof JsonArray) {
      java.util.ArrayList<java.lang.String> list = new java.util.ArrayList<>();
      json.getJsonArray("types").forEach( item -> {
        if (item instanceof String)
          list.add((String)item);
      });
      obj.setTypes(list);
    }
  }

  public static void toJson(GetIndexRequest obj, JsonObject json) {
    if (obj.getFeatures() != null) {
      JsonArray array = new JsonArray();
      obj.getFeatures().forEach(item -> array.add(item.name()));
      json.put("features", array);
    }
    json.put("includeDefaults", obj.isIncludeDefaults());
    if (obj.getIndices() != null) {
      JsonArray array = new JsonArray();
      obj.getIndices().forEach(item -> array.add(item));
      json.put("indices", array);
    }
    json.put("local", obj.isLocal());
    json.put("masterNodeTimeout", obj.getMasterNodeTimeout());
    if (obj.getTypes() != null) {
      JsonArray array = new JsonArray();
      obj.getTypes().forEach(item -> array.add(item));
      json.put("types", array);
    }
  }
}