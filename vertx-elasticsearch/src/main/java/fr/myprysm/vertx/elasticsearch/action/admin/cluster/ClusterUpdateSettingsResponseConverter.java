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

package fr.myprysm.vertx.elasticsearch.action.admin.cluster;

import io.vertx.core.json.JsonObject;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse}.
 * <p>
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse} original class using Vert.x codegen.
 */
public class ClusterUpdateSettingsResponseConverter {

    public static void fromJson(JsonObject json, ClusterUpdateSettingsResponse obj) {
        if (json.getValue("persistent") instanceof JsonObject) {
            obj.setPersistentSettings(((JsonObject) json.getValue("persistent")).copy());
        }

        if (json.getValue("persistentSettings") instanceof JsonObject) {
            obj.setPersistentSettings(((JsonObject) json.getValue("persistentSettings")).copy());
        }

        if (json.getValue("transient") instanceof JsonObject) {
            obj.setTransientSettings(((JsonObject) json.getValue("transient")).copy());
        }

        if (json.getValue("transientSettings") instanceof JsonObject) {
            obj.setTransientSettings(((JsonObject) json.getValue("transientSettings")).copy());
        }
    }

    public static void toJson(ClusterUpdateSettingsResponse obj, JsonObject json) {
        if (obj.getPersistentSettings() != null) {
            json.put("persistentSettings", obj.getPersistentSettings());
        }
        if (obj.getTransientSettings() != null) {
            json.put("transientSettings", obj.getTransientSettings());
        }
    }
}