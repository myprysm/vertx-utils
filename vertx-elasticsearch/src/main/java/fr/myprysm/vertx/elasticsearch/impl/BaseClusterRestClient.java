package fr.myprysm.vertx.elasticsearch.impl;

import io.vertx.core.Vertx;
import org.elasticsearch.client.ClusterClient;

import static java.util.Objects.requireNonNull;

/**
 * Base {@link fr.myprysm.vertx.elasticsearch.ClusterClient} for subclassing.
 */
abstract class BaseClusterRestClient extends BaseRestClient implements fr.myprysm.vertx.elasticsearch.ClusterClient {

    /**
     * The indices client instance.
     */
    private final ClusterClient client;

    /**
     * Build a new base indices client instance.
     *
     * @param vertx  the current vertx instance
     * @param client the Elasticsearch cluster client instance
     * @param name   the name of the client
     */
    BaseClusterRestClient(Vertx vertx, ClusterClient client, String name) {
        super(vertx, name);
        this.client = requireNonNull(client);
    }

    /**
     * Get the cluster client.
     *
     * @return the cluster client
     */
    ClusterClient client() {
        return client;
    }
}
