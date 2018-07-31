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

package fr.myprysm.vertx.elasticsearch.impl;

import fr.myprysm.vertx.elasticsearch.VertxESTestCase;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.junit5.VertxTestContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.elasticsearch.rest.RestStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * Simple base unit test case class providing simple mocking http server.
 * <p>
 * The port is randomly generated at the beginning of the test.
 * A router is bound to the http server, requests can be mapped with {@link #registerResourceHandler(HttpMethod, String, ContentType, RestStatus, String)}
 * and {@link #json(HttpMethod, String, RestStatus, String)}.
 * <p>
 * The router is cleared before each test, don't forget to add your handlers.
 */
public class VertxESUnitTestCase extends VertxESTestCase {


    private static int port;
    private static Router router;
    private static HttpServer server;

    @BeforeAll
    static void setUpMockHttpServer(VertxTestContext ctx) throws IOException {
        ServerSocket socket = new ServerSocket(0);
        port = socket.getLocalPort();
        socket.close();

        router = Router.router(vertx);
        server = vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(port, ctx.succeeding(s -> ctx.completeNow()));
    }

    @BeforeEach
    void resetRoutes() {
        router.clear();
    }

    /**
     * Returns the port the mock http server listens to.
     *
     * @return the port.
     */
    final int port() {
        return port;
    }

    /**
     * Join the parts with the file separator character.
     *
     * @param parts the parts
     * @return the path
     */
    static String path(String... parts) {
        return StringUtils.join(parts, File.separatorChar);
    }

    /**
     * Returns a jsonResource resource path from the path and the resource name.
     * <p>
     * Append automatically the extension.
     *
     * @param path     the path
     * @param resource the resource name
     * @return the path to the resource.
     */
    final String jsonResource(String path, String resource) {
        return path(path, resource) + ".json";
    }

    /**
     * Registers a handler that will respond the resource as JSON with provided method and status at path.
     *
     * @param method       the method
     * @param path         the path
     * @param status       the response status
     * @param resourcePath the path to the resource file
     * @return fluent
     */
    final VertxESTestCase json(HttpMethod method, String path, RestStatus status, String resourcePath) {
        return registerResourceHandler(method, path, ContentType.APPLICATION_JSON, status, resourcePath);
    }

    /**
     * Handler that responds with the status at path and serve no content.
     *
     * @param method the method
     * @param path   the path
     * @param status the status
     * @return fluent
     */
    final VertxESTestCase noContent(HttpMethod method, String path, RestStatus status) {
        return registerResourceHandler(method, path, null, status, null);
    }

    final VertxESTestCase registerResourceHandler(HttpMethod method, String path, ContentType contentType, RestStatus status, String resourcePath) {
        return registerHandler(method, path, event -> {
            HttpServerResponse response = setStatus(event, status);
            writeContent(response, contentType, resourcePath).end();
        });
    }

    final HttpServerResponse setStatus(RoutingContext event, RestStatus status) {
        return event.response().setStatusCode(status.getStatus());
    }

    final HttpServerResponse writeContent(HttpServerResponse response, ContentType contentType, String resourcePath) {
        if (StringUtils.isNotBlank(resourcePath)) {
            response.setChunked(true)
                    .putHeader("Content-Type", contentType.getMimeType())
                    .write(stringFromFile(resourcePath));
        }
        return response;
    }

    final VertxESTestCase registerHandler(HttpMethod method, String path, Handler<RoutingContext> handler) {
        router.route(method, path).handler(handler);
        return this;
    }

    @AfterAll
    static void tearDownHttpServer(VertxTestContext ctx) {
        server.close(ctx.succeeding(s -> ctx.completeNow()));
    }

}
