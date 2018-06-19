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

import fr.myprysm.vertx.core.StarterVerticle;
import fr.myprysm.vertx.core.reactivex.config.ConfigService;
import fr.myprysm.vertx.core.utils.ClassUtils;
import fr.myprysm.vertx.core.utils.Utils;
import fr.myprysm.vertx.validation.ValidationResult;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.contract.RouterFactoryException;
import io.vertx.reactivex.CompletableHelper;
import io.vertx.reactivex.config.ConfigRetriever;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.http.HttpServer;
import io.vertx.reactivex.ext.healthchecks.HealthCheckHandler;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import io.vertx.reactivex.ext.web.handler.CorsHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class WebVerticle extends AbstractVerticle {

    private final List<Endpoint> endpoints = new CopyOnWriteArrayList<>();
    private HttpServer server;
    private Router router;
    private Router monitoringRouter;
    private ConfigService configService;
    private boolean monitoring = false;
    private boolean useOpenAPI3Router = false;
    private OpenAPI3RouterFactory openAPI3RouterFactory;
    private WebVerticleOptions options;
    private JsonObject specs;
    private MetricsMeasurementHandler metricsMeasurementHandler;


    @Override
    public void start(Future<Void> startFuture) {
        this.configService = Utils.configServiceProxy(vertx);
        configure()
                .andThen(Observable.defer(this::findEndpoints))
                .flatMapCompletable(this::prepareEndpoint)
                .andThen(Completable.defer(this::startHttpServer))
                .subscribe(CompletableHelper.toObserver(startFuture));
    }

    private Completable configure() {
        JsonObject config = config();
        ValidationResult result = WebVerticleOptions.validate(config);
        if (result.isValid()) {
            options = new WebVerticleOptions(config);

            if (options.getEnableHealthChecks()) {
                configureHealthCheck();
            }

            if (options.getEnableMetrics()) {
                configureMetrics();
            }

            if (options.getUseOpenAPI3Router()) {
                useOpenAPI3Router = true;
                return OpenAPI3RouterFactory.rxCreate(vertx, options.getSpecs())
                        .doOnSuccess(this::setOpenAPI3RouterFactory)
                        .toCompletable()
                        .andThen(Completable.defer(this::prepareOpenAPI3Specs));
            } else {
                // Not an OpenAPI3 Router,
                if (options.getEnableCors()) {
                    router().route().handler(prepareCorsHandler());
                }

                // Still need to add the metrics handler at the root...
                if (options.getEnableMetrics() && metricsMeasurementHandler != null) {
                    router().route().handler(ctx -> metricsMeasurementHandler.handle(ctx.getDelegate()));
                }
            }

            return Completable.complete();
        } else {
            //noinspection ConstantConditions
            log.error("Unable to start WebVerticle, reason: {}", result.getReason().get());
            return Completable.error(result.toException());
        }
    }

    /**
     * Get the OpenAPI3 specs when enabled
     *
     * @return a completable that finishes when specs are loaded
     */
    private Completable prepareOpenAPI3Specs() {
        ConfigStoreOptions store = new ConfigStoreOptions()
                .setType("file")
                .setFormat(options.getSpecs().endsWith(".json") ? "json" : "yaml")
                .setConfig(new JsonObject().put("path", options.getSpecs()));

        ConfigRetriever retriever = ConfigRetriever.create(vertx, new ConfigRetrieverOptions().addStore(store));

        return retriever.rxGetConfig().doOnSuccess(specs -> {
            setSwaggerSpecs(specs);
            retriever.close();
        }).toCompletable();
    }

    private void setSwaggerSpecs(JsonObject specs) {
        this.specs = specs;
    }

    private void setOpenAPI3RouterFactory(OpenAPI3RouterFactory openAPI3RouterFactory) {
        this.openAPI3RouterFactory = openAPI3RouterFactory;

        if (options.getEnableCors()) {
            this.openAPI3RouterFactory.getOptions().addGlobalHandler(prepareCorsHandler().getDelegate());
        }

        if (options.getEnableMetrics() && metricsMeasurementHandler != null) {
            this.openAPI3RouterFactory.getOptions().addGlobalHandler(metricsMeasurementHandler);
        }
    }

    private void configureMetrics() {
        monitoring = true;
        metricsMeasurementHandler = new MetricsMeasurementHandler();
        new MetricsWebHandler(metricsMeasurementHandler, monitoringRouter(), vertx);
    }

    private void configureHealthCheck() {
        monitoring = true;
        monitoringRouter().get("/hc").handler(getHealthCheckHandler());
    }

    private HealthCheckHandler getHealthCheckHandler() {
        return HealthCheckHandler.createWithHealthChecks(StarterVerticle.getHealthChecks(vertx));
    }

    /**
     * Get the cors handler from the options
     *
     * @return the cors handler
     */
    private CorsHandler prepareCorsHandler() {
        log.info("Enabling Cross Origin Resource Sharing");
        CorsOptions cors = options.getCors();
        CorsHandler handler = CorsHandler.create(cors.getAllowedOrigins())
                .allowedHeaders(cors.getAllowedHeaders())
                .exposedHeaders(cors.getExposedHeaders());

        if (cors.getAllowCredentials() != null) {
            handler.allowCredentials(cors.getAllowCredentials());
        }

        // Surprisingly Rx CorsHandler does not propose allowedMethods
        cors.getAllowedMethods().forEach(handler::allowedMethod);
        return handler;
    }

    private Completable startHttpServer() {
        return configService.rxGetObject("http")
                .map(HttpServerOptions::new)
                .map(vertx::createHttpServer)
                .map(this::configureRouter)
                .flatMap(HttpServer::rxListen)
                .doOnSuccess(server -> this.server = server)
                .doOnSuccess(server -> log.info("HTTP Server started on port {}", server.actualPort()))
                .toCompletable();
    }

    /**
     * Prepares the appropriate router,
     * binds monitoring when requested,
     * and register the router to the server
     *
     * @param httpServer the server
     * @return the server
     */
    private HttpServer configureRouter(HttpServer httpServer) {
        Router router = useOpenAPI3Router ? prepareOpenAPI3Router() : router();

        if (monitoring) {
            router.mountSubRouter(options.getMonitoringPath(), monitoringRouter());
        }

        if (specs != null) {
            router.get("/swagger.json").handler(event -> event.response()
                    .putHeader("content-encoding", "UTF-8")
                    .putHeader("content-type", "application/json")
                    .end(specs.toString()));
        }

        return httpServer.requestHandler(router::accept);
    }

    /**
     * Binds all the OpenAPI endpoints with the router factory and provides the router.
     * <p>
     * Operation binding failures fail silently to allow unmapped endpoints with an outdated/alternate spec file.
     *
     * @return the OpenAPI3 router
     */
    private Router prepareOpenAPI3Router() {
        for (Endpoint e : endpoints) {
            if (e instanceof OpenAPIEndpointInternal) {
                Pair<String, Handler<RoutingContext>> pair = ((OpenAPIEndpointInternal) e).operationIdToHandler();
                try {
                    openAPI3RouterFactory.addHandlerByOperationId(pair.getKey(), pair.getValue());
                    log.info("Registered operation [{}].", pair.getKey());
                } catch (RouterFactoryException exc) {
                    log.info("Unable to register operation {}", pair.getKey());
                }
            }
        }
        return openAPI3RouterFactory.getRouter();
    }


    /**
     * Locates all the valid {@link Endpoint}s (public default constructor, concrete) in the classpath
     * and streams them as an observable.
     *
     * @return an observable of all the {@link Endpoint} implementations
     */
    private Observable<? extends Class<?>> findEndpoints() {
        return vertx.<ScanResult>rxExecuteBlocking(future -> future.complete(ClassUtils.getScan()))
                .map(scan -> scan.getNamesOfClassesImplementing(Endpoint.class))
                .flatMapObservable(Observable::fromIterable)
                .map(ClassUtils::getClass)
                .filter(clazz -> Modifier.isPublic(clazz.getModifiers()) && !Modifier.isAbstract(clazz.getModifiers()));

    }


    /**
     * Creates a new Endpoint an initializes it with vertx
     *
     * @param clazz the endpoint class
     * @return a completable that finished when the endpoint is registered
     */
    private Completable prepareEndpoint(Class<?> clazz) {
        return Single.fromCallable(() -> {
            Endpoint endpoint = (Endpoint) clazz.getConstructor().newInstance();
            endpoint.init(vertx);
            return endpoint;
        }).doOnSuccess(this::addEndpoint).flatMapCompletable(this::register);
    }

    private void addEndpoint(Endpoint endpoint) {
        this.endpoints.add(endpoint);
    }

    /**
     * Get the classic router with BodyHandler configured.
     * Singleton-like, please always access this router through here
     *
     * @return the router
     */
    private Router router() {
        if (router == null) {
            router = Router.router(vertx);
            router.route().handler(BodyHandler.create());
        }

        return router;
    }

    /**
     * Monitoring sub router to be mounted on the appropriate router
     * when monitoring is enabled
     *
     * @return the router
     */
    private Router monitoringRouter() {
        if (monitoringRouter == null) {
            monitoringRouter = Router.router(vertx);
        }

        return monitoringRouter;
    }


    @Override
    public void stop(Future<Void> stopFuture) {
        this.stopHttpServer()
                .andThen(Completable.defer(this::unregisterEndpoints))
                .subscribe(CompletableHelper.toObserver(stopFuture));
    }

    /**
     * Unregisters the endpoints (if any).
     *
     * @return a completable that finishes when all the endpoints are unregistered
     */
    private Completable unregisterEndpoints() {
        return endpoints.size() > 0
                ? Observable.fromIterable(endpoints).flatMapCompletable(Endpoint::unregister, true)
                : Completable.complete();
    }

    private Completable stopHttpServer() {
        return server != null ? server.rxClose() : Completable.complete();
    }

    /**
     * Whether configure (OpenAPI3) or register endpoint (classic).
     *
     * @param endpoint the endpoint
     * @return a completable that finished when the endpoint is configured/registered
     */
    private Completable register(Endpoint endpoint) {
        return useOpenAPI3Router ? tryConfigureEndpoint(endpoint) : endpoint.register(router());
    }

    /**
     * Triggers configuration for an endpoint when it is an OpenAPIEndpoint.
     *
     * @param endpoint the endpoint to configure
     * @return a completable that finished when the endpoint is configured
     */
    private Completable tryConfigureEndpoint(Endpoint endpoint) {
        return endpoint instanceof OpenAPIEndpoint ? ((OpenAPIEndpoint) endpoint).configure() : Completable.complete();
    }
}
