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

package fr.myprysm.vertx.elasticsearch.action.support;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.support.FetchSourceContext}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.support.FetchSourceContext} original class using Vert.x codegen.
 */
public class FetchSourceContextConverter {

  public static void fromJson(JsonObject json, FetchSourceContext obj) {
    if (json.getValue("excludes") instanceof JsonArray) {
      java.util.ArrayList<java.lang.String> list = new java.util.ArrayList<>();
      json.getJsonArray("excludes").forEach( item -> {
        if (item instanceof String)
          list.add((String)item);
      });
      obj.setExcludes(list);
    }
    if (json.getValue("fetchSource") instanceof Boolean) {
      obj.setFetchSource((Boolean)json.getValue("fetchSource"));
    }
    if (json.getValue("includes") instanceof JsonArray) {
      java.util.ArrayList<java.lang.String> list = new java.util.ArrayList<>();
      json.getJsonArray("includes").forEach( item -> {
        if (item instanceof String)
          list.add((String)item);
      });
      obj.setIncludes(list);
    }
  }

  public static void toJson(FetchSourceContext obj, JsonObject json) {
    if (obj.getExcludes() != null) {
      JsonArray array = new JsonArray();
      obj.getExcludes().forEach(item -> array.add(item));
      json.put("excludes", array);
    }
    json.put("fetchSource", obj.isFetchSource());
    if (obj.getIncludes() != null) {
      JsonArray array = new JsonArray();
      obj.getIncludes().forEach(item -> array.add(item));
      json.put("includes", array);
    }
  }
}