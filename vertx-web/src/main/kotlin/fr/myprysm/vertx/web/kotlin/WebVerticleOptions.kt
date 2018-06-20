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

