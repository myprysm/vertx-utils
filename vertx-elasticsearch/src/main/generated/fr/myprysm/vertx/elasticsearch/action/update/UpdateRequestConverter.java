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
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.update.UpdateRequest}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.update.UpdateRequest} original class using Vert.x codegen.
 */
public class UpdateRequestConverter {

  public static void fromJson(JsonObject json, UpdateRequest obj) {
    if (json.getValue("detectNoop") instanceof Boolean) {
      obj.setDetectNoop((Boolean)json.getValue("detectNoop"));
    }
    if (json.getValue("doc") instanceof JsonObject) {
      obj.setDoc(((JsonObject)json.getValue("doc")).copy());
    }
    if (json.getValue("docAsUpsert") instanceof Boolean) {
      obj.setDocAsUpsert((Boolean)json.getValue("docAsUpsert"));
    }
    if (json.getValue("fetchSourceContext") instanceof JsonObject) {
      obj.setFetchSourceContext(new fr.myprysm.vertx.elasticsearch.action.support.FetchSourceContext((JsonObject)json.getValue("fetchSourceContext")));
    }
    if (json.getValue("fields") instanceof JsonArray) {
      java.util.ArrayList<java.lang.String> list = new java.util.ArrayList<>();
      json.getJsonArray("fields").forEach( item -> {
        if (item instanceof String)
          list.add((String)item);
      });
      obj.setFields(list);
    }
    if (json.getValue("retryOnConflict") instanceof Number) {
      obj.setRetryOnConflict(((Number)json.getValue("retryOnConflict")).intValue());
    }
    if (json.getValue("script") instanceof JsonObject) {
      obj.setScript(new fr.myprysm.vertx.elasticsearch.action.support.Script((JsonObject)json.getValue("script")));
    }
    if (json.getValue("scriptedUpsert") instanceof Boolean) {
      obj.setScriptedUpsert((Boolean)json.getValue("scriptedUpsert"));
    }
    if (json.getValue("upsert") instanceof JsonObject) {
      obj.setUpsert(((JsonObject)json.getValue("upsert")).copy());
    }
    if (json.getValue("waitForActiveShards") instanceof Number) {
      obj.setWaitForActiveShards(((Number)json.getValue("waitForActiveShards")).intValue());
    }
  }

  public static void toJson(UpdateRequest obj, JsonObject json) {
    json.put("detectNoop", obj.isDetectNoop());
    if (obj.getDoc() != null) {
      json.put("doc", obj.getDoc());
    }
    json.put("docAsUpsert", obj.isDocAsUpsert());
    if (obj.getFetchSourceContext() != null) {
      json.put("fetchSourceContext", obj.getFetchSourceContext().toJson());
    }
    if (obj.getFields() != null) {
      JsonArray array = new JsonArray();
      obj.getFields().forEach(item -> array.add(item));
      json.put("fields", array);
    }
    json.put("retryOnConflict", obj.getRetryOnConflict());
    if (obj.getScript() != null) {
      json.put("script", obj.getScript().toJson());
    }
    json.put("scriptedUpsert", obj.isScriptedUpsert());
    if (obj.getUpsert() != null) {
      json.put("upsert", obj.getUpsert());
    }
    if (obj.getWaitForActiveShards() != null) {
      json.put("waitForActiveShards", obj.getWaitForActiveShards());
    }
  }
}