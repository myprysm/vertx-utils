package fr.myprysm.vertx.elasticsearch.kotlin.action.admin.indices.create

import fr.myprysm.vertx.elasticsearch.action.admin.indices.create.Alias

fun Alias(
        filter: String? = null,
        indexRouting: String? = null,
        name: String? = null,
        searchRouting: String? = null): Alias = fr.myprysm.vertx.elasticsearch.action.admin.indices.create.Alias().apply {

    if (filter != null) {
        this.setFilter(filter)
    }
    if (indexRouting != null) {
        this.setIndexRouting(indexRouting)
    }
    if (name != null) {
        this.setName(name)
    }
    if (searchRouting != null) {
        this.setSearchRouting(searchRouting)
    }
}

