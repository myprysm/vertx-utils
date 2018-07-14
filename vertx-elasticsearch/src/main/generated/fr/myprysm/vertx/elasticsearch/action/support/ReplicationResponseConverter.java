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
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.support.ReplicationResponse}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.support.ReplicationResponse} original class using Vert.x codegen.
 */
public class ReplicationResponseConverter {

  public static void fromJson(JsonObject json, ReplicationResponse obj) {
    if (json.getValue("shardInfo") instanceof JsonObject) {
      obj.setShardInfo(new fr.myprysm.vertx.elasticsearch.action.support.ShardInfo((JsonObject)json.getValue("shardInfo")));
    }
  }

  public static void toJson(ReplicationResponse obj, JsonObject json) {
    if (obj.getShardInfo() != null) {
      json.put("shardInfo", obj.getShardInfo().toJson());
    }
  }
}