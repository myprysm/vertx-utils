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

package fr.myprysm.vertx.elasticsearch.action.admin.indices.create;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.admin.indices.create.Alias}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.admin.indices.create.Alias} original class using Vert.x codegen.
 */
public class AliasConverter {

  public static void fromJson(JsonObject json, Alias obj) {
    if (json.getValue("filter") instanceof String) {
      obj.setFilter((String)json.getValue("filter"));
    }
    if (json.getValue("indexRouting") instanceof String) {
      obj.setIndexRouting((String)json.getValue("indexRouting"));
    }
    if (json.getValue("name") instanceof String) {
      obj.setName((String)json.getValue("name"));
    }
    if (json.getValue("searchRouting") instanceof String) {
      obj.setSearchRouting((String)json.getValue("searchRouting"));
    }
  }

  public static void toJson(Alias obj, JsonObject json) {
    if (obj.getFilter() != null) {
      json.put("filter", obj.getFilter());
    }
    if (obj.getIndexRouting() != null) {
      json.put("indexRouting", obj.getIndexRouting());
    }
    if (obj.getName() != null) {
      json.put("name", obj.getName());
    }
    if (obj.getSearchRouting() != null) {
      json.put("searchRouting", obj.getSearchRouting());
    }
  }
}