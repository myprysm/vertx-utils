package fr.myprysm.vertx.elasticsearch.kotlin

import fr.myprysm.vertx.elasticsearch.ElasticsearchClientOptions
import fr.myprysm.vertx.elasticsearch.HttpHost

fun ElasticsearchClientOptions(
        defaultHeaders: Map<String, String>? = null,
        hosts: Iterable<fr.myprysm.vertx.elasticsearch.HttpHost>? = null,
        maxRetryTimeout: Int? = null,
        pathPrefix: String? = null,
        useNativeAsyncAPI: Boolean? = null): ElasticsearchClientOptions = fr.myprysm.vertx.elasticsearch.ElasticsearchClientOptions().apply {

    if (defaultHeaders != null) {
        this.setDefaultHeaders(defaultHeaders)
    }
    if (hosts != null) {
        this.setHosts(hosts.toList())
    }
    if (maxRetryTimeout != null) {
        this.setMaxRetryTimeout(maxRetryTimeout)
    }
    if (pathPrefix != null) {
        this.setPathPrefix(pathPrefix)
    }
    if (useNativeAsyncAPI != null) {
        this.setUseNativeAsyncAPI(useNativeAsyncAPI)
    }
}

