package fr.myprysm.vertx.elasticsearch.impl;

import io.vertx.core.Vertx;
import org.elasticsearch.client.IndicesClient;

import static java.util.Objects.requireNonNull;

/**
 * Base {@link fr.myprysm.vertx.elasticsearch.IndicesClient} for subclassing.
 */
abstract class BaseIndicesRestClient extends BaseRestClient implements fr.myprysm.vertx.elasticsearch.IndicesClient {

    /**
     * The indices client instance.
     */
    private final IndicesClient client;

    /**
     * Build a new base indices client instance.
     *
     * @param vertx  the current vertx instance
     * @param client the Elasticsearch indices client instance.
     */
    BaseIndicesRestClient(Vertx vertx, IndicesClient client) {
        super(vertx);
        this.client = requireNonNull(client);
    }

    /**
     * Get the indices client.
     *
     * @return the indices client
     */
    IndicesClient client() {
        return client;
    }
}
