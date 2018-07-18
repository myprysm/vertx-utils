package fr.myprysm.vertx.elasticsearch.kotlin.action.search

import fr.myprysm.vertx.elasticsearch.action.search.NestedIdentity

fun NestedIdentity(
  child: fr.myprysm.vertx.elasticsearch.action.search.NestedIdentity? = null,
  field: String? = null,
  offset: Int? = null): NestedIdentity = fr.myprysm.vertx.elasticsearch.action.search.NestedIdentity().apply {

  if (child != null) {
    this.setChild(child)
  }
  if (field != null) {
    this.setField(field)
  }
  if (offset != null) {
    this.setOffset(offset)
  }
}

