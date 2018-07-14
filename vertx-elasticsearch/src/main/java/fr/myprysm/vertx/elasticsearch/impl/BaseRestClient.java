package fr.myprysm.vertx.elasticsearch.impl;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import fr.myprysm.vertx.elasticsearch.action.FutureActionListener;
import fr.myprysm.vertx.elasticsearch.converter.CommonConverters;
import fr.myprysm.vertx.elasticsearch.converter.Converter;
import fr.myprysm.vertx.elasticsearch.utils.BiConsumer;
import fr.myprysm.vertx.elasticsearch.utils.BiFunction;
import fr.myprysm.vertx.elasticsearch.utils.Function;
import fr.myprysm.vertx.elasticsearch.utils.TriConsumer;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import org.apache.http.Header;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.ActionRequest;

import static java.util.Objects.requireNonNull;

/**
 * Base rest client for subclassing.
 * <p>
 * Provides the helpers for Async and Blocking execution models with the Elasticsearch client interfaces.
 */
class BaseRestClient {

    /**
     * The vertx instance.
     */
    private final Vertx vertx;

    /**
     * Build a new client with the vertx instance.
     *
     * @param vertx the current vertx instance
     */
    BaseRestClient(Vertx vertx) {
        this.vertx = requireNonNull(vertx);
    }

    /**
     * Get the vertx instance.
     *
     * @return the vertx instance
     */
    public Vertx vertx() {
        return vertx;
    }

    //region Client Request Utils

    /**
     * Prepares a request and apply the provided {@link BiConsumer}.
     * <p>
     * The consumer accepts the elasticsearch request body and the headers.
     * It executes on the event loop.
     *
     * @param request    the request data object
     * @param converter  the request converter
     * @param handler    the result  handler
     * @param consumer   the consumer
     * @param <ReqData>  the request DataObject type
     * @param <ReqES>    the request ElasticSearch type
     * @param <RespData> the response DataObject type
     */
    <ReqData extends BaseRequest, ReqES extends ActionRequest, RespData> void prepareRequest(ReqData request,
                                                                                             Converter<ReqData, ReqES> converter,
                                                                                             Handler<AsyncResult<RespData>> handler,
                                                                                             BiConsumer<ReqES, Header[]> consumer) {
        ReqES esRequest = null;
        Header[] headers = new Header[]{};
        if (request != null) {
            try {
                esRequest = converter.convert(request);
            } catch (Exception exc) {
                handler.handle(Future.failedFuture(exc));
                return;
            }

            headers = CommonConverters.headersFromRequest(request);
        }

        try {
            consumer.consume(esRequest, headers);
        } catch (Exception exc) {
            handler.handle(Future.failedFuture(exc));
        }
    }

    /**
     * Executes an asynchronous elasticsearch request using rest high level client async mechanisms.
     *
     * @param request        the request
     * @param reqConverter   the request converter
     * @param respConverter  the response converter
     * @param handler        the result handler
     * @param clientFunction the client function that executes the simple blocking request
     * @param <ReqData>      the request DataObject type
     * @param <ReqES>        the request ElasticSearch type
     * @param <RespES>       the response ElasticSearch Type
     * @param <RespData>     the response DataObject type
     */
    <ReqData extends BaseRequest, ReqES extends ActionRequest, RespES, RespData> void executeRequestAsync(
            ReqData request,
            Converter<ReqData, ReqES> reqConverter,
            Converter<RespES, RespData> respConverter,
            Handler<AsyncResult<RespData>> handler,
            TriConsumer<ReqES, ActionListener<RespES>, Header[]> clientFunction) {

        prepareRequest(
                request,
                reqConverter,
                handler,
                (esRequest, headers) -> clientFunction.consume(esRequest, new FutureActionListener<>(respConverter, handler), headers)
        );
    }


    /**
     * Prepares a request and executes the provided {@link TriConsumer} on a worker thread.
     * <p>
     * The consumer accepts the ElasticSearch Request, the headers and a future that expects the DataObject Response.
     *
     * @param request    the request data object
     * @param converter  the request converter
     * @param handler    the result  handler
     * @param consumer   the consumer
     * @param <ReqData>  the request DataObject type
     * @param <ReqES>    the request ElasticSearch type
     * @param <RespData> the response DataObject type
     */
    <ReqData extends BaseRequest, ReqES extends ActionRequest, RespData> void prepareRequestBlocking(
            ReqData request,
            Converter<ReqData, ReqES> converter,
            Handler<AsyncResult<RespData>> handler,
            TriConsumer<ReqES, Header[], Future<RespData>> consumer) {

        prepareRequest(request, converter, handler, (esRequest, headers) ->
                vertx.executeBlocking(future -> {
                    try {
                        consumer.consume(esRequest, headers, future);
                    } catch (Exception exc) {
                        future.fail(exc);
                    }
                }, handler));
    }

    /**
     * Executes a simple blocking request with only potential headers and no request data associated on a worker thread.
     * <p>
     * Applies request and response conversions.
     * <p>
     * Any error fails the result.
     * <p>
     * Request may be <code>null</code>.
     *
     * @param request        the request
     * @param reqConverter   the request converter
     * @param respConverter  the response converter
     * @param handler        the result handler
     * @param clientFunction the client function that executes the simple blocking request
     * @param <ReqData>      the request DataObject type
     * @param <ReqES>        the request ElasticSearch type
     * @param <RespES>       the response ElasticSearch Type
     * @param <RespData>     the response DataObject type
     */
    <ReqData extends BaseRequest, ReqES extends ActionRequest, RespES, RespData> void executeSimpleRequestBlocking(
            ReqData request,
            Converter<ReqData, ReqES> reqConverter,
            Converter<RespES, RespData> respConverter,
            Handler<AsyncResult<RespData>> handler,
            Function<Header[], RespES> clientFunction) {

        executeRequestBlocking(
                request,
                reqConverter,
                respConverter,
                handler,
                (req, headers) -> clientFunction.apply(headers)
        );
    }

    /**
     * Executes a blocking request with potential headers and request data associated on a worker thread.
     * <p>
     * Applies request and response conversions.
     * <p>
     * Any error fails the result.
     * <p>
     * Request may be <code>null</code>.
     *
     * @param request       the request
     * @param reqConverter  the request converter
     * @param respConverter the response converter
     * @param handler       the result handler
     * @param function      the client function that executes the simple blocking request
     * @param <ReqData>     the request DataObject type
     * @param <ReqES>       the request ElasticSearch type
     * @param <RespES>      the response ElasticSearch Type
     * @param <RespData>    the response DataObject type
     */
    <ReqData extends BaseRequest, ReqES extends ActionRequest, RespES, RespData> void executeRequestBlocking(
            ReqData request,
            Converter<ReqData, ReqES> reqConverter,
            Converter<RespES, RespData> respConverter,
            Handler<AsyncResult<RespData>> handler,
            BiFunction<ReqES, Header[], RespES> function) {

        prepareRequestBlocking(
                request,
                reqConverter,
                handler,
                (req, headers, future) -> future.complete(respConverter.convert(function.apply(req, headers)))
        );
    }
    //endregion

}
