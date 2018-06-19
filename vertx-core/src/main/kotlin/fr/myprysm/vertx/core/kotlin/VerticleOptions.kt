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

package fr.myprysm.vertx.core.kotlin

import fr.myprysm.vertx.core.VerticleOptions
import io.vertx.core.DeploymentOptions

fun VerticleOptions(
        name: String? = null,
        options: io.vertx.core.DeploymentOptions? = null,
        verticle: String? = null): VerticleOptions = fr.myprysm.vertx.core.VerticleOptions().apply {

    if (name != null) {
        this.setName(name)
    }
    if (options != null) {
        this.setOptions(options)
    }
    if (verticle != null) {
        this.setVerticle(verticle)
    }
}

