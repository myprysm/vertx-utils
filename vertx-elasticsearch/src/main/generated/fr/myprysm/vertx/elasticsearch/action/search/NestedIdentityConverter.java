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
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.search.NestedIdentity}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.search.NestedIdentity} original class using Vert.x codegen.
 */
public class NestedIdentityConverter {

  public static void fromJson(JsonObject json, NestedIdentity obj) {
    if (json.getValue("child") instanceof JsonObject) {
      obj.setChild(new fr.myprysm.vertx.elasticsearch.action.search.NestedIdentity((JsonObject)json.getValue("child")));
    }
    if (json.getValue("field") instanceof String) {
      obj.setField((String)json.getValue("field"));
    }
    if (json.getValue("offset") instanceof Number) {
      obj.setOffset(((Number)json.getValue("offset")).intValue());
    }
  }

  public static void toJson(NestedIdentity obj, JsonObject json) {
    if (obj.getChild() != null) {
      json.put("child", obj.getChild().toJson());
    }
    if (obj.getField() != null) {
      json.put("field", obj.getField());
    }
    json.put("offset", obj.getOffset());
  }
}