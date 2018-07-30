package fr.myprysm.vertx.elasticsearch.kotlin.action.support

import fr.myprysm.vertx.elasticsearch.action.support.ShardId

fun ShardId(
        id: Int? = null,
        index: String? = null): ShardId = fr.myprysm.vertx.elasticsearch.action.support.ShardId().apply {

    if (id != null) {
        this.setId(id)
    }
    if (index != null) {
        this.setIndex(index)
    }
}

