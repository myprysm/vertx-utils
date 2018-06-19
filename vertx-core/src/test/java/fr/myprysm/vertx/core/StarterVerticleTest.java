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

import fr.myprysm.vertx.test.VertxTest;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.VertxTestContext;
import io.vertx.reactivex.ext.healthchecks.HealthChecks;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("StarterVerticle tests")
public class StarterVerticleTest implements VertxTest {
    public static final String VERTICLE = "fr.myprysm.vertx.core.StarterVerticle";


    @Test
    @DisplayName("StarterVerticle should not start without config file")
    void itShouldNotStartWithoutValidConfigFile(Vertx vertx, VertxTestContext ctx) {
        DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("path", "some/path"));
        vertx.deployVerticle(VERTICLE, options, ctx.failing(err -> ctx.completeNow()));
    }

    @Test
    @DisplayName("StarterVerticle should not start without verticles")
    void itShouldNotStartWithoutVerticles(Vertx vertx, VertxTestContext ctx) {
        DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("path", "start/no-verticles.yml"));
        vertx.deployVerticle(VERTICLE, options, ctx.failing(err -> ctx.completeNow()));
    }

    @Test
    @DisplayName("StarterVerticle should not start when a verticle fails to start")
    void itShouldNotStartWhenAVerticleFailsToStart(Vertx vertx, VertxTestContext ctx) {
        DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("path", "start/verticle-fails-to-start.yml"));
        vertx.deployVerticle(VERTICLE, options, ctx.failing(err -> ctx.completeNow()));
    }

    @Test
    @DisplayName("StarterVerticle should start verticles")
    void itShouldStartVerticles(Vertx vertx, VertxTestContext ctx) {
        DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("path", "start/valid-config.yml"));
        vertx.deployVerticle(VERTICLE, options, ctx.succeeding(zoid -> ctx.completeNow()));
    }

    @Test
    @DisplayName("Verticle Options...")
    void verticleOptions() {
        VerticleOptions options = new VerticleOptions()
                .setOptions(new DeploymentOptions())
                .setVerticle("some verticle");

        assertThat(options).isEqualTo(new VerticleOptions(options));
    }


    @Test
    @DisplayName("StarterVerticle should create the shared health check instance")
    void itShouldCreateHealthCheckInstance(Vertx vertx, VertxTestContext ctx) {
        HealthChecks hc1 = StarterVerticle.getHealthChecks(new io.vertx.reactivex.core.Vertx(vertx));
        HealthChecks hc2 = StarterVerticle.getHealthChecks(new io.vertx.reactivex.core.Vertx(vertx));

        ctx.verify(() -> {
            assertThat(hc1).isEqualTo(hc2);
            ctx.completeNow();
        });
    }

    @Slf4j
    public static class SimpleVerticle extends AbstractVerticle {

        @Override
        public void start(Future<Void> startFuture) {
            log.info("SimpleVerticle started");
            startFuture.complete();
        }

        @Override
        public void stop(Future<Void> stopFuture) {
            log.info("SimpleVerticle stopped");
            stopFuture.complete();
        }
    }

    public static class FailVerticle extends AbstractVerticle {

        @Override
        public void start(Future<Void> startFuture) {
            startFuture.fail("What did you expect?");
        }

    }

}