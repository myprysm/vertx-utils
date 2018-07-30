package fr.myprysm.vertx.elasticsearch.impl;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import fr.myprysm.vertx.elasticsearch.action.FutureActionListener;
import fr.myprysm.vertx.elasticsearch.converter.CommonConverters;
import fr.myprysm.vertx.elasticsearch.converter.Converter;
import fr.myprysm.vertx.elasticsearch.metrics.DummyMetricsProvider;
import fr.myprysm.vertx.elasticsearch.metrics.RequestMetrics;
import fr.myprysm.vertx.elasticsearch.metrics.spi.MetricsProvider;
import fr.myprysm.vertx.elasticsearch.utils.BiConsumer;
import fr.myprysm.vertx.elasticsearch.utils.BiFunction;
import fr.myprysm.vertx.elasticsearch.utils.Function;
import fr.myprysm.vertx.elasticsearch.utils.TriConsumer;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.ServiceHelper;
import io.vertx.core.Vertx;
import org.apache.http.Header;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.ActionRequest;

import java.util.Collection;

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
    private final String name;
    private MetricsProvider provider;

    /**
     * Build a new client with the vertx instance.
     *
     * @param vertx the current vertx instance
     * @param name  the name of the client
     */
    BaseRestClient(Vertx vertx, String name) {
        this.vertx = requireNonNull(vertx);
        this.name = name;
        initMetrics(name);
    }

    String name() {
        return name;
    }

    private void initMetrics(String name) {
        Collection<MetricsProvider> metricsProviders = ServiceHelper.loadFactories(MetricsProvider.class);
        MetricsProvider provider;
        if (metricsProviders.isEmpty()) {
            provider = new DummyMetricsProvider();
        } else if (metricsProviders.size() == 1) {
            provider = metricsProviders.iterator().next();
        } else {
            MetricsProvider found = new DummyMetricsProvider();
            for (MetricsProvider p : metricsProviders) {
                if (!(p instanceof DummyMetricsProvider)) {
                    found = p;
                    break;
                }
            }
            provider = found;
        }

        provider.setName(name);
        provider.init();
        this.provider = provider;
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
     * @param metrics    the metrics
     * @param request    the request data object
     * @param converter  the request converter
     * @param handler    the result  handler
     * @param consumer   the consumer
     * @param <ReqData>  the request DataObject type
     * @param <ReqES>    the request ElasticSearch type
     * @param <RespData> the response DataObject type
     */
    private <ReqData extends BaseRequest, ReqES extends ActionRequest, RespData> void prepareRequest(RequestMetrics metrics,
                                                                                                     ReqData request,
                                                                                                     Converter<ReqData, ReqES> converter,
                                                                                                     Handler<AsyncResult<RespData>> handler,
                                                                                                     BiConsumer<ReqES, Header[]> consumer) {
        ReqES esRequest = null;
        Header[] headers = new Header[]{};

        metrics.startConvertRequestToES();
        if (request != null) {

            try {
                esRequest = converter.convert(request);
            } catch (Exception exc) {
                metrics.errorConvertRequestToES();
                handler.handle(Future.failedFuture(exc));
                return;
            }
            headers = CommonConverters.headersFromRequest(request);
        }
        metrics.endConvertRequestToES();

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
        requireNonNull(request, "The request cannot be null, provide at least an empty BaseRequest.");
        requireNonNull(reqConverter, "The request converter cannot be null.");
        requireNonNull(respConverter, "The response converter cannot be null.");
        requireNonNull(handler, "The response handler cannot be null.");
        requireNonNull(clientFunction, "The client function cannot be null.");

        RequestMetrics requestMetrics = provider.forClass(request.getClass());
        prepareRequest(
                requestMetrics,
                request,
                reqConverter,
                handler,
                (esRequest, headers) -> {
                    requestMetrics.startRequest();
                    // the action listener will finish to close the metrics...
                    clientFunction.consume(esRequest, new FutureActionListener<>(requestMetrics, respConverter, handler), headers);
                }
        );
    }


    /**
     * Prepares a request and executes the provided {@link TriConsumer} on a worker thread.
     * <p>
     * The clientFunction accepts the ElasticSearch Request, the headers and a future that expects the DataObject Response.
     *
     * @param request    the request data object
     * @param converter  the request converter
     * @param handler    the result  handler
     * @param clientFunction   the clientFunction
     * @param <ReqData>  the request DataObject type
     * @param <ReqES>    the request ElasticSearch type
     * @param <RespData> the response DataObject type
     */
    private <ReqData extends BaseRequest, ReqES extends ActionRequest, RespData> void prepareRequestBlocking(
            RequestMetrics metrics,
            ReqData request,
            Converter<ReqData, ReqES> converter,
            Handler<AsyncResult<RespData>> handler,
            TriConsumer<ReqES, Header[], Future<RespData>> clientFunction) {

        prepareRequest(metrics, request, converter, handler, (esRequest, headers) ->
                vertx.executeBlocking(future -> {
                    try {
                        clientFunction.consume(esRequest, headers, future);
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
     * @param respConverter  the response converter
     * @param handler        the result handler
     * @param clientFunction the client function that executes the simple blocking request
     * @param <ReqData>      the request DataObject type
     * @param <RespES>       the response ElasticSearch Type
     * @param <RespData>     the response DataObject type
     */
    final <ReqData extends BaseRequest, RespES, RespData> void executeSimpleRequestBlocking(
            ReqData request,
            Converter<RespES, RespData> respConverter,
            Handler<AsyncResult<RespData>> handler,
            Function<Header[], RespES> clientFunction) {

        executeRequestBlocking(
                request,
                req -> null,
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
     * @param clientFunction      the client function that executes the blocking request
     * @param <ReqData>     the request DataObject type
     * @param <ReqES>       the request ElasticSearch type
     * @param <RespES>      the response ElasticSearch Type
     * @param <RespData>    the response DataObject type
     */
    final <ReqData extends BaseRequest, ReqES extends ActionRequest, RespES, RespData> void executeRequestBlocking(
            ReqData request,
            Converter<ReqData, ReqES> reqConverter,
            Converter<RespES, RespData> respConverter,
            Handler<AsyncResult<RespData>> handler,
            BiFunction<ReqES, Header[], RespES> clientFunction) {
        requireNonNull(request, "The request cannot be null, provide at least an empty BaseRequest.");
        requireNonNull(reqConverter, "The request converter cannot be null.");
        requireNonNull(respConverter, "The response converter cannot be null.");
        requireNonNull(handler, "The response handler cannot be null.");
        requireNonNull(clientFunction, "The client function cannot be null.");
        RequestMetrics requestMetrics = provider.forClass(request.getClass());
        prepareRequestBlocking(
                requestMetrics,
                request,
                reqConverter,
                handler,
                (req, headers, future) -> {
                    RespES esResponse;
                    requestMetrics.startRequest();
                    try {
                        esResponse = clientFunction.apply(req, headers);
                    } catch (Exception exc) {
                        requestMetrics.errorRequest();
                        future.fail(exc);
                        return;
                    }
                    requestMetrics.endRequest();

                    requestMetrics.startConvertResponseToDataObject();
                    try {
                        future.complete(respConverter.convert(esResponse));
                    } catch (Exception exc) {
                        requestMetrics.errorConvertResponseToDataObject();
                        future.fail(exc);
                    }
                    requestMetrics.endConvertResponseToDataObject();
                }
        );
    }
    //endregion

}
