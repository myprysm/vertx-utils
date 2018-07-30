package fr.myprysm.vertx.elasticsearch.kotlin.action.search.aggregations.matrix

import fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixStatsResult

fun MatrixStatsResult(
        correlation: Map<String, Double>? = null,
        count: Long? = null,
        covariance: Map<String, Double>? = null,
        kurtosis: Double? = null,
        mean: Double? = null,
        name: String? = null,
        skewness: Double? = null,
        variance: Double? = null): MatrixStatsResult = fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixStatsResult().apply {

    if (correlation != null) {
        this.setCorrelation(correlation)
    }
    if (count != null) {
        this.setCount(count)
    }
    if (covariance != null) {
        this.setCovariance(covariance)
    }
    if (kurtosis != null) {
        this.setKurtosis(kurtosis)
    }
    if (mean != null) {
        this.setMean(mean)
    }
    if (name != null) {
        this.setName(name)
    }
    if (skewness != null) {
        this.setSkewness(skewness)
    }
    if (variance != null) {
        this.setVariance(variance)
    }
}

