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

import fr.myprysm.vertx.test.VertxTest;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Metrics measurement handler tests")
class MetricsMeasurementHandlerTest implements VertxTest {

    private static int port;

    @BeforeAll
    static void beforeAll() throws IOException {
        ServerSocket socket = new ServerSocket(0);
        port = socket.getLocalPort();
        socket.close();
    }

    @Test
    @DisplayName("It should store values")
    void itShouldStoreValues(Vertx vertx, VertxTestContext ctx) {
        Router router = Router.router(vertx);
        MetricsMeasurementHandler measurementHandler = new MetricsMeasurementHandler();
        router.route().handler(measurementHandler);
        Handler<RoutingContext> simpleHandler = event -> event.response().end("{\"foo\":\"bar\"}");
        router.get("/first/route").handler(simpleHandler);
        router.get("/second/route").handler(simpleHandler);

        vertx.createHttpServer().requestHandler(router::accept).listen(port, ctx.succeeding(server -> {
            HttpClient client = vertx.createHttpClient(new HttpClientOptions().setDefaultPort(port));

            client.getNow("/first/route", first -> first.handler(r1 -> {
                client.getNow("/second/route", second -> second.handler(r2 -> {
                    JsonObject snapshot = measurementHandler.getSnapshot();
                    ctx.verify(() -> {
                        assertThat(snapshot).isNotNull();
                        assertThat(snapshot.getValue("/first/route")).isNotNull();
                        assertThat(snapshot.getValue("/second/route")).isNotNull();
                        ctx.completeNow();
                    });
                }));
            }));
        }));
    }

}
