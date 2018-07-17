package fr.myprysm.vertx.elasticsearch;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import fr.myprysm.vertx.elasticsearch.action.bulk.BulkRequest;
import fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponse;
import fr.myprysm.vertx.elasticsearch.action.delete.DeleteRequest;
import fr.myprysm.vertx.elasticsearch.action.delete.DeleteResponse;
import fr.myprysm.vertx.elasticsearch.action.get.GetRequest;
import fr.myprysm.vertx.elasticsearch.action.get.GetResponse;
import fr.myprysm.vertx.elasticsearch.action.get.MultiGetRequest;
import fr.myprysm.vertx.elasticsearch.action.get.MultiGetResponse;
import fr.myprysm.vertx.elasticsearch.action.index.IndexRequest;
import fr.myprysm.vertx.elasticsearch.action.index.IndexResponse;
import fr.myprysm.vertx.elasticsearch.action.main.MainResponse;
import fr.myprysm.vertx.elasticsearch.action.search.ClearScrollRequest;
import fr.myprysm.vertx.elasticsearch.action.search.ClearScrollResponse;
import fr.myprysm.vertx.elasticsearch.action.search.MultiSearchRequest;
import fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponse;
import fr.myprysm.vertx.elasticsearch.action.search.SearchRequest;
import fr.myprysm.vertx.elasticsearch.action.search.SearchResponse;
import fr.myprysm.vertx.elasticsearch.action.search.SearchScrollRequest;
import fr.myprysm.vertx.elasticsearch.action.update.UpdateRequest;
import fr.myprysm.vertx.elasticsearch.action.update.UpdateResponse;
import fr.myprysm.vertx.elasticsearch.impl.ElasticsearchClientFactory;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.UUID;

/**
 * Vertx Elasticsearch client.
 * <p>
 * See Elasticsearch documentation for precise usage (either
 * java client
 * or
 * <a href="https://www.elastic.co/guide/en/elasticsearch/client/java-rest/master/">rest client</a>
 * ).
 */
@VertxGen
public interface ElasticsearchClient {

    /**
     * The name of the default pool.
     */
    String DEFAULT_POOL_NAME = "DEFAULT_POOL";

    /**
     * Close the client.
     */
    void close();

    /**
     * Provides an {@link IndicesClient} which can be used to access the Indices API.
     * <p>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/indices.html">Indices API on elastic.co</a>
     *
     * @return the indices client
     */
    IndicesClient indices();

    /**
     * Provides a {@link ClusterClient} which can be used to access the Cluster API.
     * <p>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster.html">Cluster API on elastic.co</a>
     *
     * @return the cluster client
     */
    ClusterClient cluster();

    /**
     * Asynchronously pings the remote Elasticsearch cluster and returns true if the ping succeeded, false otherwise.
     *
     * @param request the optional request
     * @param handler the handler
     */
    void ping(BaseRequest request, Handler<AsyncResult<Boolean>> handler);

    /**
     * Asynchronously pings the remote Elasticsearch cluster and returns true if the ping succeeded, false otherwise.
     *
     * @param handler the handler
     */
    void ping(Handler<AsyncResult<Boolean>> handler);

    /**
     * Asynchronously get the cluster info otherwise provided when sending an HTTP request to port 9200.
     *
     * @param request the optional request
     * @param handler the handler
     */
    void info(BaseRequest request, Handler<AsyncResult<MainResponse>> handler);

    /**
     * Asynchronously get the cluster info otherwise provided when sending an HTTP request to port 9200.
     *
     * @param handler the handler
     */
    void info(Handler<AsyncResult<MainResponse>> handler);

    /**
     * Asynchronously retrieves a document by id using the Get API.
     * <p>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html">Get API on elastic.co</a>
     *
     * @param request the request
     * @param handler the handler
     */
    void get(GetRequest request, Handler<AsyncResult<GetResponse>> handler);

    /**
     * Asynchronously checks for the existence of a document. Returns true if it exists, false otherwise.
     * <p>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html">Get API on elastic.co</a>
     *
     * @param request the request
     * @param handler the handler
     */
    void exists(GetRequest request, Handler<AsyncResult<Boolean>> handler);

    /**
     * Asynchronously retrieves multiple documents by id using the Multi Get API.
     * <p>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-multi-get.html">Multi Get API on elastic.co</a>
     *
     * @param request the request
     * @param handler the handler
     */
    void multiGet(MultiGetRequest request, Handler<AsyncResult<MultiGetResponse>> handler);

    /**
     * Asynchronously index a document using the Index API.
     * <p>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-index_.html">Index API on elastic.co</a>
     *
     * @param request the request
     * @param handler the handler
     */
    void index(IndexRequest request, Handler<AsyncResult<IndexResponse>> handler);

    /**
     * Asynchronously updates a document using the Update API.
     * <p>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update.html">Update API on elastic.co</a>
     *
     * @param request the request
     * @param handler the handler
     */
    void update(UpdateRequest request, Handler<AsyncResult<UpdateResponse>> handler);

    /**
     * Asynchronously deletes a document by id using the Delete API.
     * <p>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-delete.html">Delete API on elastic.co</a>
     *
     * @param request the request
     * @param handler the handler
     */
    void delete(DeleteRequest request, Handler<AsyncResult<DeleteResponse>> handler);

    /**
     * Asynchronously executes a search using the Search API
     * <p>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/search-search.html">Search API on elastic.co</a>
     *
     * @param request the request
     * @param handler the handler
     */
    void search(SearchRequest request, Handler<AsyncResult<SearchResponse>> handler);

    /**
     * Asynchronously executes a multi search using the msearch API
     * <p>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/search-multi-search.html">Multi search API on
     * elastic.co</a>
     *
     * @param request the request
     * @param handler the handler
     */
    void multiSearch(MultiSearchRequest request, Handler<AsyncResult<MultiSearchResponse>> handler);


    /**
     * Asynchronously executes a search using the Search Scroll API
     * <p>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/search-request-scroll.html">Search Scroll
     * API on elastic.co</a>
     *
     * @param request the request
     * @param handler the handler
     */
    void searchScroll(SearchScrollRequest request, Handler<AsyncResult<SearchResponse>> handler);

    /**
     * Asynchronously clears one or more scroll ids using the Clear Scroll API
     * <p>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/search-request-scroll.html#_clear_scroll_api">
     * Clear Scroll API on elastic.co</a>
     *
     * @param request the request
     * @param handler the handler
     */
    void clearScroll(ClearScrollRequest request, Handler<AsyncResult<ClearScrollResponse>> handler);
//
//    /**
//     * Asynchronously executes a request using the Ranking Evaluation API.
//     * <p>
//     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/search-rank-eval.html">Ranking Evaluation API
//     * on elastic.co</a>
//*
//     * @param request the request
//     * @param handler the handler
//     */
//    void rankEval(RankEvalRequest request, Handler<AsyncResult<RankEvalResponse>> handler);
//

    /**
     * Asynchronously executes a bulk request using the Bulk API
     * <p>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-bulk.html">Bulk API on elastic.co</a>
     *
     * @param request the request
     * @param handler the handler
     */
    void bulk(BulkRequest request, Handler<AsyncResult<BulkResponse>> handler);

    /**
     * Create an ElasticSearch client which maintains its own data source.
     *
     * @param vertx  the Vert.x instance
     * @param config the configuration
     * @return the client
     */
    static ElasticsearchClient createNonShared(Vertx vertx, JsonObject config) {
        return ElasticsearchClientFactory.create(vertx, config, UUID.randomUUID().toString());
    }

    /**
     * Create an ElasticSearch client which maintains its own data source.
     *
     * @param vertx  the Vert.x instance
     * @param config the configuration
     * @return the client
     */
    @GenIgnore
    static ElasticsearchClient createNonShared(Vertx vertx, ElasticsearchClientOptions config) {
        return createNonShared(vertx, config.toJson());
    }

    /**
     * Like {@link #createShared(io.vertx.core.Vertx, JsonObject, String)} but with the default data source name.
     *
     * @param vertx  the Vert.x instance
     * @param config the configuration
     * @return the client
     */
    static ElasticsearchClient createShared(Vertx vertx, JsonObject config) {
        return ElasticsearchClientFactory.create(vertx, config, DEFAULT_POOL_NAME);
    }

    /**
     * Like {@link #createShared(io.vertx.core.Vertx, JsonObject, String)} but with the default data source name.
     *
     * @param vertx  the Vert.x instance
     * @param config the configuration
     * @return the client
     */
    @GenIgnore
    static ElasticsearchClient createShared(Vertx vertx, ElasticsearchClientOptions config) {
        return createShared(vertx, config.toJson());
    }

    /**
     * Create an ElasticSearch client which shares its data source with any other Mongo clients created with the same
     * client name.
     *
     * @param vertx      the Vert.x instance
     * @param config     the configuration
     * @param clientName the client name
     * @return the client
     */
    static ElasticsearchClient createShared(Vertx vertx, JsonObject config, String clientName) {
        return ElasticsearchClientFactory.create(vertx, config, clientName);
    }

    /**
     * Create an ElasticSearch client which shares its data source with any other Mongo clients created with the same
     * client name.
     *
     * @param vertx      the Vert.x instance
     * @param config     the configuration
     * @param clientName the client name
     * @return the client
     */
    @GenIgnore
    static ElasticsearchClient createShared(Vertx vertx, ElasticsearchClientOptions config, String clientName) {
        return createShared(vertx, config.toJson(), clientName);
    }
}
