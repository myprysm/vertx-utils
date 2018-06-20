package fr.myprysm.vertx.service.kotlin

import fr.myprysm.vertx.service.ServiceVerticleOptions
import fr.myprysm.vertx.service.ServiceOptions

fun ServiceVerticleOptions(
  services: Map<String, fr.myprysm.vertx.service.ServiceOptions>? = null): ServiceVerticleOptions = fr.myprysm.vertx.service.ServiceVerticleOptions().apply {

  if (services != null) {
    this.setServices(services)
  }
}

