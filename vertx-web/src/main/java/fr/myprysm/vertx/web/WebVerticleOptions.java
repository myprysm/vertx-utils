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

import fr.myprysm.vertx.validation.JsonValidation;
import fr.myprysm.vertx.validation.ValidationResult;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static fr.myprysm.vertx.json.JsonHelpers.obj;
import static fr.myprysm.vertx.validation.JsonValidation.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class WebVerticleOptions {

    public static final String DEFAULT_PATH = "/__";
    private static final String PATH_MESSAGE = "Path must start with a \"/\" and contain only letters, numbers, \"-\", \"_\", \".\", \"+\"";
    private Boolean useOpenAPI3Router = false;
    private String specs;
    private Boolean enableCors = false;
    private CorsOptions cors = new CorsOptions();
    private Boolean enableMetrics = false;
    private Boolean enableHealthChecks = false;
    private String monitoringPath = DEFAULT_PATH;

    public WebVerticleOptions(WebVerticleOptions other) {
        useOpenAPI3Router = other.useOpenAPI3Router;
        specs = other.specs;
        enableCors = other.enableCors;
        cors = other.cors;
        enableMetrics = other.enableMetrics;
        enableHealthChecks = other.enableHealthChecks;
        monitoringPath = other.monitoringPath;
    }

    public WebVerticleOptions(JsonObject json) {
        WebVerticleOptionsConverter.fromJson(json, this);
    }

    /**
     * Validates the input json is a valid WebVerticleOption.
     *
     * @param json the input json
     * @return the result
     */
    public static ValidationResult validate(JsonObject json) {
        return validateOpenAPI()
                .and(WebVerticleOptions::validateCorsOptions)
                .and(isNull("enableMetrics").or(isBoolean("enableMetrics")))
                .and(isNull("enableHealthChecks").or(isBoolean("enableHealthChecks")))
                .and(isNull("monitoringPath").or(matches("monitoringPath", "/.[a-zA-Z0-9\\-\\.\\+_/]+", PATH_MESSAGE)))
                .apply(json);
    }


    /**
     * Validates Cross Origin Resource Sharing.
     *
     * @return the chain
     */
    private static JsonValidation validateCorsOptions() {
        return isNull("enableCors").or(isBoolean("enableCors", false))
                .or(CorsOptions::validate);
    }

    /**
     * Validates OpenAPI 3 configuration.
     * <p>
     * Whenever it is activated it must provide specs file.
     *
     * @return the chain
     */
    private static JsonValidation validateOpenAPI() {
        return isNull("useOpenAPI3Router").or(isBoolean("useOpenAPI3Router", false))
                .or(isBoolean("useOpenAPI3Router", true).and(isString("specs", "Specs url should be provided with OpenAPI 3.0 support")));
    }

    public JsonObject toJson() {
        JsonObject json = obj();
        WebVerticleOptionsConverter.toJson(this, json);
        return json;
    }
}
