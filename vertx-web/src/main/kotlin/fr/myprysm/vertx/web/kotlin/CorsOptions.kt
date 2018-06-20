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

