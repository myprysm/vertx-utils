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

package fr.myprysm.vertx.elasticsearch.action.update;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.update.UpdateResponse}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.update.UpdateResponse} original class using Vert.x codegen.
 */
public class UpdateResponseConverter {

  public static void fromJson(JsonObject json, UpdateResponse obj) {
    if (json.getValue("getResult") instanceof JsonObject) {
      obj.setGetResult(new fr.myprysm.vertx.elasticsearch.action.get.GetResponse((JsonObject)json.getValue("getResult")));
    }
    if (json.getValue("status") instanceof String) {
      obj.setStatus(org.elasticsearch.rest.RestStatus.valueOf((String)json.getValue("status")));
    }
  }

  public static void toJson(UpdateResponse obj, JsonObject json) {
    if (obj.getGetResult() != null) {
      json.put("getResult", obj.getGetResult().toJson());
    }
    if (obj.getStatus() != null) {
      json.put("status", obj.getStatus().name());
    }
  }
}