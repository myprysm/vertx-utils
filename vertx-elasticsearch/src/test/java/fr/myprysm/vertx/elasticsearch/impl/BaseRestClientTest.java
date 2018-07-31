package fr.myprysm.vertx.elasticsearch.impl;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import fr.myprysm.vertx.elasticsearch.converter.Converter;
import fr.myprysm.vertx.elasticsearch.converter.ConverterException;
import fr.myprysm.vertx.elasticsearch.utils.BiFunction;
import fr.myprysm.vertx.elasticsearch.utils.Function;
import fr.myprysm.vertx.elasticsearch.utils.TriConsumer;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.http.Header;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.ActionRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BaseRestClientTest extends VertxESUnitTestCase {

    private static final Converter<?, ?> NULL_RESP_CONVERTER = item -> null;
    private static final Converter<BaseRequest, ActionRequest> NULL_REQ_CONVERTER = item -> null;
    private static TestClient client;

    @BeforeAll
    static void initTestClient() {
        client = new TestClient(vertx, "test-client");
    }

    @Test
    @DisplayName("It should have a name")
    void itShouldHaveAName() {
        assertThat(client.name()).isEqualTo("test-client");
    }

    @Test
    @DisplayName("It should have the same vertx instance")
    void itShouldHaveSameVertxInstance() {
        assertThat(client.vertx()).isEqualTo(vertx);
    }


    @Test
    @DisplayName("It should have required parameters")
    void itShouldHaveRequiredParameters() {
        assertThatThrownBy(() -> client.executeRequestAsync(null, null, null, null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("The request cannot be null, provide at least an empty BaseRequest.");
        assertThatThrownBy(() -> client.executeRequestAsync(new BaseRequest(), null, null, null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("The request converter cannot be null.");
        assertThatThrownBy(() -> client.executeRequestAsync(new BaseRequest(), req -> null, null, null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("The response converter cannot be null.");
        assertThatThrownBy(() -> client.executeRequestAsync(new BaseRequest(), req -> null, resp -> null, null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("The response handler cannot be null.");
        assertThatThrownBy(() -> client.executeRequestAsync(new BaseRequest(), req -> null, resp -> null, ar -> {
        }, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("The client function cannot be null.");

        assertThatThrownBy(() -> client.executeRequestBlocking(null, null, null, null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("The request cannot be null, provide at least an empty BaseRequest.");
        assertThatThrownBy(() -> client.executeRequestBlocking(new BaseRequest(), null, null, null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("The request converter cannot be null.");
        assertThatThrownBy(() -> client.executeRequestBlocking(new BaseRequest(), req -> null, null, null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("The response converter cannot be null.");
        assertThatThrownBy(() -> client.executeRequestBlocking(new BaseRequest(), req -> null, resp -> null, null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("The response handler cannot be null.");
        assertThatThrownBy(() -> client.executeRequestBlocking(new BaseRequest(), req -> null, resp -> null, ar -> {
        }, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("The client function cannot be null.");

        assertThatThrownBy(() -> client.executeSimpleRequestBlocking(null, null, null, h -> null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("The request cannot be null, provide at least an empty BaseRequest.");
        assertThatThrownBy(() -> client.executeSimpleRequestBlocking(new BaseRequest(), null, null, h -> null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("The response converter cannot be null.");
        assertThatThrownBy(() -> client.executeSimpleRequestBlocking(new BaseRequest(), resp -> null, null, h -> null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("The response handler cannot be null.");
        assertThatThrownBy(() -> client.executeSimpleRequestBlocking(new BaseRequest(), resp -> null, ar -> {
        }, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("The client function cannot be null.");

    }

    @Test
    @DisplayName("It should execute requests successfully")
    void itShouldExecuteRequestsSuccessfully() {
        JsonObject truth = new JsonObject("{\"foo\":\"bar\",\"ping\":\"pong\"}");
        client.assertSuccessAsyncWithValue(
                new BaseRequest(),
                NULL_REQ_CONVERTER,
                json -> json.put("ping", "pong"),
                resp -> assertThat(resp).isEqualTo(truth),
                new JsonObject().put("foo", "bar")
        );

        client.assertSuccessBlockingWithValue(
                new BaseRequest(),
                NULL_REQ_CONVERTER,
                json -> json.put("ping", "pong"),
                resp -> assertThat(resp).isEqualTo(truth),
                new JsonObject().put("foo", "bar")
        );

        client.assertSuccessSimpleBlockingWithValue(
                new BaseRequest(),
                json -> json.put("ping", "pong"),
                resp -> assertThat(resp).isEqualTo(truth),
                new JsonObject().put("foo", "bar")
        );
    }


    @Test
    @DisplayName("It should fail with a bad request converter")
    void itShouldFailWithBadRequestConverter() {
        Converter<BaseRequest, ActionRequest> failingConverter = req -> {
            throw new ConverterException("test");
        };
        client.assertFailingBlocking(new BaseRequest(), failingConverter, NULL_RESP_CONVERTER, exc -> {
            assertThat(exc).isInstanceOf(ConverterException.class);
            assertThat(exc).hasMessage("test");
        });

        client.assertFailingAsync(new BaseRequest(), failingConverter, NULL_RESP_CONVERTER, exc -> {
            assertThat(exc).isInstanceOf(ConverterException.class);
            assertThat(exc).hasMessage("test");
        });
    }

    @Test
    @DisplayName("It should fail with a bad response converter")
    void itShouldFailWithBadResponseConverter() {
        Converter<Object, Object> failingConverter = req -> {
            throw new ConverterException("test");
        };
        client.assertFailingBlocking(new BaseRequest(), NULL_REQ_CONVERTER, failingConverter, exc -> {
            assertThat(exc).isInstanceOf(ConverterException.class);
            assertThat(exc).hasMessage("test");
        });

        client.assertFailingAsync(new BaseRequest(), NULL_REQ_CONVERTER, failingConverter, exc -> {
            assertThat(exc).isInstanceOf(ConverterException.class);
            assertThat(exc).hasMessage("test");
        });
    }

    @Test
    @DisplayName("It should fail with a bad client function")
    void itShouldFailWithBadClientFunction() {
        client.assertFailingBlocking(new BaseRequest(), NULL_REQ_CONVERTER, NULL_RESP_CONVERTER, exc -> {
            assertThat(exc).isInstanceOf(ConverterException.class);
            assertThat(exc).hasMessage("test");
        }, (r, h) -> {
            throw new ConverterException("test");
        });

        client.assertFailingAsync(new BaseRequest(), NULL_REQ_CONVERTER, NULL_RESP_CONVERTER, exc -> {
            assertThat(exc).isInstanceOf(ConverterException.class);
            assertThat(exc).hasMessage("test");
        }, (r, l, h) -> {
            throw new ConverterException("test");
        });
    }

    @Test
    @DisplayName("It should fail when async result is an error")
    void itShouldFailWithAsyncError() {
        client.assertFailingAsyncWithError(new BaseRequest(), NULL_REQ_CONVERTER, NULL_RESP_CONVERTER, exc -> {
            assertThat(exc).isInstanceOf(ConverterException.class);
            assertThat(exc).hasMessage("test");
        }, new ConverterException("test"));
    }

    @SuppressWarnings("Duplicates")
    static class TestClient extends BaseRestClient {

        /**
         * Build a new client with the vertx instance.
         *
         * @param vertx the current vertx instance
         * @param name  the name of the client
         */
        TestClient(Vertx vertx, String name) {
            super(vertx, name);
        }

        <ReqData extends BaseRequest, ReqES extends ActionRequest, RespES, RespData> void assertFailingBlockingWithValue(
                ReqData request,
                Converter<ReqData, ReqES> reqConverter,
                Converter<RespES, RespData> respConverter,
                Handler<Throwable> handler,
                RespES value
        ) {

            assertFailingBlocking(request, reqConverter, respConverter, handler, (req, head) -> value);
        }

        <ReqData extends BaseRequest, ReqES extends ActionRequest, RespES, RespData> void assertFailingBlocking(
                ReqData request,
                Converter<ReqData, ReqES> reqConverter,
                Converter<RespES, RespData> respConverter,
                Handler<Throwable> handler
        ) {

            assertFailingBlockingWithValue(request, reqConverter, respConverter, handler, null);
        }

        <ReqData extends BaseRequest, ReqES extends ActionRequest, RespES, RespData> void assertFailingBlocking(
                ReqData request,
                Converter<ReqData, ReqES> reqConverter,
                Converter<RespES, RespData> respConverter,
                Handler<Throwable> handler,
                BiFunction<ReqES, Header[], RespES> clientFunction
        ) {

            executeRequestBlocking(request, reqConverter, respConverter, ar -> {
                assertThat(ar.failed()).isTrue();
                assertThat(ar.succeeded()).isFalse();
                assertThat(ar.result()).isNull();
                assertThat(ar.cause()).isNotNull();
                handler.handle(ar.cause());
            }, clientFunction);
        }

        <ReqData extends BaseRequest, ReqES extends ActionRequest, RespES, RespData> void assertFailingAsync(
                ReqData request,
                Converter<ReqData, ReqES> reqConverter,
                Converter<RespES, RespData> respConverter,
                Handler<Throwable> handler
        ) {
            assertFailingAsyncWithValue(request, reqConverter, respConverter, handler, null);
        }

        <ReqData extends BaseRequest, ReqES extends ActionRequest, RespES, RespData> void assertFailingAsyncWithValue(
                ReqData request,
                Converter<ReqData, ReqES> reqConverter,
                Converter<RespES, RespData> respConverter,
                Handler<Throwable> handler,
                RespES value
        ) {
            assertFailingAsync(request, reqConverter, respConverter, handler, (r, l, h) -> l.onResponse(value));
        }

        <ReqData extends BaseRequest, ReqES extends ActionRequest, RespES, RespData> void assertFailingAsync(
                ReqData request,
                Converter<ReqData, ReqES> reqConverter,
                Converter<RespES, RespData> respConverter,
                Handler<Throwable> handler,
                TriConsumer<ReqES, ActionListener<RespES>, Header[]> clientFunction
        ) {
            executeRequestAsync(request, reqConverter, respConverter, ar -> {
                assertThat(ar.failed()).isTrue();
                assertThat(ar.succeeded()).isFalse();
                assertThat(ar.result()).isNull();
                assertThat(ar.cause()).isNotNull();
                handler.handle(ar.cause());
            }, clientFunction);
        }

        <ReqData extends BaseRequest, ReqES extends ActionRequest, RespES, RespData> void assertFailingAsyncWithError(
                ReqData request,
                Converter<ReqData, ReqES> reqConverter,
                Converter<RespES, RespData> respConverter,
                Handler<Throwable> handler,
                Exception value
        ) {
            assertFailingAsync(request, reqConverter, respConverter, handler, (r, l, h) -> l.onFailure(value));
        }


        <ReqData extends BaseRequest, ReqES extends ActionRequest, RespES, RespData> void assertSuccessBlockingWithValue(
                ReqData request,
                Converter<ReqData, ReqES> reqConverter,
                Converter<RespES, RespData> respConverter,
                Handler<RespData> handler,
                RespES value) {

            assertSuccessBlocking(request, reqConverter, respConverter, handler, (r, h) -> value);
        }

        <ReqData extends BaseRequest, ReqES extends ActionRequest, RespES, RespData> void assertSuccessBlocking(
                ReqData request,
                Converter<ReqData, ReqES> reqConverter,
                Converter<RespES, RespData> respConverter,
                Handler<RespData> handler,
                BiFunction<ReqES, Header[], RespES> clientFunction) {

            executeRequestBlocking(request, reqConverter, respConverter, ar -> {
                assertThat(ar.failed()).isFalse();
                assertThat(ar.succeeded()).isTrue();
                assertThat(ar.result()).isNotNull();
                assertThat(ar.cause()).isNull();
                handler.handle(ar.result());
            }, clientFunction);
        }

        <ReqData extends BaseRequest, RespES, RespData> void assertSuccessSimpleBlockingWithValue(
                ReqData request,
                Converter<RespES, RespData> respConverter,
                Handler<RespData> handler,
                RespES value) {
            assertSuccessSimpleBlocking(request, respConverter, handler, h -> value);
        }

        <ReqData extends BaseRequest, RespES, RespData> void assertSuccessSimpleBlocking(
                ReqData request,
                Converter<RespES, RespData> respConverter,
                Handler<RespData> handler,
                Function<Header[], RespES> clientFunction) {

            executeSimpleRequestBlocking(request, respConverter, ar -> {
                assertThat(ar.failed()).isFalse();
                assertThat(ar.succeeded()).isTrue();
                assertThat(ar.result()).isNotNull();
                assertThat(ar.cause()).isNull();
                handler.handle(ar.result());
            }, clientFunction);
        }

        <ReqData extends BaseRequest, ReqES extends ActionRequest, RespES, RespData> void assertSuccessAsync(
                ReqData request,
                Converter<ReqData, ReqES> reqConverter,
                Converter<RespES, RespData> respConverter,
                Handler<RespData> handler,
                TriConsumer<ReqES, ActionListener<RespES>, Header[]> clientFunction) {
            executeRequestAsync(request, reqConverter, respConverter, ar -> {
                assertThat(ar.failed()).isFalse();
                assertThat(ar.succeeded()).isTrue();
                assertThat(ar.result()).isNotNull();
                assertThat(ar.cause()).isNull();
                handler.handle(ar.result());
            }, clientFunction);
        }

        <ReqData extends BaseRequest, ReqES extends ActionRequest, RespES, RespData> void assertSuccessAsyncWithValue(
                ReqData request,
                Converter<ReqData, ReqES> reqConverter,
                Converter<RespES, RespData> respConverter,
                Handler<RespData> handler,
                RespES value) {
            assertSuccessAsync(request, reqConverter, respConverter, handler, (r, l, h) -> l.onResponse(value));
        }
    }

}