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
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.support.ScoreDoc}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.support.ScoreDoc} original class using Vert.x codegen.
 */
public class ScoreDocConverter {

  public static void fromJson(JsonObject json, ScoreDoc obj) {
    if (json.getValue("doc") instanceof Number) {
      obj.setDoc(((Number)json.getValue("doc")).intValue());
    }
    if (json.getValue("score") instanceof Number) {
      obj.setScore(((Number)json.getValue("score")).floatValue());
    }
    if (json.getValue("shardIndex") instanceof Number) {
      obj.setShardIndex(((Number)json.getValue("shardIndex")).intValue());
    }
  }

  public static void toJson(ScoreDoc obj, JsonObject json) {
    json.put("doc", obj.getDoc());
    json.put("score", obj.getScore());
    json.put("shardIndex", obj.getShardIndex());
  }
}