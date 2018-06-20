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

import fr.myprysm.vertx.web.CorsOptions
import io.vertx.core.http.HttpMethod

/**
 * A function providing a DSL for building [fr.myprysm.vertx.web.CorsOptions] objects.
 *
 * See Vert.x documentation for Cross Origin Resource Sharing.
 *
 * @param allowCredentials
 * @param allowedHeaders
 * @param allowedMethods
 * @param allowedOrigins
 * @param exposedHeaders
 * @param maxAgeSeconds 
 *
 * <p/>
 * NOTE: This function has been automatically generated from the [fr.myprysm.vertx.web.CorsOptions original] using Vert.x codegen.
 */
fun CorsOptions(
        allowCredentials: Boolean? = null,
        allowedHeaders: Iterable<String>? = null,
        allowedMethods: Iterable<HttpMethod>? = null,
        allowedOrigins: String? = null,
        exposedHeaders: Iterable<String>? = null,
        maxAgeSeconds: Int? = null): CorsOptions = fr.myprysm.vertx.web.CorsOptions().apply {

    if (allowCredentials != null) {
        this.setAllowCredentials(allowCredentials)
    }
    if (allowedHeaders != null) {
        this.setAllowedHeaders(allowedHeaders.toSet())
    }
    if (allowedMethods != null) {
        this.setAllowedMethods(allowedMethods.toSet())
    }
    if (allowedOrigins != null) {
        this.setAllowedOrigins(allowedOrigins)
    }
    if (exposedHeaders != null) {
        this.setExposedHeaders(exposedHeaders.toSet())
    }
    if (maxAgeSeconds != null) {
        this.setMaxAgeSeconds(maxAgeSeconds)
    }
}

