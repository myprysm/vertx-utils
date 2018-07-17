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

package fr.myprysm.vertx.elasticsearch.action.search.suggest;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.search.suggest.CompletionOption}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.search.suggest.CompletionOption} original class using Vert.x codegen.
 */
public class CompletionOptionConverter {

  public static void fromJson(JsonObject json, CompletionOption obj) {
    if (json.getValue("doc") instanceof JsonObject) {
      obj.setDoc(new fr.myprysm.vertx.elasticsearch.action.support.ScoreDoc((JsonObject)json.getValue("doc")));
    }
    if (json.getValue("hit") instanceof JsonObject) {
      obj.setHit(new fr.myprysm.vertx.elasticsearch.action.search.SearchHit((JsonObject)json.getValue("hit")));
    }
  }

  public static void toJson(CompletionOption obj, JsonObject json) {
    if (obj.getDoc() != null) {
      json.put("doc", obj.getDoc().toJson());
    }
    if (obj.getHit() != null) {
      json.put("hit", obj.getHit().toJson());
    }
  }
}