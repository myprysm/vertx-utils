package fr.myprysm.vertx.elasticsearch.action.bulk;

import fr.myprysm.vertx.elasticsearch.action.delete.DeleteConverters;
import fr.myprysm.vertx.elasticsearch.action.delete.DeleteRequest;
import fr.myprysm.vertx.elasticsearch.action.index.IndexConverters;
import fr.myprysm.vertx.elasticsearch.action.index.IndexRequest;
import fr.myprysm.vertx.elasticsearch.action.support.DocWriteRequest;
import fr.myprysm.vertx.elasticsearch.action.update.UpdateConverters;
import fr.myprysm.vertx.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.common.unit.TimeValue;

import java.util.ArrayList;

public interface BulkConverters {

    static org.elasticsearch.action.bulk.BulkRequest requestToES(BulkRequest request) {
        org.elasticsearch.action.bulk.BulkRequest esRequest = new org.elasticsearch.action.bulk.BulkRequest();

        if (request.getRefreshPolicy() != null) {
            esRequest.setRefreshPolicy(request.getRefreshPolicy());
        }

        if (request.getWaitForActiveShards() != null) {
            esRequest.waitForActiveShards(request.getWaitForActiveShards());
        }

        if (request.getRequests().size() > 0) {
            for (DocWriteRequest docWriteRequest : request.getRequests()) {
                docWriteRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.NONE);
                if (docWriteRequest instanceof IndexRequest) {
                    esRequest.add(IndexConverters.requestToES((IndexRequest) docWriteRequest));
                } else {
                    if (docWriteRequest instanceof UpdateRequest) {
                        esRequest.add(UpdateConverters.requestToES((UpdateRequest) docWriteRequest));
                    } else if (docWriteRequest instanceof DeleteRequest) {
                        esRequest.add(DeleteConverters.requestToES((DeleteRequest) docWriteRequest));
                    }
                }
            }
        }

        return esRequest.timeout(TimeValue.timeValueMillis(request.getTimeout()));
    }

    static BulkResponse responseToDataObject(org.elasticsearch.action.bulk.BulkResponse esResponse) {
        boolean hasErrors = false;
        ArrayList<BulkResponseItem> items = new ArrayList<>();

        for (BulkItemResponse esItem : esResponse.getItems()) {
            BulkResponseItem item = bulkItemResponseToDataObject(esItem);
            hasErrors |= item.isFailed();
            items.add(item);
        }

        return new BulkResponse(items, hasErrors, esResponse.getTook().millis(), esResponse.status());
    }

    static BulkResponseItem bulkItemResponseToDataObject(BulkItemResponse esItem) {
        return new BulkResponseItem(
                esItem.getOpType(),
                esItem.status(),
                esItem.getIndex(),
                esItem.getType(),
                esItem.getId(),
                esItem.getVersion(),
                esItem.isFailed(),
                esItem.getFailureMessage()
        );
    }
}
