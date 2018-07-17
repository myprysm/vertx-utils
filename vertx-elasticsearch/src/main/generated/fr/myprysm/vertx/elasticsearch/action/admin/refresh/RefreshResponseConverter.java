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

package fr.myprysm.vertx.elasticsearch.action.admin.refresh;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.admin.refresh.RefreshResponse}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.admin.refresh.RefreshResponse} original class using Vert.x codegen.
 */
public class RefreshResponseConverter {

  public static void fromJson(JsonObject json, RefreshResponse obj) {
    if (json.getValue("failedShards") instanceof Number) {
      obj.setFailedShards(((Number)json.getValue("failedShards")).intValue());
    }
    if (json.getValue("shardFailures") instanceof JsonArray) {
      java.util.ArrayList<fr.myprysm.vertx.elasticsearch.action.support.ShardFailure> list = new java.util.ArrayList<>();
      json.getJsonArray("shardFailures").forEach( item -> {
        if (item instanceof JsonObject)
          list.add(new fr.myprysm.vertx.elasticsearch.action.support.ShardFailure((JsonObject)item));
      });
      obj.setShardFailures(list);
    }
    if (json.getValue("status") instanceof String) {
      obj.setStatus(org.elasticsearch.rest.RestStatus.valueOf((String)json.getValue("status")));
    }
    if (json.getValue("successfulShards") instanceof Number) {
      obj.setSuccessfulShards(((Number)json.getValue("successfulShards")).intValue());
    }
    if (json.getValue("totalShards") instanceof Number) {
      obj.setTotalShards(((Number)json.getValue("totalShards")).intValue());
    }
  }

  public static void toJson(RefreshResponse obj, JsonObject json) {
    json.put("failedShards", obj.getFailedShards());
    if (obj.getShardFailures() != null) {
      JsonArray array = new JsonArray();
      obj.getShardFailures().forEach(item -> array.add(item.toJson()));
      json.put("shardFailures", array);
    }
    if (obj.getStatus() != null) {
      json.put("status", obj.getStatus().name());
    }
    json.put("successfulShards", obj.getSuccessfulShards());
    json.put("totalShards", obj.getTotalShards());
  }
}