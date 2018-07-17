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
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.search.suggest.Entry}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.search.suggest.Entry} original class using Vert.x codegen.
 */
public class EntryConverter {

  public static void fromJson(JsonObject json, Entry obj) {
    if (json.getValue("length") instanceof Number) {
      obj.setLength(((Number)json.getValue("length")).intValue());
    }
    if (json.getValue("offset") instanceof Number) {
      obj.setOffset(((Number)json.getValue("offset")).intValue());
    }
    if (json.getValue("text") instanceof String) {
      obj.setText((String)json.getValue("text"));
    }
  }

  public static void toJson(Entry obj, JsonObject json) {
    json.put("length", obj.getLength());
    json.put("offset", obj.getOffset());
    if (obj.getText() != null) {
      json.put("text", obj.getText());
    }
  }
}