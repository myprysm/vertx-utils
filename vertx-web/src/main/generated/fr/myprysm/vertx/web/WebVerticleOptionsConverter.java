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

package fr.myprysm.vertx.web;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.web.WebVerticleOptions}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.web.WebVerticleOptions} original class using Vert.x codegen.
 */
public class WebVerticleOptionsConverter {

  public static void fromJson(JsonObject json, WebVerticleOptions obj) {
    if (json.getValue("cors") instanceof JsonObject) {
      obj.setCors(new fr.myprysm.vertx.web.CorsOptions((JsonObject)json.getValue("cors")));
    }
    if (json.getValue("enableCors") instanceof Boolean) {
      obj.setEnableCors((Boolean)json.getValue("enableCors"));
    }
    if (json.getValue("enableHealthChecks") instanceof Boolean) {
      obj.setEnableHealthChecks((Boolean)json.getValue("enableHealthChecks"));
    }
    if (json.getValue("enableMetrics") instanceof Boolean) {
      obj.setEnableMetrics((Boolean)json.getValue("enableMetrics"));
    }
    if (json.getValue("monitoringPath") instanceof String) {
      obj.setMonitoringPath((String)json.getValue("monitoringPath"));
    }
    if (json.getValue("specs") instanceof String) {
      obj.setSpecs((String)json.getValue("specs"));
    }
    if (json.getValue("useOpenAPI3Router") instanceof Boolean) {
      obj.setUseOpenAPI3Router((Boolean)json.getValue("useOpenAPI3Router"));
    }
  }

  public static void toJson(WebVerticleOptions obj, JsonObject json) {
    if (obj.getCors() != null) {
      json.put("cors", obj.getCors().toJson());
    }
    if (obj.getEnableCors() != null) {
      json.put("enableCors", obj.getEnableCors());
    }
    if (obj.getEnableHealthChecks() != null) {
      json.put("enableHealthChecks", obj.getEnableHealthChecks());
    }
    if (obj.getEnableMetrics() != null) {
      json.put("enableMetrics", obj.getEnableMetrics());
    }
    if (obj.getMonitoringPath() != null) {
      json.put("monitoringPath", obj.getMonitoringPath());
    }
    if (obj.getSpecs() != null) {
      json.put("specs", obj.getSpecs());
    }
    if (obj.getUseOpenAPI3Router() != null) {
      json.put("useOpenAPI3Router", obj.getUseOpenAPI3Router());
    }
  }
}