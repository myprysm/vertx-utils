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

package fr.myprysm.vertx.service;

import fr.myprysm.vertx.validation.JsonValidation;
import fr.myprysm.vertx.validation.ValidationResult;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static fr.myprysm.vertx.validation.JsonValidation.holds;
import static fr.myprysm.vertx.validation.JsonValidation.nested;
import static fr.myprysm.vertx.validation.ValidationResult.valid;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DataObject(generateConverter = true)
public class ServiceVerticleOptions {

    private Map<String, ServiceOptions> services;


    public ServiceVerticleOptions(ServiceVerticleOptions other) {
        services = other.services;
    }

    public ServiceVerticleOptions(JsonObject json) {
        ServiceVerticleOptionsConverter.fromJson(json, this);
    }

    /**
     * Validates a {@link ServiceVerticleOptions}.
     * <p>
     * It must contain a valid java class in the field <code>verticle</code>
     * and an optional JSON object in the field <code>config</code>
     *
     * @param options the options as a {@link JsonObject}
     * @return the validation result
     */
    public static ValidationResult validate(JsonObject options) {
        return nested("services", validateServices()).apply(options);

    }

    /**
     * Validates the services. Stops on first error.
     *
     * @return the chain
     */
    private static JsonValidation validateServices() {
        AtomicReference<String> message = new AtomicReference<>();
        return holds(json -> {
            ValidationResult serviceValidation = json.fieldNames()
                    .stream()
                    .map(field -> nested(field, ServiceOptions::validate).apply(json))
                    .filter(result -> !result.isValid())
                    .findFirst()
                    .orElse(valid());

            if (!serviceValidation.isValid()) {
                message.set(serviceValidation.getReason().get());
            }
            return serviceValidation.isValid();
        }, message::get);
    }

    /**
     * The service map to start with the <code>ServiceVerticle</code>
     *
     * @param services the services to register
     * @return this
     */
    public ServiceVerticleOptions setServices(Map<String, ServiceOptions> services) {
        this.services = services;
        return this;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        ServiceVerticleOptionsConverter.toJson(this, json);
        return json;
    }
}
