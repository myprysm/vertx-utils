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

package fr.myprysm.vertx.service.kotlin

import fr.myprysm.vertx.service.ServiceOptions

fun ServiceOptions(
        address: String? = null,
        discovery: Boolean? = null,
        facade: String? = null,
        factoryMethod: String? = null,
        healthCheck: Boolean? = null,
        healthCheckName: String? = null,
        healthCheckTimeout: Long? = null,
        implementation: String? = null,
        name: String? = null,
        register: Boolean? = null): ServiceOptions = fr.myprysm.vertx.service.ServiceOptions().apply {

    if (address != null) {
        this.setAddress(address)
    }
    if (discovery != null) {
        this.setDiscovery(discovery)
    }
    if (facade != null) {
        this.setFacade(facade)
    }
    if (factoryMethod != null) {
        this.setFactoryMethod(factoryMethod)
    }
    if (healthCheck != null) {
        this.setHealthCheck(healthCheck)
    }
    if (healthCheckName != null) {
        this.setHealthCheckName(healthCheckName)
    }
    if (healthCheckTimeout != null) {
        this.setHealthCheckTimeout(healthCheckTimeout)
    }
    if (implementation != null) {
        this.setImplementation(implementation)
    }
    if (name != null) {
        this.setName(name)
    }
    if (register != null) {
        this.setRegister(register)
    }
}

