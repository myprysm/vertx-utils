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
import fr.myprysm.vertx.elasticsearch.converter.CommonConverters;
import fr.myprysm.vertx.elasticsearch.converter.Converter;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Elasticsearch client working with the Elasticsearch Async API.
 */
class NativeAsyncElasticsearchRestClientImpl extends BaseElasticsearchRestClient {

    /**
     * Build a new client with the options and the provided name.
     *
     * @param vertx      the current vertx instance
     * @param options    the options
     * @param clientName the client name
     */
    NativeAsyncElasticsearchRestClientImpl(Vertx vertx, ElasticsearchClientOptions options, String clientName) {
        super(vertx, options, clientName);
    }

    @Override
    Pair<IndicesClient, ClusterClient> getClients() {
        return Pair.of(
                new NativeAsyncIndicesRestClientImpl(vertx(), client().indices(), name()),
                new NativeAsyncClusterRestClientImpl(vertx(), client().cluster(), name())
        );
    }


    @Override
    public void get(GetRequest request, Handler<AsyncResult<GetResponse>> handler) {
        executeRequestAsync(
                request,
                GetConverters::requestToES,
                GetConverters::responseToDataObject,
                handler,
                client()::getAsync
        );
    }

    @Override
    public void exists(GetRequest request, Handler<AsyncResult<Boolean>> handler) {
        executeRequestAsync(
                request,
                GetConverters::requestToES,
                (Converter<Boolean, Boolean>) CommonConverters::objectToBoolean,
                handler,
                client()::existsAsync
        );
    }

    @Override
    public void multiGet(MultiGetRequest request, Handler<AsyncResult<MultiGetResponse>> handler) {
        executeRequestAsync(
                request,
                MultiGetConverters::requestToES,
                MultiGetConverters::responseToDataObject,
                handler,
                client()::multiGetAsync
        );
    }

    @Override
    public void index(IndexRequest request, Handler<AsyncResult<IndexResponse>> handler) {
        executeRequestAsync(
                request,
                IndexConverters::requestToES,
                IndexConverters::responseToDataObject,
                handler,
                client()::indexAsync
        );
    }

    @Override
    public void update(UpdateRequest request, Handler<AsyncResult<UpdateResponse>> handler) {
        executeRequestAsync(
                request,
                UpdateConverters::requestToES,
                UpdateConverters::responseToDataObject,
                handler,
                client()::updateAsync
        );
    }

    @Override
    public void delete(DeleteRequest request, Handler<AsyncResult<DeleteResponse>> handler) {
        executeRequestAsync(
                request,
                DeleteConverters::requestToES,
                DeleteConverters::responseToDataObject,
                handler,
                client()::deleteAsync
        );
    }

    @Override
    public void search(SearchRequest request, Handler<AsyncResult<SearchResponse>> handler) {
        executeRequestAsync(
                request,
                SearchConverters::requestToES,
                SearchConverters::responseToDataObject,
                handler,
                client()::searchAsync
        );
    }

    @Override
    public void multiSearch(MultiSearchRequest request, Handler<AsyncResult<MultiSearchResponse>> handler) {
        executeRequestAsync(
                request,
                MultiSearchConverters::requestToES,
                MultiSearchConverters::responseToDataObject,
                handler,
                client()::multiSearchAsync
        );
    }

    @Override
    public void searchScroll(SearchScrollRequest request, Handler<AsyncResult<SearchResponse>> handler) {
        executeRequestAsync(
                request,
                SearchScrollConverters::requestToES,
                SearchConverters::responseToDataObject,
                handler,
                client()::searchScrollAsync
        );
    }

    @Override
    public void clearScroll(ClearScrollRequest request, Handler<AsyncResult<ClearScrollResponse>> handler) {
        executeRequestAsync(
                request,
                ClearScrollConverters::requestToES,
                ClearScrollConverters::responseToDataObject,
                handler,
                client()::clearScrollAsync
        );
    }

    @Override
    public void bulk(BulkRequest request, Handler<AsyncResult<BulkResponse>> handler) {
        executeRequestAsync(
                request,
                BulkConverters::requestToES,
                BulkConverters::responseToDataObject,
                handler,
                client()::bulkAsync
        );
    }

}
