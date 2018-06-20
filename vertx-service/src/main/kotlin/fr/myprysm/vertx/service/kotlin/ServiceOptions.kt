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

