package fr.myprysm.vertx.elasticsearch.action.search;

public interface ClearScrollConverters {

    static org.elasticsearch.action.search.ClearScrollRequest requestToES(ClearScrollRequest request) {
        org.elasticsearch.action.search.ClearScrollRequest esRequest = new org.elasticsearch.action.search.ClearScrollRequest();
        esRequest.scrollIds(request.getScrollIds());
        return esRequest;
    }

    static ClearScrollResponse responseToDataObject(org.elasticsearch.action.search.ClearScrollResponse esResponse) {
        return new ClearScrollResponse(
                esResponse.isSucceeded(),
                esResponse.getNumFreed(),
                esResponse.status()
        );
    }
}
