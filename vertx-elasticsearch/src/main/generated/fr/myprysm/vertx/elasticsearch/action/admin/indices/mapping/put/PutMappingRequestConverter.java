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

package fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest} original class using Vert.x codegen.
 */
public class PutMappingRequestConverter {

  public static void fromJson(JsonObject json, PutMappingRequest obj) {
    if (json.getValue("indices") instanceof JsonArray) {
      java.util.ArrayList<java.lang.String> list = new java.util.ArrayList<>();
      json.getJsonArray("indices").forEach( item -> {
        if (item instanceof String)
          list.add((String)item);
      });
      obj.setIndices(list);
    }
    if (json.getValue("source") instanceof JsonObject) {
      obj.setSource(((JsonObject)json.getValue("source")).copy());
    }
    if (json.getValue("timeout") instanceof Number) {
      obj.setTimeout(((Number)json.getValue("timeout")).longValue());
    }
    if (json.getValue("type") instanceof String) {
      obj.setType((String)json.getValue("type"));
    }
  }

  public static void toJson(PutMappingRequest obj, JsonObject json) {
    if (obj.getIndices() != null) {
      JsonArray array = new JsonArray();
      obj.getIndices().forEach(item -> array.add(item));
      json.put("indices", array);
    }
    if (obj.getSource() != null) {
      json.put("source", obj.getSource());
    }
    json.put("timeout", obj.getTimeout());
    if (obj.getType() != null) {
      json.put("type", obj.getType());
    }
  }
}