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

import fr.myprysm.vertx.core.utils.ClassUtils;
import fr.myprysm.vertx.validation.JsonValidation;
import fr.myprysm.vertx.validation.ValidationResult;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Modifier;
import java.util.concurrent.atomic.AtomicReference;

import static fr.myprysm.vertx.validation.JsonValidation.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DataObject(generateConverter = true)
public class ServiceOptions {

    private static final Long DEFAULT_HEALTHCHECK_TIMEOUT = 1000L;
    private String name;
    private String address;
    private String facade;
    private String implementation;
    private String factoryMethod;
    private Boolean register = true;
    private Boolean discovery = true;
    private Boolean healthCheck = true;
    private String healthCheckName;
    private Long healthCheckTimeout = DEFAULT_HEALTHCHECK_TIMEOUT;


    public ServiceOptions(ServiceOptions other) {
        name = other.name;
        address = other.address;
        facade = other.facade;
        implementation = other.implementation;
        factoryMethod = other.factoryMethod;
        register = other.register;
        discovery = other.discovery;
        healthCheck = other.healthCheck;
        healthCheckName = other.healthCheckName;
        healthCheckTimeout = other.healthCheckTimeout;
    }

    public ServiceOptions(JsonObject json) {
        ServiceOptionsConverter.fromJson(json, this);
    }

    /**
     * Validates a {@link ServiceOptions}.
     * <p>
     * It must contain a valid java class in the field <code>verticle</code>
     * and an optional JSON object in the field <code>config</code>
     *
     * @param options the options as a {@link JsonObject}
     * @return the validation result
     */
    public static ValidationResult validate(JsonObject options) {
        return isString("address")
                .and(validateFacade())
                .and(hasFactoryOrImplementation())
                .and(validateFactoryMethod(options))
                .and(validateImplementation(options))
                .and(validateFlags())
                .and(validateHealthChecks())
                .apply(options);
    }

    /**
     * Validates that the facade is a public interface
     *
     * @return the validation chain
     */
    private static JsonValidation validateFacade() {
        return isClass("facade")
                .and(() -> holds(json ->
                                Modifier.isInterface(ClassUtils.getClass(json.getString("facade")).getModifiers()),
                        () -> message("facade", "is not an interface"))
                );
    }

    /**
     * Validate the Health Check options
     *
     * @return the chain
     */
    private static JsonValidation validateHealthChecks() {
        return isNull("healthCheckName").or(isString("healthCheckName"))
                .and(isNull("healthCheckTimeout").or(isLong("healthCheckTimeout")));
    }

    /**
     * Validate the flags
     *
     * @return the chain
     */
    private static JsonValidation validateFlags() {
        return isNull("register").or(isBoolean("register"))
                .and(isNull("discovery").or(isBoolean("discovery")))
                .and(isNull("healthCheck").or(isBoolean("healthCheck")));
    }

    /**
     * Ensures whether factoryMethod or implementation is provided
     *
     * @return the chain
     */
    private static JsonValidation hasFactoryOrImplementation() {
        return isString("factoryMethod").or(isString("implementation"));
    }

    /**
     * When using implementation a service <b>must</b> extend both facade interface and {@link Service} interface.
     *
     * @param options the options
     * @return the chain
     */
    private static JsonValidation validateImplementation(JsonObject options) {
        AtomicReference<String> message = new AtomicReference<>();
        return isNull("implementation")
                .or(isClass("implementation").and(holds(json -> {
                    Class<?> implementation = ClassUtils.getClass(json.getString("implementation"));
                    Class<?> facade = ClassUtils.getClass(json.getString("facade"));
                    if (!(facade.isAssignableFrom(implementation) && Service.class.isAssignableFrom(implementation))) {
                        String reason = "Implementation '" +
                                implementation.getName() +
                                "' is not an implementation of " +
                                facade.getName() +
                                " or an implementation of " + Service.class.getName();
                        message.set(reason);
                        return false;
                    }
                    return true;
                }, message::get)));
    }

    /**
     * Validates that the facade has a factory method as described per Vert.x conventions
     * with Vertx and Handler as parameters.
     *
     * @param options the options
     * @return the chain
     */
    private static JsonValidation validateFactoryMethod(JsonObject options) {
        return isNull("factoryMethod")
                .or(() -> ClassUtils.hasStaticMethod(
                        ClassUtils.getClass(options.getString("facade")),
                        options.getString("factoryMethod"),
                        // Class params
                        Vertx.class, Handler.class
                ));
    }

    /**
     * The name of the service for service discovery.
     *
     * @param name the name of the service
     * @return this
     */
    public ServiceOptions setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * The event bus address on which the service will be registered.
     * <p>
     * Must not be blank or null.
     *
     * @param address the address
     * @return this
     */
    public ServiceOptions setAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * The Service facade/interface name.
     *
     * @param facade the class name
     * @return this
     */
    public ServiceOptions setFacade(String facade) {
        this.facade = facade;
        return this;
    }

    /**
     * The Service implementation name.
     * <p>
     * The class must implement the provided <code>facade</code>.
     *
     * @param implementation the class name
     * @return this
     */
    public ServiceOptions setImplementation(String implementation) {
        this.implementation = implementation;
        return this;
    }

    /**
     * The factory method.
     * <p>
     * This should the name of a static method available on the <code>facade</code> class,
     * accepting as arguments <code>Vertx</code> and a <code>Handler</code>
     * that will receive the configured service.
     *
     * @param factoryMethod the method name
     * @return this
     */
    public ServiceOptions setFactoryMethod(String factoryMethod) {
        this.factoryMethod = factoryMethod;
        return this;
    }

    /**
     * Indicates whether the service will be registered.
     * <p>
     * Please note that this behaviour is enabled by default.
     * You have to disable it through configuration.
     * <p>
     * This disables also <code>discovery</code>.
     *
     * @param register <code>false</code> to disable registration
     * @return this
     */
    public ServiceOptions setRegister(Boolean register) {
        this.register = register;
        return this;
    }

    /**
     * Indicates whether the service will be registered on Vert.x Service Discovery.
     * <p>
     * Please note that this behaviour is enabled by default.
     * You have
     *
     * @param discovery <code>false</code> to disable discovery
     * @return this
     */
    public ServiceOptions setDiscovery(Boolean discovery) {
        this.discovery = discovery;
        return this;
    }

    /**
     * Indicates whether Health Check is enabled for the service.
     * <p>
     * This does not apply to classic Vert.x services instanciated with the <code>factoryMethod</code>.
     * This does not apply to a <code>Service</code> that does not implement <code>HealthCheck</code> interface.
     *
     * @param healthCheck <code>false</code> when a health check must be disabled for a <code>Service</code>
     * @return this
     */
    public ServiceOptions setHealthCheck(Boolean healthCheck) {
        this.healthCheck = healthCheck;
        return this;
    }

    /**
     * Custom health check name.
     * <p>
     * It allows to change the default name configured for a <code>Service</code> health check.
     * <p>
     * It is automatically bound to Vert.x Health Check so name can contain "/" to group
     * results in a single <code>JsonObject</code>
     *
     * @param healthCheckName the health check name.
     * @return this
     */
    public ServiceOptions setHealthCheckName(String healthCheckName) {
        this.healthCheckName = healthCheckName;
        return this;
    }

    /**
     * Custom health check timeout.
     * <p>
     * Must be a positive value, representing a duration in milliseconds.
     * Default is 1 second (1000 milliseconds).
     *
     * @param healthCheckTimeout the health check timeout.
     * @return this
     */
    public ServiceOptions setHealthCheckTimeout(Long healthCheckTimeout) {
        this.healthCheckTimeout = healthCheckTimeout;
        return this;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        ServiceOptionsConverter.toJson(this, json);
        return json;
    }

}
