package fr.myprysm.vertx.elasticsearch.action.search;

import fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation;
import fr.myprysm.vertx.elasticsearch.action.search.suggest.Suggestion;
import fr.myprysm.vertx.elasticsearch.action.support.ShardFailure;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject
public class SearchResponse {

    private int totalShards;
    private int successfulShards;
    private int skippedShards;
    private int failedShards;
    private List<ShardFailure> shardFailures;
    private Clusters clusters;
    private long tookInMillis;
    private String scrollId;
    private SearchHits hits;
    private Map<String, Aggregation> aggregations;
    private Map<String, Suggestion> suggest;
    private Map<String, JsonObject> profileResults;
    private boolean timedOut;
    private Boolean terminatedEarly;
    private int numReducePhases;

    public SearchResponse(SearchResponse other) {
        totalShards = other.totalShards;
        successfulShards = other.successfulShards;
        skippedShards = other.skippedShards;
        shardFailures = other.shardFailures;
        clusters = other.clusters;
        tookInMillis = other.tookInMillis;
        scrollId = other.scrollId;
        hits = other.hits;
        aggregations = other.aggregations;
        suggest = other.suggest;
        profileResults = other.profileResults;
        timedOut = other.timedOut;
        terminatedEarly = other.terminatedEarly;
        numReducePhases = other.numReducePhases;
    }

    @SuppressWarnings("unchecked")
    public <T extends Aggregation> T getAggregationByName(String name) {
        if (aggregations == null) {
            return null;
        }

        return (T) aggregations.get(name);

    }

    @SuppressWarnings("unchecked")
    public <T extends Suggestion> T getSuggestionByName(String name) {
        if (suggest == null) {
            return null;
        }

        return (T) suggest.get(name);
    }

    /**
     * Build a new <code>SearchResponse</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public SearchResponse(JsonObject json) {
        SearchResponseConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>SearchResponse</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        SearchResponseConverter.toJson(this, json);
        return json;
    }
}
