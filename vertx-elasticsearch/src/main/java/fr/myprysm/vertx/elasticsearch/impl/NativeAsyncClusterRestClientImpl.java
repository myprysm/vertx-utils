package fr.myprysm.vertx.elasticsearch.impl;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsConverters;
import fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse;
import fr.myprysm.vertx.elasticsearch.converter.Converter;
import fr.myprysm.vertx.elasticsearch.listener.JsonResponseListener;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.http.client.methods.HttpGet;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * Cluster client working with the Elasticsearch Async API.
 */
public class NativeAsyncClusterRestClientImpl extends BaseClusterRestClient {
    /**
     * Build a new base indices client instance.
     *
     * @param vertx  the current vertx instance
     * @param client the Elasticsearch cluster client instance
     * @param name   the name of the client
     */
    NativeAsyncClusterRestClientImpl(Vertx vertx, RestHighLevelClient client, String name) {
        super(vertx, client, name);
    }

    @Override
    public void putSettings(ClusterUpdateSettingsRequest request, Handler<AsyncResult<ClusterUpdateSettingsResponse>> handler) {
        executeRequestAsync(
                request,
                ClusterUpdateSettingsConverters::requestToES,
                ClusterUpdateSettingsConverters::responseToDataObject,
                handler,
                clusterClient()::putSettingsAsync
        );
    }

    @Override
    public void getSettings(BaseRequest request, Handler<AsyncResult<ClusterUpdateSettingsResponse>> handler) {
        executeRequestAsync(
                request,
                req -> null,
                (Converter<JsonObject, ClusterUpdateSettingsResponse>) ClusterUpdateSettingsResponse::new,
                handler,
                (req, listener, headers) -> client()
                        .getLowLevelClient()
                        .performRequestAsync(HttpGet.METHOD_NAME, "/_cluster/settings", new JsonResponseListener(listener), headers)
        );
    }

}
