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

package fr.myprysm.vertx.elasticsearch.action.search;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponseItem}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponseItem} original class using Vert.x codegen.
 */
public class MultiSearchResponseItemConverter {

  public static void fromJson(JsonObject json, MultiSearchResponseItem obj) {
    if (json.getValue("failure") instanceof JsonObject) {
      obj.setFailure(new fr.myprysm.vertx.elasticsearch.action.support.Failure((JsonObject)json.getValue("failure")));
    }
    if (json.getValue("response") instanceof JsonObject) {
      obj.setResponse(new fr.myprysm.vertx.elasticsearch.action.search.SearchResponse((JsonObject)json.getValue("response")));
    }
  }

  public static void toJson(MultiSearchResponseItem obj, JsonObject json) {
    if (obj.getFailure() != null) {
      json.put("failure", obj.getFailure().toJson());
    }
    if (obj.getResponse() != null) {
      json.put("response", obj.getResponse().toJson());
    }
  }
}