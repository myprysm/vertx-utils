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

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.dropwizard.ThroughputTimer;
import io.vertx.ext.dropwizard.impl.Helper;
import io.vertx.ext.web.RoutingContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Handler that measures the response <b>write</b> time for metrics.
 * <p>
 * Time is measured until the last byte of the response has been written.
 * (ancillary async processes are not measured...)
 */
class MetricsMeasurementHandler implements Handler<RoutingContext> {
    public static final String REGISTRY_NAME = "http-requests";
    /**
     * Shared registry across all WebVerticle instances to sum all endpoints statistics in 1 endpoint
     * <p>
     * Some statistics on shared paths across different WebVerticles may be overwritten
     */
    private static final MetricRegistry REGISTRY = SharedMetricRegistries.getOrCreate(REGISTRY_NAME);
    /**
     * Shared cache across all WebVerticle instances
     */
    private static final ConcurrentHashMap<String, ThroughputTimer> CACHE = new ConcurrentHashMap<>();


    /**
     * Measures the time to respond to a client request.
     *
     * @param event the event request
     */
    @Override
    public void handle(RoutingContext event) {
        long start = System.nanoTime();
        event.response().bodyEndHandler(zoid -> {
            try {
                long duration = System.nanoTime() - start;
                getOrCreate(event.currentRoute().getPath()).update(duration, TimeUnit.NANOSECONDS);
            } catch (Exception exc) {
                // Silence is golden, some errors wont affect too much the measurement.
            }
        });
        event.next();
    }

    /**
     * Initializes a new throughput timer for the requested path.
     *
     * @param path the request path
     * @return the timer
     */
    private ThroughputTimer getOrCreate(String path) {
        return CACHE.putIfAbsent(path, throughputTimer(path));
    }

    /**
     * Get a snapshot of the current request metrics over the last 15 minutes.
     *
     * @return the metrics
     */
    JsonObject getSnapshot() {
        Map<String, Object> map = REGISTRY.getMetrics().
                entrySet().
                stream().
                collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> Helper.convertMetric(e.getValue(), TimeUnit.SECONDS, TimeUnit.MILLISECONDS)));
        return new JsonObject(map);
    }

    /**
     * Provides a new {@link ThroughputTimer} when requested, fallbacks to an unregistered timer when some error occurs
     *
     * @param name the name of the timer
     * @return the timer
     */
    private ThroughputTimer throughputTimer(String name) {
        Metric metric = REGISTRY.getMetrics().get(name);
        if (metric == null) {
            return REGISTRY.register(name, new ThroughputTimer());
        } else if (metric instanceof ThroughputTimer) {
            return (ThroughputTimer) metric;
        } else {
            return new ThroughputTimer();
        }
    }
}
