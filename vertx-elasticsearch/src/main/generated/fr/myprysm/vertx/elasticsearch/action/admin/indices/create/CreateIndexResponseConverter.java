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
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexResponse}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexResponse} original class using Vert.x codegen.
 */
public class CreateIndexResponseConverter {

  public static void fromJson(JsonObject json, CreateIndexResponse obj) {
    if (json.getValue("index") instanceof String) {
      obj.setIndex((String)json.getValue("index"));
    }
    if (json.getValue("shardsAcknowledged") instanceof Boolean) {
      obj.setShardsAcknowledged((Boolean)json.getValue("shardsAcknowledged"));
    }
  }

  public static void toJson(CreateIndexResponse obj, JsonObject json) {
    if (obj.getIndex() != null) {
      json.put("index", obj.getIndex());
    }
    json.put("shardsAcknowledged", obj.isShardsAcknowledged());
  }
}