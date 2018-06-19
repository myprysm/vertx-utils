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

import com.google.common.collect.ImmutableSet;
import fr.myprysm.vertx.validation.ValidationResult;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

import static fr.myprysm.vertx.json.JsonHelpers.obj;
import static fr.myprysm.vertx.validation.JsonValidation.*;

/**
 * See Vert.x documentation for Cross Origin Resource Sharing.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class CorsOptions {

    private String allowedOrigins = "*";
    private Set<HttpMethod> allowedMethods = ImmutableSet.of(HttpMethod.GET);
    private Set<String> allowedHeaders = new HashSet<>();
    private Set<String> exposedHeaders = new HashSet<>();
    private Integer maxAgeSeconds = -1;
    private Boolean allowCredentials;

    public CorsOptions(CorsOptions other) {
        allowedOrigins = other.allowedOrigins;
        allowedMethods = other.allowedMethods;
        allowedHeaders = other.allowedHeaders;
        exposedHeaders = other.exposedHeaders;
        maxAgeSeconds = other.maxAgeSeconds;
        allowCredentials = other.allowCredentials;
    }

    public CorsOptions(JsonObject json) {
        CorsOptionsConverter.fromJson(json, this);
    }

    public static ValidationResult validate(JsonObject json) {
        return isNull("allowedOrigins").or(isString("allowedOrigins"))
                .and(isNull("allowedMethods").or(isEnum("allowedMethods", HttpMethod.class)))
                .and(isNull("allowedHeaders").or(arrayOf("allowedHeaders", String.class)))
                .and(isNull("exposedHeaders").or(arrayOf("exposedHeaders", String.class)))
                .and(isNull("maxAgeSeconds").or(gte("maxAgeSeconds", -1L)))
                .apply(json);
    }

    public JsonObject toJson() {
        JsonObject json = obj();
        CorsOptionsConverter.toJson(this, json);
        return json;
    }

}
