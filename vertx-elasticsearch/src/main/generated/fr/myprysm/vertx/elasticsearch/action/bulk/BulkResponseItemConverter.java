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
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponseItem}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponseItem} original class using Vert.x codegen.
 */
public class BulkResponseItemConverter {

  public static void fromJson(JsonObject json, BulkResponseItem obj) {
    if (json.getValue("failed") instanceof Boolean) {
      obj.setFailed((Boolean)json.getValue("failed"));
    }
    if (json.getValue("failure") instanceof String) {
      obj.setFailure((String)json.getValue("failure"));
    }
    if (json.getValue("id") instanceof String) {
      obj.setId((String)json.getValue("id"));
    }
    if (json.getValue("index") instanceof String) {
      obj.setIndex((String)json.getValue("index"));
    }
    if (json.getValue("opType") instanceof String) {
      obj.setOpType(org.elasticsearch.action.DocWriteRequest.OpType.valueOf((String)json.getValue("opType")));
    }
    if (json.getValue("status") instanceof String) {
      obj.setStatus(org.elasticsearch.rest.RestStatus.valueOf((String)json.getValue("status")));
    }
    if (json.getValue("type") instanceof String) {
      obj.setType((String)json.getValue("type"));
    }
    if (json.getValue("version") instanceof Number) {
      obj.setVersion(((Number)json.getValue("version")).longValue());
    }
  }

  public static void toJson(BulkResponseItem obj, JsonObject json) {
    json.put("failed", obj.isFailed());
    if (obj.getFailure() != null) {
      json.put("failure", obj.getFailure());
    }
    if (obj.getId() != null) {
      json.put("id", obj.getId());
    }
    if (obj.getIndex() != null) {
      json.put("index", obj.getIndex());
    }
    if (obj.getOpType() != null) {
      json.put("opType", obj.getOpType().name());
    }
    if (obj.getStatus() != null) {
      json.put("status", obj.getStatus().name());
    }
    if (obj.getType() != null) {
      json.put("type", obj.getType());
    }
    if (obj.getVersion() != null) {
      json.put("version", obj.getVersion());
    }
  }
}