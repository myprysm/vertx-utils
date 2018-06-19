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

package fr.myprysm.vertx.web.kotlin

import fr.myprysm.vertx.web.WebVerticleOptions
import fr.myprysm.vertx.web.CorsOptions

fun WebVerticleOptions(
        cors: fr.myprysm.vertx.web.CorsOptions? = null,
        enableCors: Boolean? = null,
        enableHealthChecks: Boolean? = null,
        enableMetrics: Boolean? = null,
        monitoringPath: String? = null,
        specs: String? = null,
        useOpenAPI3Router: Boolean? = null): WebVerticleOptions = fr.myprysm.vertx.web.WebVerticleOptions().apply {

    if (cors != null) {
        this.setCors(cors)
    }
    if (enableCors != null) {
        this.setEnableCors(enableCors)
    }
    if (enableHealthChecks != null) {
        this.setEnableHealthChecks(enableHealthChecks)
    }
    if (enableMetrics != null) {
        this.setEnableMetrics(enableMetrics)
    }
    if (monitoringPath != null) {
        this.setMonitoringPath(monitoringPath)
    }
    if (specs != null) {
        this.setSpecs(specs)
    }
    if (useOpenAPI3Router != null) {
        this.setUseOpenAPI3Router(useOpenAPI3Router)
    }
}

