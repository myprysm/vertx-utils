package fr.myprysm.vertx.elasticsearch.kotlin.action.support

import fr.myprysm.vertx.elasticsearch.action.support.AcknowledgedResponse

/**
 * A function providing a DSL for building [fr.myprysm.vertx.elasticsearch.action.support.AcknowledgedResponse] objects.
 *
 * Response that indicates whether it has been acknowledged.
 *
 * @param acknowledged 
 *
 * <p/>
 * NOTE: This function has been automatically generated from the [fr.myprysm.vertx.elasticsearch.action.support.AcknowledgedResponse original] using Vert.x codegen.
 */
fun AcknowledgedResponse(
  acknowledged: Boolean? = null): AcknowledgedResponse = fr.myprysm.vertx.elasticsearch.action.support.AcknowledgedResponse().apply {

  if (acknowledged != null) {
    this.setAcknowledged(acknowledged)
  }
}

