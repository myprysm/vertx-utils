package fr.myprysm.vertx.elasticsearch.kotlin

import fr.myprysm.vertx.elasticsearch.HttpHost

/**
 * A function providing a DSL for building [fr.myprysm.vertx.elasticsearch.HttpHost] objects.
 *
 * HttpHost.
 *
 * @param hostname
 * @param port
 * @param protocol
 *
 * <p/>
 * NOTE: This function has been automatically generated from the [fr.myprysm.vertx.elasticsearch.HttpHost original] using Vert.x codegen.
 */
fun HttpHost(
        hostname: String? = null,
        port: Int? = null,
        protocol: String? = null): HttpHost = fr.myprysm.vertx.elasticsearch.HttpHost().apply {

    if (hostname != null) {
        this.setHostname(hostname)
    }
    if (port != null) {
        this.setPort(port)
    }
    if (protocol != null) {
        this.setProtocol(protocol)
    }
}

