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

package fr.myprysm.vertx.elasticsearch.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;
import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import fr.myprysm.vertx.elasticsearch.metrics.spi.MetricsProvider;

public class DropwizardMetricsProvider implements MetricsProvider {
    private String name;
    private MetricRegistry registry;


    @Override
    public <T extends BaseRequest> RequestMetrics forClass(Class<T> requestClass) {
        return new DropwizardRequestMetrics(registry, requestClass);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void init() {
        registry = SharedMetricRegistries.getOrCreate(this.name);
    }
}
