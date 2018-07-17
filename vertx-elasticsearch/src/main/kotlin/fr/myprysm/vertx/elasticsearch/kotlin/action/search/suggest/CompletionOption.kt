package fr.myprysm.vertx.elasticsearch.kotlin.action.search.suggest

import fr.myprysm.vertx.elasticsearch.action.search.suggest.CompletionOption
import fr.myprysm.vertx.elasticsearch.action.search.SearchHit
import fr.myprysm.vertx.elasticsearch.action.support.ScoreDoc

fun CompletionOption(
        collateMatch: Boolean? = null,
        doc: fr.myprysm.vertx.elasticsearch.action.support.ScoreDoc? = null,
        highlighted: String? = null,
        hit: fr.myprysm.vertx.elasticsearch.action.search.SearchHit? = null,
        score: Float? = null,
        text: String? = null): CompletionOption = fr.myprysm.vertx.elasticsearch.action.search.suggest.CompletionOption().apply {

    if (collateMatch != null) {
        this.setCollateMatch(collateMatch)
    }
    if (doc != null) {
        this.setDoc(doc)
    }
    if (highlighted != null) {
        this.setHighlighted(highlighted)
    }
    if (hit != null) {
        this.setHit(hit)
    }
    if (score != null) {
        this.setScore(score)
    }
    if (text != null) {
        this.setText(text)
    }
}

