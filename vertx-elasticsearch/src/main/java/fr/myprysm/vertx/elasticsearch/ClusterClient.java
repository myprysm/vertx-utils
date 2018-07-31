package fr.myprysm.vertx.elasticsearch;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/**
 * Vertx Elasticsearch cluster client.
 * <p>
 * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster.html">Cluster API on elastic.co</a>
 */
@VertxGen
public interface ClusterClient {

    /**
     * Asynchronously updates cluster wide specific settings using the Cluster Update Settings API.
     * <p>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-update-settings.html"> Cluster Update Settings
     * API on elastic.co</a>
     *
     * @param request the request
     * @param handler the handler
     */
    void putSettings(ClusterUpdateSettingsRequest request, Handler<AsyncResult<ClusterUpdateSettingsResponse>> handler);

    /**
     * Asynchronously get cluster wide specific settings using the Cluster Update Settings API.
     * <p>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-update-settings.html"> Cluster Update Settings
     * API on elastic.co</a>
     *
     * @param request the request
     * @param handler the handler
     */
    void getSettings(BaseRequest request, Handler<AsyncResult<ClusterUpdateSettingsResponse>> handler);

    /**
     * Asynchronously get cluster wide specific settings using the Cluster Update Settings API.
     * <p>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-update-settings.html"> Cluster Update Settings
     * API on elastic.co</a>
     *
     * @param handler the handler
     */
    void getSettings(Handler<AsyncResult<ClusterUpdateSettingsResponse>> handler);
}
