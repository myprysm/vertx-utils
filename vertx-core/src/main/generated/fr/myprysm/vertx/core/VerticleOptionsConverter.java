/*
 * Copyright 2018 the original author or the original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.myprysm.vertx.core;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.core.VerticleOptions}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.core.VerticleOptions} original class using Vert.x codegen.
 */
public class VerticleOptionsConverter {

  public static void fromJson(JsonObject json, VerticleOptions obj) {
    if (json.getValue("name") instanceof String) {
      obj.setName((String)json.getValue("name"));
    }
    if (json.getValue("options") instanceof JsonObject) {
      obj.setOptions(new io.vertx.core.DeploymentOptions((JsonObject)json.getValue("options")));
    }
    if (json.getValue("verticle") instanceof String) {
      obj.setVerticle((String)json.getValue("verticle"));
    }
  }

  public static void toJson(VerticleOptions obj, JsonObject json) {
    if (obj.getName() != null) {
      json.put("name", obj.getName());
    }
    if (obj.getOptions() != null) {
      json.put("options", obj.getOptions().toJson());
    }
    if (obj.getVerticle() != null) {
      json.put("verticle", obj.getVerticle());
    }
  }
}