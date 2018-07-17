package fr.myprysm.vertx.elasticsearch.kotlin.action.support

import fr.myprysm.vertx.elasticsearch.action.support.ScoreDoc

fun ScoreDoc(
        doc: Int? = null,
        score: Float? = null,
        shardIndex: Int? = null): ScoreDoc = fr.myprysm.vertx.elasticsearch.action.support.ScoreDoc().apply {

    if (doc != null) {
        this.setDoc(doc)
    }
    if (score != null) {
        this.setScore(score)
    }
    if (shardIndex != null) {
        this.setShardIndex(shardIndex)
    }
}

