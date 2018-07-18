package fr.myprysm.vertx.elasticsearch.impl;

import fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsConverters;
import fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

/**
 * Cluster client working with {@link Vertx#executeBlocking(Handler, Handler)}.
 */
public class VertxAsyncClusterRestClientImpl extends BaseClusterRestClient {
    /**
     * Build a new base indices client instance.
     *
     * @param vertx  the current vertx instance
     * @param client the Elasticsearch cluster client instance
     * @param name   the name of the client
     */
    VertxAsyncClusterRestClientImpl(Vertx vertx, org.elasticsearch.client.ClusterClient client, String name) {
        super(vertx, client, name);
    }

    @Override
    public void putSettings(ClusterUpdateSettingsRequest request, Handler<AsyncResult<ClusterUpdateSettingsResponse>> handler) {
        executeRequestBlocking(
                request,
                ClusterUpdateSettingsConverters::requestToES,
                ClusterUpdateSettingsConverters::responseToDataObject,
                handler,
                client()::putSettings
        );
    }
}
