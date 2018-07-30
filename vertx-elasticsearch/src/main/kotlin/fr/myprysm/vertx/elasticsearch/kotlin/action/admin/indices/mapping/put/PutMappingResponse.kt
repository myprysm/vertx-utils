package fr.myprysm.vertx.elasticsearch.kotlin.action.admin.indices.mapping.put

import fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse

/**
 * A function providing a DSL for building [fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse] objects.
 *
 * PutMappingResponse.
 *
 * @param acknowledged
 *
 * <p/>
 * NOTE: This function has been automatically generated from the [fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse original] using Vert.x codegen.
 */
fun PutMappingResponse(
        acknowledged: Boolean? = null): PutMappingResponse = fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse().apply {

    if (acknowledged != null) {
        this.setAcknowledged(acknowledged)
    }
}

