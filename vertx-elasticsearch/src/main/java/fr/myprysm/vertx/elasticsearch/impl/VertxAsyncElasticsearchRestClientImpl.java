package fr.myprysm.vertx.elasticsearch.impl;

import fr.myprysm.vertx.elasticsearch.ClusterClient;
import fr.myprysm.vertx.elasticsearch.ElasticsearchClientOptions;
import fr.myprysm.vertx.elasticsearch.IndicesClient;
import fr.myprysm.vertx.elasticsearch.action.bulk.BulkConverters;
import fr.myprysm.vertx.elasticsearch.action.bulk.BulkRequest;
import fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponse;
import fr.myprysm.vertx.elasticsearch.action.delete.DeleteConverters;
import fr.myprysm.vertx.elasticsearch.action.delete.DeleteRequest;
import fr.myprysm.vertx.elasticsearch.action.delete.DeleteResponse;
import fr.myprysm.vertx.elasticsearch.action.get.GetConverters;
import fr.myprysm.vertx.elasticsearch.action.get.GetRequest;
import fr.myprysm.vertx.elasticsearch.action.get.GetResponse;
import fr.myprysm.vertx.elasticsearch.action.get.MultiGetConverters;
import fr.myprysm.vertx.elasticsearch.action.get.MultiGetRequest;
import fr.myprysm.vertx.elasticsearch.action.get.MultiGetResponse;
import fr.myprysm.vertx.elasticsearch.action.index.IndexConverters;
import fr.myprysm.vertx.elasticsearch.action.index.IndexRequest;
import fr.myprysm.vertx.elasticsearch.action.index.IndexResponse;
import fr.myprysm.vertx.elasticsearch.action.search.ClearScrollConverters;
import fr.myprysm.vertx.elasticsearch.action.search.ClearScrollRequest;
import fr.myprysm.vertx.elasticsearch.action.search.ClearScrollResponse;
import fr.myprysm.vertx.elasticsearch.action.search.MultiSearchConverters;
import fr.myprysm.vertx.elasticsearch.action.search.MultiSearchRequest;
import fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponse;
import fr.myprysm.vertx.elasticsearch.action.search.SearchConverters;
import fr.myprysm.vertx.elasticsearch.action.search.SearchRequest;
import fr.myprysm.vertx.elasticsearch.action.search.SearchResponse;
import fr.myprysm.vertx.elasticsearch.action.search.SearchScrollConverters;
import fr.myprysm.vertx.elasticsearch.action.search.SearchScrollRequest;
import fr.myprysm.vertx.elasticsearch.action.update.UpdateConverters;
import fr.myprysm.vertx.elasticsearch.action.update.UpdateRequest;
import fr.myprysm.vertx.elasticsearch.action.update.UpdateResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Elasticsearch client working with {@link Vertx#executeBlocking(Handler, Handler)}.
 */
class VertxAsyncElasticsearchRestClientImpl extends BaseElasticsearchRestClient {

    /**
     * Build a new client with the options and the provided name.
     *
     * @param vertx      the current vertx instance
     * @param options    the options
     * @param clientName the client name
     */
    VertxAsyncElasticsearchRestClientImpl(Vertx vertx, ElasticsearchClientOptions options, String clientName) {
        super(vertx, options, clientName);
    }

    @Override
    Pair<IndicesClient, ClusterClient> getClients() {
        return Pair.of(
                new VertxAsyncIndicesRestClientImpl(vertx(), client(), name()),
                new VertxAsyncClusterRestClientImpl(vertx(), client(), name())
        );
    }

    @Override
    public void get(GetRequest request, Handler<AsyncResult<GetResponse>> handler) {
        executeRequestBlocking(
                request,
                GetConverters::requestToES,
                GetConverters::responseToDataObject,
                handler,
                client()::get
        );
    }

    @Override
    public void exists(GetRequest request, Handler<AsyncResult<Boolean>> handler) {
        executeRequestBlocking(
                request,
                GetConverters::requestToES,
                res -> res,
                handler,
                client()::exists
        );
    }

    @Override
    public void multiGet(MultiGetRequest request, Handler<AsyncResult<MultiGetResponse>> handler) {
        executeRequestBlocking(
                request,
                MultiGetConverters::requestToES,
                MultiGetConverters::responseToDataObject,
                handler,
                client()::multiGet
        );
    }

    @Override
    public void index(IndexRequest request, Handler<AsyncResult<IndexResponse>> handler) {
        executeRequestBlocking(
                request,
                IndexConverters::requestToES,
                IndexConverters::responseToDataObject,
                handler,
                client()::index
        );
    }

    @Override
    public void update(UpdateRequest request, Handler<AsyncResult<UpdateResponse>> handler) {
        executeRequestBlocking(
                request,
                UpdateConverters::requestToES,
                UpdateConverters::responseToDataObject,
                handler,
                client()::update
        );
    }

    @Override
    public void delete(DeleteRequest request, Handler<AsyncResult<DeleteResponse>> handler) {
        executeRequestBlocking(
                request,
                DeleteConverters::requestToES,
                DeleteConverters::responseToDataObject,
                handler,
                client()::delete
        );
    }

    @Override
    public void search(SearchRequest request, Handler<AsyncResult<SearchResponse>> handler) {
        executeRequestBlocking(
                request,
                SearchConverters::requestToES,
                SearchConverters::responseToDataObject,
                handler,
                client()::search
        );
    }

    @Override
    public void multiSearch(MultiSearchRequest request, Handler<AsyncResult<MultiSearchResponse>> handler) {
        executeRequestBlocking(
                request,
                MultiSearchConverters::requestToES,
                MultiSearchConverters::responseToDataObject,
                handler,
                client()::multiSearch
        );
    }

    @Override
    public void searchScroll(SearchScrollRequest request, Handler<AsyncResult<SearchResponse>> handler) {
        executeRequestBlocking(
                request,
                SearchScrollConverters::requestToES,
                SearchConverters::responseToDataObject,
                handler,
                client()::searchScroll
        );
    }

    @Override
    public void clearScroll(ClearScrollRequest request, Handler<AsyncResult<ClearScrollResponse>> handler) {
        executeRequestBlocking(
                request,
                ClearScrollConverters::requestToES,
                ClearScrollConverters::responseToDataObject,
                handler,
                client()::clearScroll
        );
    }

    @Override
    public void bulk(BulkRequest request, Handler<AsyncResult<BulkResponse>> handler) {
        executeRequestBlocking(
                request,
                BulkConverters::requestToES,
                BulkConverters::responseToDataObject,
                handler,
                client()::bulk
        );
    }
}
