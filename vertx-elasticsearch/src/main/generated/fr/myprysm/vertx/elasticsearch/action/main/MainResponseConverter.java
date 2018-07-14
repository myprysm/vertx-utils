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

package fr.myprysm.vertx.elasticsearch.action.main;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.main.MainResponse}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.main.MainResponse} original class using Vert.x codegen.
 */
public class MainResponseConverter {

  public static void fromJson(JsonObject json, MainResponse obj) {
    if (json.getValue("available") instanceof Boolean) {
      obj.setAvailable((Boolean)json.getValue("available"));
    }
    if (json.getValue("build") instanceof String) {
      obj.setBuild((String)json.getValue("build"));
    }
    if (json.getValue("clusterName") instanceof String) {
      obj.setClusterName((String)json.getValue("clusterName"));
    }
    if (json.getValue("clusterUuid") instanceof String) {
      obj.setClusterUuid((String)json.getValue("clusterUuid"));
    }
    if (json.getValue("nodeName") instanceof String) {
      obj.setNodeName((String)json.getValue("nodeName"));
    }
    if (json.getValue("version") instanceof String) {
      obj.setVersion((String)json.getValue("version"));
    }
  }

  public static void toJson(MainResponse obj, JsonObject json) {
    json.put("available", obj.isAvailable());
    if (obj.getBuild() != null) {
      json.put("build", obj.getBuild());
    }
    if (obj.getClusterName() != null) {
      json.put("clusterName", obj.getClusterName());
    }
    if (obj.getClusterUuid() != null) {
      json.put("clusterUuid", obj.getClusterUuid());
    }
    if (obj.getNodeName() != null) {
      json.put("nodeName", obj.getNodeName());
    }
    if (obj.getVersion() != null) {
      json.put("version", obj.getVersion());
    }
  }
}