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

import fr.myprysm.vertx.validation.JsonValidation;
import fr.myprysm.vertx.validation.ValidationResult;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static fr.myprysm.vertx.validation.JsonValidation.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@DataObject(generateConverter = true)
public class VerticleOptions {
    private String verticle;
    private String name;
    private DeploymentOptions options = new DeploymentOptions();

    public VerticleOptions(VerticleOptions other) {
        verticle = other.verticle;
        name = other.name;
        options = other.options;
    }

    public VerticleOptions(JsonObject json) {
        VerticleOptionsConverter.fromJson(json, this);
    }

    /**
     * Validates a {@link VerticleOptions}.
     * <p>
     * It must contain a valid java class in the field <code>verticle</code>
     * and an optional JSON object in the field <code>config</code>
     *
     * @param config the options as a {@link JsonObject}
     * @return the validation result
     */
    public static ValidationResult validate(JsonObject config) {
        return isClass("verticle")
                .and(isNull("options").or(isObject("options").and(nested("options", VerticleOptions::validateOptions))))
                .apply(config);
    }

    /**
     * Validates Vert.x DeploymentOptions
     *
     * @return the validation chain
     */
    static JsonValidation validateOptions() {
        return isNull("worker").or(isBoolean("worker"))
                .and(isNull("multiThreaded").or(isBoolean("multiThreaded")))
                .and(isNull("isolationGroup").or(isString("isolationGroup")))
                .and(isNull("workerPoolName").or(isString("workerPoolName")))
                .and(isNull("workerPoolSize").or(gt("workerPoolSize", 0L)))
                .and(isNull("maxWorkerExecuteTime").or(gt("maxWorkerExecuteTime", 0L)))
                .and(isNull("ha").or(isBoolean("ha")))
                .and(isNull("extraClasspath").or(arrayOf("extraClasspath", String.class)))
                .and(isNull("instances").or(gt("instances", 0L)))
                .and(isNull("isolatedClasses").or(arrayOf("isolatedClasses", String.class)))
                .and(isNull("config").or(isObject("config")));
    }

    public VerticleOptions setVerticle(String verticle) {
        this.verticle = verticle;
        return this;
    }

    public VerticleOptions setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the verticle options.
     * <p>
     * This expects a Vert.x <code>DeploymentOptions</code> JSON object
     *
     * @param options the options
     * @return this
     */
    public VerticleOptions setOptions(DeploymentOptions options) {
        this.options = options;
        return this;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        VerticleOptionsConverter.toJson(this, json);
        return json;
    }

}
