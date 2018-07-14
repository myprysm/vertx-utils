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
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.support.DocWriteResponse}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.support.DocWriteResponse} original class using Vert.x codegen.
 */
public class DocWriteResponseConverter {

  public static void fromJson(JsonObject json, DocWriteResponse obj) {
    if (json.getValue("forcedRefresh") instanceof Boolean) {
      obj.setForcedRefresh((Boolean)json.getValue("forcedRefresh"));
    }
    if (json.getValue("id") instanceof String) {
      obj.setId((String)json.getValue("id"));
    }
    if (json.getValue("index") instanceof String) {
      obj.setIndex((String)json.getValue("index"));
    }
    if (json.getValue("primaryTerm") instanceof Number) {
      obj.setPrimaryTerm(((Number)json.getValue("primaryTerm")).longValue());
    }
    if (json.getValue("result") instanceof String) {
      obj.setResult(org.elasticsearch.action.DocWriteResponse.Result.valueOf((String)json.getValue("result")));
    }
    if (json.getValue("seqNo") instanceof Number) {
      obj.setSeqNo(((Number)json.getValue("seqNo")).longValue());
    }
    if (json.getValue("shardId") instanceof JsonObject) {
      obj.setShardId(new fr.myprysm.vertx.elasticsearch.action.support.ShardId((JsonObject)json.getValue("shardId")));
    }
    if (json.getValue("type") instanceof String) {
      obj.setType((String)json.getValue("type"));
    }
    if (json.getValue("version") instanceof Number) {
      obj.setVersion(((Number)json.getValue("version")).longValue());
    }
  }

  public static void toJson(DocWriteResponse obj, JsonObject json) {
    json.put("forcedRefresh", obj.isForcedRefresh());
    if (obj.getId() != null) {
      json.put("id", obj.getId());
    }
    if (obj.getIndex() != null) {
      json.put("index", obj.getIndex());
    }
    json.put("primaryTerm", obj.getPrimaryTerm());
    if (obj.getResult() != null) {
      json.put("result", obj.getResult().name());
    }
    json.put("seqNo", obj.getSeqNo());
    if (obj.getShardId() != null) {
      json.put("shardId", obj.getShardId().toJson());
    }
    if (obj.getType() != null) {
      json.put("type", obj.getType());
    }
    json.put("version", obj.getVersion());
  }
}