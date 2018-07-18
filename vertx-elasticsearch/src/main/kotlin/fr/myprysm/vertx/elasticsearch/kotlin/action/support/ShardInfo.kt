package fr.myprysm.vertx.elasticsearch.kotlin.action.support

import fr.myprysm.vertx.elasticsearch.action.support.ShardInfo
import fr.myprysm.vertx.elasticsearch.action.support.ShardInfoFailure

fun ShardInfo(
  failed: Int? = null,
  failures: Iterable<fr.myprysm.vertx.elasticsearch.action.support.ShardInfoFailure>? = null,
  successful: Int? = null,
  total: Int? = null): ShardInfo = fr.myprysm.vertx.elasticsearch.action.support.ShardInfo().apply {

  if (failed != null) {
    this.setFailed(failed)
  }
  if (failures != null) {
    this.setFailures(failures.toList())
  }
  if (successful != null) {
    this.setSuccessful(successful)
  }
  if (total != null) {
    this.setTotal(total)
  }
}

