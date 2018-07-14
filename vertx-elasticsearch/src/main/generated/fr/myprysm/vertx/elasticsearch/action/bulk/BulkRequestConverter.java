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
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.bulk.BulkRequest}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.bulk.BulkRequest} original class using Vert.x codegen.
 */
public class BulkRequestConverter {

  public static void fromJson(JsonObject json, BulkRequest obj) {
    if (json.getValue("refreshPolicy") instanceof String) {
      obj.setRefreshPolicy(org.elasticsearch.action.support.WriteRequest.RefreshPolicy.valueOf((String)json.getValue("refreshPolicy")));
    }
    if (json.getValue("requests") instanceof JsonArray) {
      java.util.ArrayList<fr.myprysm.vertx.elasticsearch.action.support.DocWriteRequest> list = new java.util.ArrayList<>();
      json.getJsonArray("requests").forEach( item -> {
        if (item instanceof JsonObject)
          list.add(new fr.myprysm.vertx.elasticsearch.action.support.DocWriteRequest((JsonObject)item));
      });
      obj.setRequests(list);
    }
    if (json.getValue("timeout") instanceof Number) {
      obj.setTimeout(((Number)json.getValue("timeout")).longValue());
    }
    if (json.getValue("waitForActiveShards") instanceof Number) {
      obj.setWaitForActiveShards(((Number)json.getValue("waitForActiveShards")).intValue());
    }
  }

  public static void toJson(BulkRequest obj, JsonObject json) {
    if (obj.getRefreshPolicy() != null) {
      json.put("refreshPolicy", obj.getRefreshPolicy().name());
    }
    if (obj.getRequests() != null) {
      JsonArray array = new JsonArray();
      obj.getRequests().forEach(item -> array.add(item.toJson()));
      json.put("requests", array);
    }
    json.put("timeout", obj.getTimeout());
    if (obj.getWaitForActiveShards() != null) {
      json.put("waitForActiveShards", obj.getWaitForActiveShards());
    }
  }
}