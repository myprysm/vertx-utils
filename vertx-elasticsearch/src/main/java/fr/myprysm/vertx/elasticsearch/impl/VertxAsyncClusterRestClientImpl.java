package fr.myprysm.vertx.elasticsearch.impl;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsConverters;
import fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse;
import fr.myprysm.vertx.elasticsearch.converter.CommonConverters;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import org.apache.http.client.methods.HttpGet;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;

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
    VertxAsyncClusterRestClientImpl(Vertx vertx, RestHighLevelClient client, String name) {
        super(vertx, client, name);
    }

    @Override
    public void putSettings(ClusterUpdateSettingsRequest request, Handler<AsyncResult<ClusterUpdateSettingsResponse>> handler) {
        executeRequestBlocking(
                request,
                ClusterUpdateSettingsConverters::requestToES,
                ClusterUpdateSettingsConverters::responseToDataObject,
                handler,
                clusterClient()::putSettings
        );
    }

    @Override
    public void getSettings(BaseRequest request, Handler<AsyncResult<ClusterUpdateSettingsResponse>> handler) {
        executeSimpleRequestBlocking(
                request,
                ClusterUpdateSettingsResponse::new,
                handler,
                headers -> {
                    Response response = client().getLowLevelClient().performRequest(HttpGet.METHOD_NAME, "/_cluster/settings", headers);
                    return CommonConverters.responseAsJson(response);
                }
        );
    }
}
