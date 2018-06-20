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

