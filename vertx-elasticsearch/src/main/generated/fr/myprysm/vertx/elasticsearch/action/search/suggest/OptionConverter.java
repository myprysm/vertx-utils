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
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.search.suggest.Option}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.search.suggest.Option} original class using Vert.x codegen.
 */
public class OptionConverter {

  public static void fromJson(JsonObject json, Option obj) {
    if (json.getValue("collateMatch") instanceof Boolean) {
      obj.setCollateMatch((Boolean)json.getValue("collateMatch"));
    }
    if (json.getValue("highlighted") instanceof String) {
      obj.setHighlighted((String)json.getValue("highlighted"));
    }
    if (json.getValue("score") instanceof Number) {
      obj.setScore(((Number)json.getValue("score")).floatValue());
    }
    if (json.getValue("text") instanceof String) {
      obj.setText((String)json.getValue("text"));
    }
  }

  public static void toJson(Option obj, JsonObject json) {
    if (obj.getCollateMatch() != null) {
      json.put("collateMatch", obj.getCollateMatch());
    }
    if (obj.getHighlighted() != null) {
      json.put("highlighted", obj.getHighlighted());
    }
    json.put("score", obj.getScore());
    if (obj.getText() != null) {
      json.put("text", obj.getText());
    }
  }
}