package fr.myprysm.vertx.elasticsearch.kotlin.action.admin.indices.delete

import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexResponse

/**
 * A function providing a DSL for building [fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexResponse] objects.
 *
 * DeleteIndexResponse.
 *
 * @param acknowledged 
 *
 * <p/>
 * NOTE: This function has been automatically generated from the [fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexResponse original] using Vert.x codegen.
 */
fun DeleteIndexResponse(
        acknowledged: Boolean? = null): DeleteIndexResponse = fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexResponse().apply {

    if (acknowledged != null) {
        this.setAcknowledged(acknowledged)
    }
}

