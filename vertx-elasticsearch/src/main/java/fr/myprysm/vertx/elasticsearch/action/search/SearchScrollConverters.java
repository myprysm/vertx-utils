package fr.myprysm.vertx.elasticsearch.action.search;

import org.elasticsearch.common.unit.TimeValue;

public interface SearchScrollConverters {

    static org.elasticsearch.action.search.SearchScrollRequest requestToES(SearchScrollRequest request) {
        org.elasticsearch.action.search.SearchScrollRequest esRequest = new org.elasticsearch.action.search.SearchScrollRequest();

        if (request.getKeepAlive() != null) {
            esRequest.scroll(TimeValue.timeValueMillis(request.getKeepAlive()));
        }

        return esRequest.scrollId(request.getScrollId());
    }
}
