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

import fr.myprysm.vertx.core.StarterVerticle;
import fr.myprysm.vertx.core.utils.ClassUtils;
import fr.myprysm.vertx.test.QuickAssert;
import fr.myprysm.vertx.test.VertxTest;
import fr.myprysm.vertx.validation.ValidationException;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.Checkpoint;
import io.vertx.junit5.VertxTestContext;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.types.EventBusService;
import io.vertx.serviceproxy.ServiceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Service Verticle tests")
class ServiceVerticleTest implements VertxTest, QuickAssert {

    public static final String VERTICLE = "fr.myprysm.vertx.service.ServiceVerticle";
    public static final DeploymentOptions OPTIONS = new DeploymentOptions();
    private String verticleId;

    @BeforeAll
    static void loadClasses(Vertx vertx, VertxTestContext ctx) {
        vertx.executeBlocking(future -> {
            ClassUtils.getScan();
            future.complete();
        }, ctx.succeeding(zoid -> ctx.completeNow()));
    }

    @AfterEach
    void tearDownVerticle(Vertx vertx, VertxTestContext ctx) {
        if (verticleId != null && vertx.deploymentIDs().contains(verticleId)) {
            vertx.undeploy(verticleId, ar -> ctx.completeNow());
            verticleId = null;
        } else {
            verticleId = null;
            ctx.completeNow();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "services/invalid/bad-facade.json",
            "services/invalid/bad-facade-not-interface.json",
            "services/invalid/bad-factory-method.json",
            "services/invalid/bad-factory-method-signature.json",
            "services/invalid/bad-implementation.json",
            "services/invalid/no-address.json",
            "services/invalid/no-facade.json",
            "services/invalid/no-implementation.json",
            "services/invalid/no-name.json"
    })
    @DisplayName("it should not start with invalid options")
    void itShouldNotStartWithInvalidOptions(String config, Vertx vertx, VertxTestContext ctx) {

        vertx.deployVerticle(VERTICLE, OPTIONS.setConfig(objectFromFile(config)), assertFail(err -> {
            assertThat(err).isInstanceOf(ValidationException.class);
            ctx.completeNow();
        }, null, ctx));


    }

    @Test
    @DisplayName("it should start with valid options")
    void itShouldStartWithValidOptions(Vertx vertx, VertxTestContext ctx) {
        JsonObject config;
        Checkpoint cp = ctx.checkpoint();

        config = objectFromFile("services/valid/default.json");
        ServiceVerticle verticle = new ServiceVerticle();
        vertx.deployVerticle(verticle, OPTIONS.setConfig(config), assertSuccess(id -> {
            verticleId = id;
            assertThat(verticle.getBinds()).size().isEqualTo(2);
            assertThat(verticle.getRecords()).size().isEqualTo(2);
            assertThat(verticle.getServices()).size().isEqualTo(2);
            cp.flag();
        }, null, ctx));
    }

    @Test
    @DisplayName("it should provide a health check for services implementing the interface")
    void itShouldProvideHCForImplementingServices(Vertx vertx, VertxTestContext ctx) {
        JsonObject config;
        Checkpoint cp = ctx.checkpoint();

        config = objectFromFile("services/valid/default.json");
        vertx.deployVerticle(VERTICLE, OPTIONS.setConfig(config), ctx.succeeding(id -> {
            verticleId = id;
            StarterVerticle.getHealthChecks(new io.vertx.reactivex.core.Vertx(vertx))
                    .invoke("simple-success-service", ctx.succeeding(data -> ctx.verify(() -> {

                        assertThat(data.getString("id")).isEqualTo("simple-success-service");
                        assertThat(data.getString("status")).isEqualTo("UP");
                        cp.flag();
                    })));

        }));
    }


    @Test
    @DisplayName("Health Check should fail when timeout exceeded")
    void itShouldFailWhenHCTimeoutExceeded(Vertx vertx, VertxTestContext ctx) {
        JsonObject config = objectFromFile("services/valid/health-check-timeout.json");
        vertx.deployVerticle(VERTICLE, OPTIONS.setConfig(config), assertSuccess(id -> {
            verticleId = id;
            StarterVerticle.getHealthChecks(new io.vertx.reactivex.core.Vertx(vertx))
                    .invoke("delay-health-check-service", assertSuccess(data -> {

                        assertThat(data.getString("id")).isEqualTo("delay-health-check-service");
                        assertThat(data.getString("status")).isEqualTo("DOWN");
                        ctx.completeNow();
                    }, null, ctx));
        }, null, ctx));
    }


    @Test
    @DisplayName("Not Implemented should return 900")
    void itShouldReturnNineHundredWhenNotImplemented(Vertx vertx, VertxTestContext ctx) {
        JsonObject config = objectFromFile("services/valid/failure-service.json");
        vertx.deployVerticle(VERTICLE, OPTIONS.setConfig(config), assertSuccess(id -> {
            verticleId = id;
            ServiceDiscovery.create(vertx, discovery -> {
                EventBusService.getServiceProxyWithJsonFilter(discovery,
                        new JsonObject().put("name", "failure-service"),
                        SimpleService.class,
                        assertSuccess(service -> {
                            service.asyncOperation(assertFail(err -> {
                                assertThat(err).isInstanceOf(ServiceException.class);
                                assertThat(((ServiceException) err).failureCode()).isEqualTo(900);
                                assertThat(err.getMessage()).isEqualTo("Method not implemented");
                                ctx.completeNow();
                            }, null, ctx));
                        }, null, ctx));
            });
        }, null, ctx));
    }

    @Test
    @DisplayName("Verticle Options...")
    void serviceVerticleOptions() {
        ServiceVerticleOptions options = new ServiceVerticleOptions().setServices(new HashMap<>());
        ServiceVerticleOptions otherOptions = new ServiceVerticleOptions(new HashMap<>());
        assertThat(options).isEqualTo(new ServiceVerticleOptions(options));
        assertThat(options).isEqualTo(otherOptions);
        assertThat(options.toJson()).isEqualTo(otherOptions.toJson());
        assertThat(options.hashCode()).isEqualTo(otherOptions.hashCode());
        assertThat(options.toString()).isEqualTo(otherOptions.toString());

    }

    @Test
    @DisplayName("Service Options...")
    void serviceOptions() {
        ServiceOptions options = new ServiceOptions()
                .setName("name")
                .setAddress("address")
                .setFacade("facade")
                .setImplementation("implementation")
                .setFactoryMethod("method")
                .setRegister(false)
                .setDiscovery(false)
                .setHealthCheck(false)
                .setHealthCheckName("health-check")
                .setHealthCheckTimeout(100L);

        ServiceOptions otherOptions = new ServiceOptions(
                "name",
                "address",
                "facade",
                "implementation",
                "method",
                false,
                false,
                false,
                "health-check",
                100L
        );

        assertThat(options).isEqualTo(new ServiceOptions(options));
        assertThat(options.toJson()).isEqualTo(otherOptions.toJson());
    }
}
