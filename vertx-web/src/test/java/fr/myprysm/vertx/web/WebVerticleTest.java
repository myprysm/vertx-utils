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
import fr.myprysm.vertx.core.test.ConfigTestHelper;
import fr.myprysm.vertx.test.VertxTest;
import io.reactivex.Completable;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.Checkpoint;
import io.vertx.junit5.VertxTestContext;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DisplayName("WebVerticle tests")
class WebVerticleTest implements VertxTest {
    private static final String VERTICLE = "fr.myprysm.vertx.web.WebVerticle";
    private static final String TEST_MONITORING = "/TEST/__";
    private static final WebVerticleOptions DEFAULTS = new WebVerticleOptions()
            .setEnableHealthChecks(true)
            .setEnableMetrics(true)
            .setEnableCors(true)
            .setMonitoringPath(TEST_MONITORING)
            .setUseOpenAPI3Router(true)
            .setSpecs("web/swagger-minimal.yml");
    private String deploymentId;

    static Handler<HttpClientResponse> pageFound(VertxTestContext ctx, Checkpoint cp) {
        return response -> ctx.verify(() -> {
            assertThat(response.statusCode()).isBetween(200, 204);
            cp.flag();
        });
    }

    static Handler<HttpClientResponse> pageFoundAndJson(VertxTestContext ctx, Checkpoint cp) {
        return response -> ctx.verify(() -> {
            assertThat(response.statusCode()).isBetween(200, 204);
            assertThat(response.getHeader("content-type")).isEqualToIgnoringCase("application/json");
            assertThat(response.getHeader("content-encoding")).isEqualToIgnoringCase("UTF-8");
            cp.flag();
        });
    }

    @AfterEach
    void shutdownVerticle(Vertx vertx, VertxTestContext ctx) {
        if (deploymentId != null) {
            vertx.undeploy(deploymentId, zoid -> {
                deploymentId = null;
                ctx.completeNow();
            });
        } else {
            ctx.completeNow();
        }
    }

    @Test
    @DisplayName("WebVerticle should start metrics and monitoring with OpenAPI3.0 support")
    void itShouldStartMetricsAndMonitoringWithOpenApiSupport(Vertx vertx, VertxTestContext ctx) {
        ConfigTestHelper.load(vertx, ctx, "web/web-config-openapi.yml", (_configService, _csConsumer) -> {

            vertx.deployVerticle(VERTICLE, new DeploymentOptions().setConfig(DEFAULTS.toJson()), ctx.succeeding(id -> {
                deploymentId = id;
                HttpClient client = vertx.createHttpClient(new HttpClientOptions().setDefaultPort(9001));
                Checkpoint cp = ctx.checkpoint(6);


                client.getNow("/operation", pageFoundAndJson(ctx, cp));
                client.getNow("/swagger.json", pageFoundAndJson(ctx, cp));
                client.getNow(TEST_MONITORING + "/hc", pageFound(ctx, cp));
                client.getNow(TEST_MONITORING + "/metrics/vertx", pageFound(ctx, cp));
                client.getNow(TEST_MONITORING + "/metrics/requests", pageFound(ctx, cp));
                client.getNow(TEST_MONITORING + "/metrics/hystrix", pageFound(ctx, cp));

                _csConsumer.unregister(ctx.succeeding(zoid -> cp.flag()));
            }));
        });
    }

    @Test
    @DisplayName("WebVerticle should start metrics and monitoring with classic endpoints")
    void itShouldStartMetricsAndMonitoringWithClassicEndpoints(Vertx vertx, VertxTestContext ctx) {
        ConfigTestHelper.load(vertx, ctx, "web/web-config-classic.yml", (_configService, _csConsumer) -> {
            vertx.deployVerticle(VERTICLE, new DeploymentOptions().setConfig(new WebVerticleOptions(DEFAULTS).setUseOpenAPI3Router(false).toJson()), ctx.succeeding(id -> {
                deploymentId = id;
                HttpClient client = vertx.createHttpClient(new HttpClientOptions().setDefaultPort(9002));
                Checkpoint cp = ctx.checkpoint(5);


                client.getNow("/simple/route", pageFoundAndJson(ctx, cp));
                client.getNow(TEST_MONITORING + "/hc", pageFound(ctx, cp));
                client.getNow(TEST_MONITORING + "/metrics/vertx", pageFound(ctx, cp));
                client.getNow(TEST_MONITORING + "/metrics/requests", pageFound(ctx, cp));
                client.getNow(TEST_MONITORING + "/metrics/hystrix", pageFound(ctx, cp));

                _csConsumer.unregister(ctx.succeeding(zoid -> cp.flag()));
            }));
        });
    }

    @Test
    @DisplayName("CORS Options...")
    void corsOptions() {
        CorsOptions options = new CorsOptions()
                .setAllowedOrigins("some origin")
                .setAllowedMethods(ImmutableSet.of(HttpMethod.POST))
                .setAllowedHeaders(ImmutableSet.of("x-custom-header"))
                .setExposedHeaders(ImmutableSet.of("x-custom-header"))
                .setMaxAgeSeconds(200)
                .setAllowCredentials(true);

        CorsOptions otherOptions = new CorsOptions(
                "some origin",
                ImmutableSet.of(HttpMethod.POST),
                ImmutableSet.of("x-custom-header"),
                ImmutableSet.of("x-custom-header"),
                200,
                true
        );
        assertThat(options).isEqualTo(new CorsOptions(options));
        assertThat(options.toJson()).isEqualTo(otherOptions.toJson());
    }

    @Test
    @DisplayName("WebVerticle Options...")
    void webVerticleOptions() {
        WebVerticleOptions options = new WebVerticleOptions()
                .setUseOpenAPI3Router(true)
                .setSpecs("specs")
                .setEnableCors(true)
                .setCors(new CorsOptions())
                .setEnableMetrics(true)
                .setEnableHealthChecks(true)
                .setMonitoringPath("some path");

        WebVerticleOptions otherOptions = new WebVerticleOptions(
                true,
                "specs",
                true,
                new CorsOptions(),
                true,
                true,
                "some path"
        );
        assertThat(options).isEqualTo(new WebVerticleOptions(options));
        assertThat(options.toJson()).isEqualTo(otherOptions.toJson());
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "bad-type-cors.json",
            "bad-cors-origin.json",
            "bad-cors-method-string.json",
            "bad-cors-method-array.json",
            "bad-cors-max-age.json",
            "bad-cors-allow-credentials.json",
            "bad-type-hc.json",
            "bad-type-metrics.json",
            "bad-type-monitoring-path.json",
            "bad-type-open-api.json",
            "open-api-no-specs.json"
    })
    @DisplayName("WebVerticle should not start with invalid options")
    void itShouldNotStartWithInvalidOptions(String file, Vertx vertx, VertxTestContext ctx) {
        JsonObject config = objectFromFile("web/invalid/" + file);
        vertx.deployVerticle(VERTICLE, new DeploymentOptions().setConfig(config), ctx.failing(err -> ctx.completeNow()));
    }


    public static class OperationEndpoint extends AbstractOpenAPIEndpoint {

        public OperationEndpoint() {

        }

        @Override
        public String operationId() {
            return "operation";
        }

        @Override
        public void handle(RoutingContext event) {
            assertThat(configService()).isNotNull();
            assertThat(vertx()).isNotNull();
            setContentJson(event);
            event.response().end("{\"title\": \"This is my title!\"}");
        }
    }

    public static class UnmappedEndpoint extends AbstractOpenAPIEndpoint {

        public UnmappedEndpoint() {

        }

        @Override
        public String operationId() {
            return "unmapped";
        }

        @Override
        public void handle(RoutingContext event) {
            event.response().end("{\"title\": \"This is my title!\"}");
        }
    }

    public static class SimpleEndpoint extends AbstractEndpoint {

        public SimpleEndpoint() {

        }

        @Override
        public Completable register(Router router) {
            router.get("/simple/route").handler(event -> {
                setContentJson(event);
                event.response().end("{\"title\": \"This is my title!\"}");
            });
            return Completable.complete();
        }

    }

}

