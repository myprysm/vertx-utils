package fr.myprysm.vertx.elasticsearch.action.get;

import fr.myprysm.vertx.elasticsearch.action.support.SupportConverters;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetRequest.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * MultiGet request converters.
 */
public interface MultiGetConverters {

    /**
     * Transforms a DataObject request into an Elasticsearch request.
     *
     * @param request the request
     * @return the Elasticsearch request
     */
    static org.elasticsearch.action.get.MultiGetRequest requestToES(MultiGetRequest request) {
        org.elasticsearch.action.get.MultiGetRequest esRequest = new org.elasticsearch.action.get.MultiGetRequest();
        if (request.getItems() != null) {
            for (GetRequestItem item : request.getItems()) {
                esRequest.add(itemToRequest(item));
            }
        }

        return esRequest
                .preference(request.getPreference())
                .realtime(request.isRealTime())
                .refresh(request.isRefresh());
    }

    /**
     * Transforms a DataObject item into an Elasticsearch item.
     *
     * @param item the item
     * @return the Elasticsearch item
     */
    static Item itemToRequest(GetRequestItem item) {
        Item esItem = new Item(item.getIndex(), item.getType(), item.getId());

        if (item.getFetchSourceContext() != null) {
            esItem.fetchSourceContext(SupportConverters.fetchSourceContextToES(item.getFetchSourceContext()));
        }

        if (item.getStoredFields() != null) {
            esItem.storedFields(item.getStoredFields().toArray(new String[]{}));
        }
        return esItem
                .routing(item.getRouting())
                .parent(item.getParent())
                .version(item.getVersion())
                .versionType(item.getVersionType());

    }

    /**
     * Transforms an Elasticsearch response into a DataObject response.
     *
     * @param esResponse the Elasticsearch response
     * @return the response
     */
    static MultiGetResponse responseToDataObject(org.elasticsearch.action.get.MultiGetResponse esResponse) {
        List<GetResponse> responses = new ArrayList<>();
        List<GetFailure> failures = new ArrayList<>();

        for (MultiGetItemResponse multiGetItemResponse : esResponse) {
            if (multiGetItemResponse.isFailed()) {
                failures.add(failureToDataObject(multiGetItemResponse.getFailure()));
            } else {
                responses.add(GetConverters.responseToDataObject(multiGetItemResponse.getResponse()));
            }
        }

        return new MultiGetResponse(responses, failures);
    }

    /**
     * Transforms an Elasticsearch failure into a DataObject failure.
     *
     * @param esFailure the Elasticsearch failure
     * @return the failure
     */
    static GetFailure failureToDataObject(org.elasticsearch.action.get.MultiGetResponse.Failure esFailure) {
        return new GetFailure(esFailure.getIndex(), esFailure.getType(), esFailure.getId(), esFailure.getMessage());
    }


}
