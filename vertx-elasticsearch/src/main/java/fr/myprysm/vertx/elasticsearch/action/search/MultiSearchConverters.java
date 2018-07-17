package fr.myprysm.vertx.elasticsearch.action.search;

import fr.myprysm.vertx.elasticsearch.action.support.Failure;

import java.util.ArrayList;

public interface MultiSearchConverters {

    static MultiSearchRequest requestToDataObject(org.elasticsearch.action.search.MultiSearchRequest esRequest) {
        ArrayList<SearchRequest> requests = new ArrayList<>(esRequest.requests().size());
        for (org.elasticsearch.action.search.SearchRequest searchRequest : esRequest.requests()) {
            requests.add(SearchConverters.requestToDataObject(searchRequest));
        }

        return new MultiSearchRequest(esRequest.maxConcurrentSearchRequests() > 0 ? esRequest.maxConcurrentSearchRequests() : null, requests);
    }

    static org.elasticsearch.action.search.MultiSearchRequest requestToES(MultiSearchRequest request) {
        org.elasticsearch.action.search.MultiSearchRequest esRequest = new org.elasticsearch.action.search.MultiSearchRequest();

        for (SearchRequest searchRequest : request.getRequests()) {
            esRequest.add(SearchConverters.requestToES(searchRequest));
        }

        if (request.getMaxConcurrentSearchRequests() != null) {
            esRequest.maxConcurrentSearchRequests(request.getMaxConcurrentSearchRequests());
        }

        return esRequest;
    }

    static MultiSearchResponse responseToDataObject(org.elasticsearch.action.search.MultiSearchResponse esResponse) {
        ArrayList<MultiSearchResponseItem> items = new ArrayList<>(esResponse.getResponses().length);
        for (org.elasticsearch.action.search.MultiSearchResponse.Item item : esResponse) {
            if (item.isFailure()) {
                items.add(new MultiSearchResponseItem(new Failure(item.getFailureMessage()), null));
            } else {
                items.add(new MultiSearchResponseItem(null, SearchConverters.responseToDataObject(item.getResponse())));
            }
        }

        return new MultiSearchResponse(items);
    }
}
