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

package fr.myprysm.vertx.core.config;

import fr.myprysm.vertx.core.VerticleOptions;
import fr.myprysm.vertx.core.reactivex.config.ConfigService;
import fr.myprysm.vertx.core.utils.Utils;
import fr.myprysm.vertx.test.VertxTest;
import fr.myprysm.vertx.validation.ValidationException;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.VertxTestContext;
import io.vertx.serviceproxy.ServiceBinder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static fr.myprysm.vertx.core.config.ConfigService.ADDRESS;
import static fr.myprysm.vertx.validation.JsonValidation.message;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("Duplicates")
@DisplayName("(Rx)ConfigService implementation tests")
class ConfigServiceTest implements VertxTest {
    private static final String TEST_CONFIG = "config/test-config-service.yml";
    private static final String INVALID_VERTICLES_CONFIG = "config/invalid-verticles-config.yml";
    private static final JsonObject NUMBERS = new JsonObject()
            .put("integers", new JsonObject()
                    .put("int", 123)
                    .put("long", 123456789123L))
            .put("decimals", new JsonObject()
                    .put("float", 123.123)
                    .put("double", 123456789123.123456D));

    private static final JsonArray LIST = new JsonArray().add("one").add("two").add("three");

    private ConfigService service;
    private ConfigService proxy;
    private MessageConsumer<JsonObject> consumer;

    @BeforeAll
    static void setupSystemProperties() {
        System.setProperty("configuration.env.types.int", "123");
        System.setProperty("configuration.env.types.long", "123456789123");
        System.setProperty("configuration.env.types.float", "123.123");
        System.setProperty("configuration.env.types.double", "123456789123.123456");
        System.setProperty("configuration.env.types.bool.y", "y");
        System.setProperty("configuration.env.types.bool.n", "n");
        System.setProperty("configuration_env_types_string", "foo bar");
    }

    @BeforeEach
    void setupConfigService(Vertx vertx, VertxTestContext ctx) {
        service = fr.myprysm.vertx.core.config.ConfigService.createRx(vertx, TEST_CONFIG, ctx.succeeding(configService -> {
            consumer = new ServiceBinder(vertx)
                    .setAddress(ADDRESS)
                    .register(fr.myprysm.vertx.core.config.ConfigService.class, configService);
            proxy = fr.myprysm.vertx.core.config.ConfigService.createRxProxy(vertx);
            Utils.configServiceProxy(new io.vertx.reactivex.core.Vertx(vertx));
            ctx.completeNow();
        }));

    }

    @AfterEach
    void teardownConfigService(VertxTestContext ctx) {
        consumer.unregister(ctx.succeeding(event -> ctx.completeNow()));
    }

    @Test
    @DisplayName("ConfigService coverage")
    void registryCoverage(VertxTestContext ctx) {
        assertThat(service).isNotEqualTo(proxy);
        assertThat(service).isNotEqualTo(null);
        assertThat(service).isNotEqualTo(service.getDelegate());
        assertThat(service.hashCode()).isNotEqualTo(proxy.hashCode());
        assertThat(service.toString()).isNotEqualTo(proxy.toString());

        fr.myprysm.vertx.core.config.ConfigService unwrap = ConfigService.__TYPE_ARG.unwrap(service);
        ConfigService wrap = ConfigService.__TYPE_ARG.wrap(service.getDelegate());
        assertThat(unwrap).isEqualTo(service.getDelegate());
        assertThat(service).isEqualTo(wrap);
        assertThat(service).isEqualTo(ConfigService.newInstance(service.getDelegate()));

        ctx.completeNow();
    }


    @Nested
    @DisplayName("ConfigServiceLocal Tests")
    class ConfigServiceLocalTest {
        @Test
        @DisplayName("ConfigService should not load invalid verticles")
        void serviceShouldNotLoadInvalidVerticles(Vertx vertx, VertxTestContext ctx) {
            fr.myprysm.vertx.core.config.ConfigService.create(vertx, INVALID_VERTICLES_CONFIG, ctx.failing(throwable -> {
                assertThat(throwable).isInstanceOf(ValidationException.class);
                assertThat(throwable).hasMessage(message("verticle", "is not a valid java class"));
                ctx.completeNow();
            }));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "config/invalid/bad-verticle-config.yml",
                "config/invalid/bad-worker.yml",
                "config/invalid/bad-multithreaded.yml",
                "config/invalid/bad-isolation-group.yml",
                "config/invalid/bad-worker-pool-name.yml",
                "config/invalid/bad-worker-pool-size-value.yml",
                "config/invalid/bad-worker-pool-size-type.yml",
                "config/invalid/bad-worker-exec-time-value.yml",
                "config/invalid/bad-worker-exec-time-type.yml",
                "config/invalid/bad-ha.yml",
                "config/invalid/bad-extra-cp.yml",
                "config/invalid/bad-instances.yml",
                "config/invalid/bad-isolated-classes.yml",
        })
        @DisplayName("ConfigService should not load invalid verticle configuration")
        void serviceShouldNotLoadInvalidVerticleConfiguration(String config, Vertx vertx, VertxTestContext ctx) {
            fr.myprysm.vertx.core.config.ConfigService.create(vertx, config, ctx.failing(throwable -> ctx.verify(() -> {
                assertThat(throwable).isInstanceOf(ValidationException.class);
                ctx.completeNow();
            })));
        }

    }


    @Nested
    @DisplayName("ConfigServiceProxy Tests")
    class ConfigServiceProxyTest {
        @BeforeEach
        void setupConfigService(Vertx vertx, VertxTestContext ctx) {
            service = fr.myprysm.vertx.core.config.ConfigService.createRx(vertx, TEST_CONFIG, ctx.succeeding(configService -> {
                consumer = new ServiceBinder(vertx)
                        .setAddress(ADDRESS)
                        .register(fr.myprysm.vertx.core.config.ConfigService.class, configService);
                proxy = fr.myprysm.vertx.core.config.ConfigService.createRxProxy(vertx);
                Utils.configServiceProxy(new io.vertx.reactivex.core.Vertx(vertx));
                ctx.completeNow();
            }));

        }

        @AfterEach
        void teardownConfigService(VertxTestContext ctx) {
            consumer.unregister(ctx.succeeding(event -> ctx.completeNow()));
        }

        @Test
        @DisplayName("ConfigService proxy should get same values")
        void proxyShouldGetSameValues() throws InterruptedException {
            proxy.rxGetString("types.string").test().await().assertValue("foo bar");
            proxy.rxGetBoolean("types.booleans.y").test().await().assertValue(true);
            proxy.rxGetBoolean("types.booleans.n").test().await().assertValue(false);
            proxy.rxGetFloat("types.numbers.decimals.float").test().await().assertValue(123.123F);
            proxy.rxGetDouble("types.numbers.decimals.double").test().await().assertValue(123456789123.123456D);
            proxy.rxGetInteger("types.numbers.integers.int").test().await().assertValue(123);
            proxy.rxGetLong("types.numbers.integers.long").test().await().assertValue(123456789123L);
            proxy.rxGetObject("types.numbers").test().await().assertValue(NUMBERS);
            proxy.rxGetArray("types.arrays.list").test().await().assertValue(LIST);
            proxy.rxGetString("blah blah").test().await().assertError(ConfigServiceException.class);
        }

    }

    @DisplayName("Rx ConfigService Tests")
    @Nested
    class RxConfigServiceTest {

        @BeforeEach
        void setupConfigService(Vertx vertx, VertxTestContext ctx) {
            service = fr.myprysm.vertx.core.config.ConfigService.createRx(vertx, TEST_CONFIG, ctx.succeeding(configService -> {
                consumer = new ServiceBinder(vertx)
                        .setAddress(ADDRESS)
                        .register(fr.myprysm.vertx.core.config.ConfigService.class, configService);
                proxy = fr.myprysm.vertx.core.config.ConfigService.createRxProxy(vertx);
                Utils.configServiceProxy(new io.vertx.reactivex.core.Vertx(vertx));
                ctx.completeNow();
            }));

        }

        @AfterEach
        void teardownConfigService(VertxTestContext ctx) {
            consumer.unregister(ctx.succeeding(event -> ctx.completeNow()));
        }

        @Test
        @DisplayName("ConfigService should load verticles")
        void serviceShouldLoadVerticlesAndTheirOptions() {
            service.rxGetVerticles().test().assertOf(check -> {
                assertThat(check.valueCount()).isEqualTo(1);

                List<VerticleOptions> values = check.values().get(0);
                assertThat(values.size()).isEqualTo(2);
                assertThat(values.get(0).getOptions()).isEqualTo(new DeploymentOptions());
                assertThat(values.get(1).getOptions()).isEqualTo(new DeploymentOptions().setConfig(new JsonObject().put("some", "param")));
            });
        }

        @Test
        @DisplayName("ConfigService should return config")
        void serviceShouldReturnConfig() {
            service.rxGetConfig()
                    .test()
                    .assertOf(check -> {
                        JsonObject config = check.values().get(0);
                        assertThat(config.fieldNames()).containsExactly("env", "types");
                    });
        }


        @Test
        @DisplayName("ConfigService should merge config from env and sys")
        void serviceShouldMergeConfigFromEnv() {
            service.rxGetObject("env.types").test().assertValue(new JsonObject()
                    // Vertx parses them automatically as respective float and double
                    .put("int", 123.0)
                    .put("long", 123456789123D)
                    // Rest is okay...
                    .put("float", 123.123)
                    .put("double", 123456789123.123456D)
                    .put("bool", new JsonObject().put("y", true).put("n", false))
                    .put("string", "foo bar"));
        }


        @Test
        @DisplayName("ConfigService should return values as Strings")
        void serviceShouldReturnValuesAsStrings() {
            service.rxGetString("env.types.string").test().assertValue("foo bar");
            service.rxGetString("types.string").test().assertValue("foo bar");
            service.rxGetString("types.numbers.integers.int").test().assertValue("123");
            service.rxGetString("types.numbers.decimals.double").test().assertValue("1.2345678912312346E11");
            service.rxGetString("types.numbers").test().assertValue(NUMBERS.toString());
            service.rxGetString("types.arrays.list").test().assertValue(LIST.toString());
            service.rxGetString("types.booleans.Yes").test().assertValue("true");
            service.rxGetString("types.booleans.off").test().assertValue("false");
            service.rxGetString("blah blah").test().assertError(ConfigServiceException.class);
        }

        @Test
        @DisplayName("ConfigService should return values as Booleans when possible")
        void serviceShouldReturnValuesAsBooleans() {
            service.rxGetBoolean("env.types.bool.y").test().assertValue(true);
            service.rxGetBoolean("env.types.bool.n").test().assertValue(false);

            service.rxGetBoolean("types.numbers.integers.int").test().assertValue(true);
            service.rxGetBoolean("types.numbers.integers.long").test().assertValue(true);
            service.rxGetBoolean("types.numbers.decimals.float").test().assertValue(true);
            service.rxGetBoolean("types.numbers.decimals.double").test().assertValue(true);
            service.rxGetBoolean("types.numbers.decimals.double").test().assertValue(true);

            service.rxGetBoolean("types.booleans.y").test().assertValue(true);
            service.rxGetBoolean("types.booleans.Y").test().assertValue(true);
            service.rxGetBoolean("types.booleans.on").test().assertValue(true);
            service.rxGetBoolean("types.booleans.yes").test().assertValue(true);
            service.rxGetBoolean("types.booleans.Yes").test().assertValue(true);
            service.rxGetBoolean("types.booleans.true").test().assertValue(true);
            service.rxGetBoolean("types.booleans.TRUE").test().assertValue(true);

            service.rxGetBoolean("types.booleans.zero").test().assertValue(false);
            service.rxGetBoolean("types.booleans.n").test().assertValue(false);
            service.rxGetBoolean("types.booleans.N").test().assertValue(false);
            service.rxGetBoolean("types.booleans.off").test().assertValue(false);
            service.rxGetBoolean("types.booleans.no").test().assertValue(false);
            service.rxGetBoolean("types.booleans.No").test().assertValue(false);
            service.rxGetBoolean("types.booleans.false").test().assertValue(false);
            service.rxGetBoolean("types.booleans.FALSE").test().assertValue(false);

            service.rxGetBoolean("types.numbers").test().assertError(ConfigServiceException.class);
            service.rxGetBoolean("types.arrays.inline").test().assertError(ConfigServiceException.class);
            service.rxGetBoolean("types.arrays.list").test().assertError(ConfigServiceException.class);
            service.rxGetBoolean("types.string").test().assertError(ConfigServiceException.class);
            service.rxGetBoolean("blah blah").test().assertError(ConfigServiceException.class);
        }

        @Test
        @DisplayName("ConfigService should return values as Doubles when possible")
        void serviceShouldReturnValuesAsDoubles() {
            service.rxGetDouble("env.types.double").test().assertValue(123456789123.123456D);
            service.rxGetDouble("types.numbers.integers.long").test().assertValue(123456789123D);
            service.rxGetDouble("types.numbers.decimals.double").test().assertValue(123456789123.123456D);
            service.rxGetDouble("types.string").test().assertError(ConfigServiceException.class);
            service.rxGetDouble("types.boolean.on").test().assertError(ConfigServiceException.class);
            service.rxGetDouble("blah blah").test().assertError(ConfigServiceException.class);
        }

        @Test
        @DisplayName("ConfigService should return values as Floats when possible")
        void serviceShouldReturnValuesAsFloats() {
            service.rxGetFloat("env.types.float").test().assertValue(123.123F);
            service.rxGetFloat("types.numbers.integers.int").test().assertValue(123F);
            service.rxGetFloat("types.numbers.integers.long").test().assertValue(123456789123F);
            service.rxGetFloat("types.numbers.decimals.float").test().assertValue(123.123F);
            service.rxGetFloat("types.numbers.decimals.double").test().assertValue(123456789123.123456F);
            service.rxGetFloat("types.string").test().assertError(ConfigServiceException.class);
            service.rxGetFloat("types.boolean.on").test().assertError(ConfigServiceException.class);
            service.rxGetFloat("blah blah").test().assertError(ConfigServiceException.class);
        }

        @Test
        @DisplayName("ConfigService should return values as Longs when possible")
        void serviceShouldReturnValuesAsLongs() {
            service.rxGetLong("env.types.long").test().assertValue(123456789123L);
            service.rxGetLong("types.numbers.integers.long").test().assertValue(123456789123L);
            service.rxGetLong("types.numbers.decimals.double").test().assertValue(123456789123L);
            service.rxGetLong("types.string").test().assertError(ConfigServiceException.class);
            service.rxGetLong("types.boolean.on").test().assertError(ConfigServiceException.class);
            service.rxGetLong("blah blah").test().assertError(ConfigServiceException.class);
        }

        @Test
        @DisplayName("ConfigService should return values as Integers when possible")
        void serviceShouldReturnValuesAsIntegers() {
            service.rxGetInteger("env.types.int").test().assertValue(123);
            service.rxGetInteger("types.numbers.integers.int").test().assertValue(123);
            service.rxGetInteger("types.numbers.integers.long").test().assertValue(-1097262461); // Overflow
            service.rxGetInteger("types.numbers.decimals.double").test().assertValue(2147483647); // Overflow
            service.rxGetInteger("types.string").test().assertError(ConfigServiceException.class);
            service.rxGetInteger("types.boolean.on").test().assertError(ConfigServiceException.class);
            service.rxGetInteger("blah blah").test().assertError(ConfigServiceException.class);
        }

        @Test
        @DisplayName("ConfigService should return values as JsonObjects when possible")
        void serviceShouldReturnValuesAsJsonObjects() {
            service.rxGetObject("types.numbers").test().assertValue(NUMBERS);
            service.rxGetObject("blah blah").test().assertError(ConfigServiceException.class);
        }


        @Test
        @DisplayName("ConfigService should return values as JsonArrays when possible")
        void serviceShouldReturnValuesAsJsonArrays() {
            service.rxGetArray("types.arrays.inline").test().assertValue(new JsonArray().add(1).add(2).add(3));
            service.rxGetArray("types.arrays.list").test().assertValue(LIST);
            service.rxGetArray("types.numbers").test().assertError(ConfigServiceException.class);
            service.rxGetArray("blah blah").test().assertError(ConfigServiceException.class);
        }
    }

    @Nested
    class ConfigServiceExceptionMessageCodecTest {
        @Test
        @DisplayName("Message codec should encode & decode properly")
        @SuppressWarnings("ThrowableNotThrown")
        void messageCodecShouldEncodeAndDecode() {
            ConfigServiceExceptionMessageCodec codec = new ConfigServiceExceptionMessageCodec();

            ConfigServiceException exc = new ConfigServiceException(ConfigServiceException.UNKNOWN_ERROR, "test");
            Buffer buffer = Buffer.buffer();
            codec.encodeToWire(buffer, exc);
            ConfigServiceException decoded = codec.decodeFromWire(0, buffer);
            assertThat(exc.failureCode()).isEqualTo(decoded.failureCode());
        }
    }
}
