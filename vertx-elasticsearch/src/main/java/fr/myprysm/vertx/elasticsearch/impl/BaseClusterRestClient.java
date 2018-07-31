package fr.myprysm.vertx.elasticsearch.impl;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import org.elasticsearch.client.ClusterClient;
import org.elasticsearch.client.RestHighLevelClient;

import static java.util.Objects.requireNonNull;

/**
 * Base {@link fr.myprysm.vertx.elasticsearch.ClusterClient} for subclassing.
 */
abstract class BaseClusterRestClient extends BaseRestClient implements fr.myprysm.vertx.elasticsearch.ClusterClient {

    /**
     * The indices clusterClient instance.
     */
    private final ClusterClient clusterClient;
    /**
     * The elasticsearch client instance.
     */
    private final RestHighLevelClient client;

    /**
     * Build a new base indices clusterClient instance.
     *
     * @param vertx  the current vertx instance
     * @param client the Elasticsearch cluster clusterClient instance
     * @param name   the name of the clusterClient
     */
    BaseClusterRestClient(Vertx vertx, RestHighLevelClient client, String name) {
        super(vertx, name);
        this.client = requireNonNull(client);
        this.clusterClient = client.cluster();
    }

    /**
     * Get the cluster client.
     *
     * @return the cluster client
     */
    ClusterClient clusterClient() {
        return clusterClient;
    }

    /**
     * Get the elasticsearch client.
     *
     * @return the elasticsearch client
     */
    RestHighLevelClient client() {
        return client;
    }


    @Override
    public void getSettings(Handler<AsyncResult<ClusterUpdateSettingsResponse>> handler) {
        getSettings(new BaseRequest(), handler);
    }
}
