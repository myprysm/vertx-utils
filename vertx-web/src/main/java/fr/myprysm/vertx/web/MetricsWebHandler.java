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

import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.circuitbreaker.HystrixMetricHandler;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.buffer.Buffer;
import io.vertx.reactivex.ext.dropwizard.MetricsService;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import org.apache.commons.lang3.StringUtils;

/**
 * Metrics web handler to serve data to any greedy system requesting them...
 */
class MetricsWebHandler {

    private final Vertx vertx;
    private final MetricsMeasurementHandler webMetrics;
    MetricsService vertxMetrics;


    /**
     * Builds a Metrics handler on the provided router.
     *
     * @param webMetrics the requests metrics handler
     * @param router     the router for metrics
     * @param vertx      the Vert.x instance
     */
    public MetricsWebHandler(MetricsMeasurementHandler webMetrics, Router router, Vertx vertx) {
        router.get("/metrics/vertx").handler(this::handleVertxMetrics);
        router.get("/metrics/hystrix").handler(HystrixMetricHandler.create(vertx));
        router.get("/metrics/requests").handler(this::handleHttpMetrics);
        this.vertx = vertx;
        vertxMetrics = MetricsService.create(vertx);
        this.webMetrics = webMetrics;
    }

    /**
     * Handles Request throughput metrics
     *
     * @param event the incoming request
     */
    private void handleHttpMetrics(RoutingContext event) {
        respondJson(webMetrics.getSnapshot(), event);
    }

    /**
     * Handles Vert.x internal metrics when activated
     *
     * @param event the incoming request
     */
    private void handleVertxMetrics(RoutingContext event) {
        respondJson(vertxMetrics.getMetricsSnapshot(getName(event)), event);

    }

    /**
     * Returns the name of the metric to retrieve
     *
     * @param event the event request
     * @return the name, empty string if none provided
     */
    private String getName(RoutingContext event) {
        String name = event.request().getParam("name");
        if (StringUtils.isBlank(name)) {
            name = "";
        }
        return name;
    }

    /**
     * Responds to the event request.
     *
     * @param json  the metrics object to send
     * @param event the event request
     */
    private void respondJson(JsonObject json, RoutingContext event) {
        if (json == null) {
            event.response().setStatusCode(204).end();
            return;
        }

        event.response()
                .putHeader("content-type", "application/json")
                .putHeader("content-encoding", "UTF-8")
                .end(Buffer.newInstance(json.toBuffer()));
    }
}
