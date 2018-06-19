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

package fr.myprysm.vertx.retrofit;

import fr.myprysm.vertx.test.VertxTest;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.junit5.Checkpoint;
import io.vertx.junit5.VertxTestContext;
import okhttp3.HttpUrl;
import org.junit.jupiter.api.*;
import retrofit2.http.GET;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Service Factory and Builder tests")
class ServiceFactoryTest implements VertxTest {


    interface LocalService {
        @GET("/test")
        Single<JsonObject> test();
    }

    @Nested
    @DisplayName("Service Factory integration tests")
    @SuppressWarnings("ResultOfMethodCallIgnored")
    class ServiceFactoryIntegrationTest {
        private static final int PORT = 8765;
        private HttpServer server;
        private ServiceFactory.Builder builder;

        @Test
        @DisplayName("It should not add header when no access token is provided")
        void itShouldNotAddHeaderWhenNoAccessTokenIsProvided(VertxTestContext ctx) {
            LocalService service = builder.build().create(LocalService.class);

            service.test().subscribe(response -> ctx.verify(() -> {
                        assertThat(response.getJsonObject("headers").fieldNames()).doesNotContain("authorization");
                        ctx.completeNow();
                    }),
                    ctx::failNow
            );
        }

        @Test
        @DisplayName("It should add default header when access token is provided")
        void itShouldAddHeaderWhenAccessTokenIsProvided(VertxTestContext ctx) {
            LocalService service = builder
                    .accessToken("access_token")
                    .build().create(LocalService.class);

            service.test().subscribe(response -> ctx.verify(() -> {
                        JsonObject headers = response.getJsonObject("headers");
                        assertThat(headers.fieldNames()).contains("authorization");
                        assertThat(headers.getString("authorization")).isEqualTo("bearer: access_token");
                        ctx.completeNow();
                    }),
                    ctx::failNow
            );
        }

        @Test
        @DisplayName("It should add custom header when alternate name and access token are provided")
        void itShouldAddCustomHeaderWhenNameAndTokenAreProvided(VertxTestContext ctx) {
            LocalService service = builder
                    .accessToken("access_token")
                    .authorizationHeader("New-Header")
                    .build().create(LocalService.class);

            service.test().subscribe(response -> ctx.verify(() -> {
                        JsonObject headers = response.getJsonObject("headers");
                        assertThat(headers.fieldNames()).contains("new-header");
                        assertThat(headers.getString("new-header")).isEqualTo("bearer: access_token");
                        ctx.completeNow();
                    }),
                    ctx::failNow
            );
        }

        @Test
        @DisplayName("It should manage custom type when access token is provided")
        void itShouldManageCustomTypeWhenTokenIsProvided(VertxTestContext ctx) {
            LocalService service = builder
                    .accessToken("access_token")
                    .tokenType("my-type")
                    .build().create(LocalService.class);

            service.test().subscribe(response -> ctx.verify(() -> {
                        JsonObject headers = response.getJsonObject("headers");
                        assertThat(headers.fieldNames()).contains("authorization");
                        assertThat(headers.getString("authorization")).isEqualTo("my-type: access_token");
                        ctx.completeNow();
                    }),
                    ctx::failNow
            );
        }

        @Test
        @DisplayName("It should not add type when empty and access token is provided")
        void itShouldNotAddTypeWhenEmptyAndTokenIsProvided(VertxTestContext ctx) {
            Checkpoint cp = ctx.checkpoint(2);
            LocalService service = builder
                    .accessToken("access_token")
                    .tokenType(null)
                    .build().create(LocalService.class);

            Consumer<JsonObject> success = response -> ctx.verify(() -> {
                JsonObject headers = response.getJsonObject("headers");
                assertThat(headers.fieldNames()).contains("authorization");
                assertThat(headers.getString("authorization")).isEqualTo("access_token");
                cp.flag();
            });

            service.test().subscribe(success, ctx::failNow);

            service = builder
                    .tokenType("     ")
                    .build().create(LocalService.class);

            service.test().subscribe(success, ctx::failNow);
        }

        @Test
        @DisplayName("It should not add parameter when no key is provided")
        void itShouldNotAddHeaderWhenNoKeyIsProvided(VertxTestContext ctx) {
            LocalService service = builder.build().create(LocalService.class);
            service.test().subscribe(response -> {
                        assertThat(response.getJsonObject("params").fieldNames()).isEmpty();
                        ctx.completeNow();
                    },
                    ctx::failNow
            );
        }

        @Test
        @DisplayName("It should add key parameter when provided")
        void itShouldAddKeyWhenProvided(VertxTestContext ctx) {
            LocalService service = builder
                    .apiKey("some_key")
                    .build().create(LocalService.class);

            service.test().subscribe(response -> {
                        JsonObject params = response.getJsonObject("params");
                        assertThat(params.fieldNames()).contains("key");
                        assertThat(params.getString("key")).isEqualTo("some_key");
                        ctx.completeNow();
                    },
                    ctx::failNow
            );
        }

        @Test
        @DisplayName("It should add key and alternate name parameter when provided")
        void itShouldAddKeyWithParamNameWhenProvided(VertxTestContext ctx) {
            // Code Coverage...
            LocalService service = ServiceFactory.forBase("http://localhost:" + PORT + "/")
                    .apiKey("some_key")
                    .keyParam("my_param")
                    .build().create(LocalService.class);

            service.test().subscribe(response -> {
                        JsonObject params = response.getJsonObject("params");
                        assertThat(params.fieldNames()).contains("my_param");
                        assertThat(params.getString("my_param")).isEqualTo("some_key");
                        ctx.completeNow();
                    },
                    ctx::failNow
            );
        }

        @BeforeEach
        void setUp(Vertx vertx, VertxTestContext ctx) {
            Router router = Router.router(vertx);
            // Test coverage...
            builder = ServiceFactory.forBase(HttpUrl.parse("http://localhost:" + PORT + "/"));
            router.route().handler(event -> {
                JsonObject response = new JsonObject()
                        .put("params", new JsonObject())
                        .put("headers", new JsonObject());

                if (!event.queryParams().isEmpty()) {
                    JsonObject params = response.getJsonObject("params");
                    event.queryParams().forEach(param -> params.put(param.getKey().toLowerCase(), param.getValue().toLowerCase()));
                }

                if (!event.request().headers().isEmpty()) {
                    JsonObject headers = response.getJsonObject("headers");
                    event.request().headers().forEach(param -> headers.put(param.getKey().toLowerCase(), param.getValue().toLowerCase()));
                }

                event.response()
                        .putHeader("content-type", "application/json")
                        .end(response.encode());
            });
            server = vertx.createHttpServer().requestHandler(router::accept);

            server.listen(PORT, ctx.succeeding(s -> ctx.completeNow()));
        }

        @AfterEach
        void tearDown(VertxTestContext ctx) {
            server.close(ctx.succeeding(zoid -> ctx.completeNow()));
        }
    }

    @Nested
    @DisplayName("Builder tests")
    class BuilderTests {

        @Test
        @DisplayName("It should not build without base url")
        void itShouldNotBuildWithoutBaseUrl() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new ServiceFactory.Builder().build(),
                    "Base URL is required"
            );
        }

        @Test
        @DisplayName("It should not accept an empty access token, null is ok")
        void itShouldNotAcceptAnEmptyAccessToken() {
            new ServiceFactory.Builder().accessToken(null);
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new ServiceFactory.Builder().accessToken("    "),
                    "Access token is blank"
            );
        }

        @Test
        @DisplayName("It should not accept an empty API key, null is ok")
        void itShouldNotAcceptAnEmptyAPIKey() {
            new ServiceFactory.Builder().apiKey(null);
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new ServiceFactory.Builder().apiKey("    "),
                    "API Key is blank"
            );
        }

        @Test
        @DisplayName("It should not accept an invalid URL")
        void itShouldNotAcceptAnInvalidURL() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new ServiceFactory.Builder("bla bla"),
                    "The provided base URL is not valid"
            );
            assertThrows(
                    IllegalArgumentException.class,
                    () -> ServiceFactory.forBase("    "),
                    "The provided base URL is not valid"
            );
        }

        @Test
        @DisplayName("It should not accept an invalid key parameter name")
        void itShouldNotAcceptAnEmptyKeyParam() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new ServiceFactory.Builder().keyParam(null),
                    "API Key parameter is blank"
            );
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new ServiceFactory.Builder().keyParam("   "),
                    "API Key parameter is blank"
            );
        }

        @Test
        @DisplayName("It should not accept an empty authorization header")
        void itShouldNotAcceptAnEmptyAuthorizationHeader() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new ServiceFactory.Builder().authorizationHeader(null),
                    "Authorization header is blank"
            );
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new ServiceFactory.Builder().authorizationHeader("   "),
                    "Authorization header is blank"
            );
        }
    }
}