package fr.myprysm.vertx.elasticsearch.action.search;

import fr.myprysm.vertx.elasticsearch.action.search.aggregations.AggregationConverters;
import fr.myprysm.vertx.elasticsearch.action.search.suggest.SuggestConverters;
import fr.myprysm.vertx.elasticsearch.action.support.Explanation;
import fr.myprysm.vertx.elasticsearch.action.support.SupportConverters;
import fr.myprysm.vertx.elasticsearch.converter.CommonConverters;
import fr.myprysm.vertx.elasticsearch.converter.ConverterException;
import fr.myprysm.vertx.elasticsearch.converter.ConverterUtils;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.profile.ProfileShardResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface SearchConverters {

    static org.elasticsearch.action.search.SearchRequest requestToES(SearchRequest request) {
        org.elasticsearch.action.search.SearchRequest esRequest = new org.elasticsearch.action.search.SearchRequest();

        esRequest.setBatchedReduceSize(request.getBatchedReduceSize());
        esRequest.setMaxConcurrentShardRequests(request.getMaxConcurrentShardRequests());
        esRequest.setPreFilterShardSize(request.getPreFilterShardSize());

        if (request.getSearchType() != null) {
            esRequest.searchType(request.getSearchType());
        }

        if (request.getKeepAlive() != null) {
            esRequest.scroll(TimeValue.timeValueMillis(request.getKeepAlive()));
        }

        try {
            SearchSourceBuilder parser = SearchSourceBuilder.fromXContent(CommonConverters.toXContent(request.getSource()));
            esRequest.source(parser);
        } catch (IOException exc) {
            throw new ConverterException("Unable to parse generated Json from Vert.X?!!", exc);
        }

        if (request.getAllowPartialSearchResults() != null) {
            esRequest.allowPartialSearchResults(request.getAllowPartialSearchResults());
        }

        return esRequest
                .indices(request.getIndices().toArray(new String[]{}))
                .types(request.getTypes().toArray(new String[]{}))
                .routing(request.getRouting())
                .preference(request.getPreference())
                .requestCache(request.getRequestCache());
    }

    static SearchRequest requestToDataObject(org.elasticsearch.action.search.SearchRequest esRequest) {
        SearchRequest request = new SearchRequest();
        if (esRequest.scroll() != null) {
            request.setKeepAlive(esRequest.scroll().keepAlive().millis());
        }

        if (esRequest.source() != null) {
            request.setSource(CommonConverters.fromXContent(esRequest.source()));
        }

        return request
                .setIndices(Arrays.asList(esRequest.indices()))
                .setTypes(Arrays.asList(esRequest.types()))
                .setPreference(esRequest.preference())
                .setRouting(esRequest.routing())
                .setSearchType(esRequest.searchType())
                .setAllowPartialSearchResults(esRequest.allowPartialSearchResults())
                .setBatchedReduceSize(esRequest.getBatchedReduceSize())
                .setPreFilterShardSize(esRequest.getPreFilterShardSize())
                .setMaxConcurrentShardRequests(esRequest.getMaxConcurrentShardRequests())
                .setRequestCache(esRequest.requestCache());
    }

    static SearchResponse responseToDataObject(org.elasticsearch.action.search.SearchResponse esResponse) {
        SearchResponse response = new SearchResponse();

        if (esResponse.getHits() != null) {
            response.setHits(searchHitsToDataObject(esResponse.getHits()));
        }

        if (esResponse.getSuggest() != null) {
            response.setSuggest(SuggestConverters.suggestToDataObject(esResponse.getSuggest()));
        }

        if (esResponse.getShardFailures().length > 0) {
            response.setShardFailures(ConverterUtils.convert(
                    Arrays.asList(esResponse.getShardFailures()),
                    SupportConverters::shardSearchFailureToDataObject)
            );
        }

        if (esResponse.getClusters() != null) {
            response.setClusters(clustersToDataObject(esResponse.getClusters()));
        }

        if (!esResponse.getProfileResults().isEmpty()) {
            response.setProfileResults(ConverterUtils.convert(esResponse.getProfileResults(), SearchConverters::profileShardResultToJson));
        }

        if (esResponse.getAggregations() != null) {
            response.setAggregations(ConverterUtils.convert(esResponse.getAggregations().asMap(), AggregationConverters::aggregationToDataObject));
        }

        return response
                .setTookInMillis(esResponse.getTook().millis())
                .setScrollId(esResponse.getScrollId())
                .setNumReducePhases(esResponse.getNumReducePhases())
                .setTotalShards(esResponse.getTotalShards())
                .setSkippedShards(esResponse.getSkippedShards())
                .setFailedShards(esResponse.getFailedShards())
                .setSuccessfulShards(esResponse.getSuccessfulShards())
                .setTerminatedEarly(esResponse.isTerminatedEarly())
                .setTimedOut(esResponse.isTimedOut());
    }


    static Clusters clustersToDataObject(org.elasticsearch.action.search.SearchResponse.Clusters clusters) {
        return new Clusters(
                clusters.getTotal(),
                clusters.getSuccessful(),
                clusters.getSkipped()
        );
    }

    static SearchHits searchHitsToDataObject(org.elasticsearch.search.SearchHits esHits) {
        ArrayList<SearchHit> hitsList = new ArrayList<>();

        for (org.elasticsearch.search.SearchHit esHit : esHits) {
            hitsList.add(searchHitToDataObject(esHit));
        }

        return new SearchHits(
                esHits.getTotalHits(),
                esHits.getMaxScore(),
                hitsList
        );
    }

    static SearchHit searchHitToDataObject(org.elasticsearch.search.SearchHit esHit) {
        SearchHit hit = new SearchHit()
                .setId(esHit.getId())
                .setType(esHit.getType())
                .setIndex(esHit.getIndex())
                .setVersion(esHit.getVersion())
                .setScore(esHit.getScore())
                .setClusterAlias(esHit.getClusterAlias());

        if (esHit.hasSource()) {
            hit.setSource(new JsonObject(esHit.getSourceAsMap()));
        }

        if (!esHit.getFields().isEmpty()) {
            hit.setFields(ConverterUtils.convert(esHit.getFields(), SupportConverters::documentFieldToDataObject));
        }

        if (!esHit.getHighlightFields().isEmpty()) {
            hit.setHighlightFields(ConverterUtils.convert(esHit.getHighlightFields(), SearchConverters::highlightFieldToList));
        }

        if (esHit.getInnerHits() != null && !esHit.getInnerHits().isEmpty()) {
            hit.setInnerHits(ConverterUtils.convert(esHit.getInnerHits(), SearchConverters::searchHitsToDataObject));
        }

        if (esHit.getNestedIdentity() != null) {
            hit.setNestedIdentity(nestedIdentityToDataObject(esHit.getNestedIdentity()));
        }

        if (esHit.getExplanation() != null) {
            hit.setExplanation(explanationToDataObject(esHit.getExplanation()));
        }

        if (esHit.getMatchedQueries() != null) {
            hit.setMatchedQueries(Arrays.asList(esHit.getMatchedQueries()));
        }

        if (esHit.getSortValues() != null) {
            hit.setSortValues(new JsonArray(Arrays.asList(esHit.getSortValues())));
        }

        return hit;
    }

    static List<String> highlightFieldToList(HighlightField field) {
        if (field.fragments() == null) {
            return null;
        }
        List<String> list = new ArrayList<>();
        for (Text text : field.fragments()) {
            list.add(text.string());
        }
        return list;
    }

    static Explanation explanationToDataObject(org.apache.lucene.search.Explanation explanation) {
        return new Explanation(
                explanation.isMatch(),
                explanation.getValue(),
                explanation.getDescription(),
                explanation.getDetails() == null ? null
                        : ConverterUtils.convert(Arrays.asList(explanation.getDetails()), SearchConverters::explanationToDataObject)
        );
    }

    static NestedIdentity nestedIdentityToDataObject(org.elasticsearch.search.SearchHit.NestedIdentity esIdentity) {
        return new NestedIdentity(
                esIdentity.getField().string(),
                esIdentity.getOffset(),
                esIdentity.getChild() == null ? null : nestedIdentityToDataObject(esIdentity.getChild())
        );
    }

    static JsonObject profileShardResultToJson(ProfileShardResult result) {
        JsonObject profile = null;
        if (result.getAggregationProfileResults() != null) {
            profile = new JsonObject();

            profile.put("aggregation", CommonConverters.fromXContent(result.getAggregationProfileResults()));
        }

        if (result.getQueryProfileResults().size() > 0) {
            if (profile == null) {
                profile = new JsonObject();
            }

            profile.put("searches", ConverterUtils.convert(result.getQueryProfileResults(), CommonConverters::fromXContent));
        }

        return profile;
    }
}
