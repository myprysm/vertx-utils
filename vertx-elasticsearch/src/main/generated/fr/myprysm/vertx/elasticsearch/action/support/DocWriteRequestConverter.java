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
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.support.DocWriteRequest}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.support.DocWriteRequest} original class using Vert.x codegen.
 */
public class DocWriteRequestConverter {

  public static void fromJson(JsonObject json, DocWriteRequest obj) {
    if (json.getValue("id") instanceof String) {
      obj.setId((String)json.getValue("id"));
    }
    if (json.getValue("index") instanceof String) {
      obj.setIndex((String)json.getValue("index"));
    }
    if (json.getValue("opType") instanceof String) {
      obj.setOpType(org.elasticsearch.action.DocWriteRequest.OpType.valueOf((String)json.getValue("opType")));
    }
    if (json.getValue("parent") instanceof String) {
      obj.setParent((String)json.getValue("parent"));
    }
    if (json.getValue("refreshPolicy") instanceof String) {
      obj.setRefreshPolicy(org.elasticsearch.action.support.WriteRequest.RefreshPolicy.valueOf((String)json.getValue("refreshPolicy")));
    }
    if (json.getValue("routing") instanceof String) {
      obj.setRouting((String)json.getValue("routing"));
    }
    if (json.getValue("type") instanceof String) {
      obj.setType((String)json.getValue("type"));
    }
    if (json.getValue("version") instanceof Number) {
      obj.setVersion(((Number)json.getValue("version")).longValue());
    }
    if (json.getValue("versionType") instanceof String) {
      obj.setVersionType(org.elasticsearch.index.VersionType.valueOf((String)json.getValue("versionType")));
    }
  }

  public static void toJson(DocWriteRequest obj, JsonObject json) {
    if (obj.getId() != null) {
      json.put("id", obj.getId());
    }
    if (obj.getIndex() != null) {
      json.put("index", obj.getIndex());
    }
    if (obj.getOpType() != null) {
      json.put("opType", obj.getOpType().name());
    }
    if (obj.getParent() != null) {
      json.put("parent", obj.getParent());
    }
    if (obj.getRefreshPolicy() != null) {
      json.put("refreshPolicy", obj.getRefreshPolicy().name());
    }
    if (obj.getRouting() != null) {
      json.put("routing", obj.getRouting());
    }
    if (obj.getType() != null) {
      json.put("type", obj.getType());
    }
    json.put("version", obj.getVersion());
    if (obj.getVersionType() != null) {
      json.put("versionType", obj.getVersionType().name());
    }
  }
}