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

package fr.myprysm.vertx.elasticsearch.action.bulk;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponse}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponse} original class using Vert.x codegen.
 */
public class BulkResponseConverter {

  public static void fromJson(JsonObject json, BulkResponse obj) {
    if (json.getValue("errors") instanceof Boolean) {
      obj.setErrors((Boolean)json.getValue("errors"));
    }
    if (json.getValue("items") instanceof JsonArray) {
      java.util.ArrayList<fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponseItem> list = new java.util.ArrayList<>();
      json.getJsonArray("items").forEach( item -> {
        if (item instanceof JsonObject)
          list.add(new fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponseItem((JsonObject)item));
      });
      obj.setItems(list);
    }
    if (json.getValue("status") instanceof String) {
      obj.setStatus(org.elasticsearch.rest.RestStatus.valueOf((String)json.getValue("status")));
    }
    if (json.getValue("took") instanceof Number) {
      obj.setTook(((Number)json.getValue("took")).longValue());
    }
  }

  public static void toJson(BulkResponse obj, JsonObject json) {
    json.put("errors", obj.isErrors());
    if (obj.getItems() != null) {
      JsonArray array = new JsonArray();
      obj.getItems().forEach(item -> array.add(item.toJson()));
      json.put("items", array);
    }
    if (obj.getStatus() != null) {
      json.put("status", obj.getStatus().name());
    }
    json.put("took", obj.getTook());
  }
}