package fr.myprysm.vertx.elasticsearch.impl;

import io.vertx.core.Vertx;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RestHighLevelClient;

import static java.util.Objects.requireNonNull;

/**
 * Base {@link fr.myprysm.vertx.elasticsearch.IndicesClient} for subclassing.
 */
abstract class BaseIndicesRestClient extends BaseRestClient implements fr.myprysm.vertx.elasticsearch.IndicesClient {

    /**
     * The indices client instance.
     */
    private final IndicesClient indicesClient;

    /**
     * The elasticsearch client instance.
     */
    private final RestHighLevelClient client;

    /**
     * Build a new base indices client instance.
     *
     * @param vertx  the current vertx instance
     * @param client the Elasticsearch indices client instance
     * @param name   the name of the client
     */
    BaseIndicesRestClient(Vertx vertx, RestHighLevelClient client, String name) {
        super(vertx, name);
        this.client = requireNonNull(client);
        this.indicesClient = client.indices();
    }

    /**
     * Get the indices client.
     *
     * @return the indices client
     */
    IndicesClient indicesClient() {
        return indicesClient;
    }

    /**
     * Get the elasticsearch client.
     *
     * @return the elasticsearch client
     */
    RestHighLevelClient client() {
        return client;
    }
}
