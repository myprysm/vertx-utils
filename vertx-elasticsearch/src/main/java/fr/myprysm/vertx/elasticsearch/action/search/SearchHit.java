package fr.myprysm.vertx.elasticsearch.action.search;


import fr.myprysm.vertx.elasticsearch.action.support.DocumentField;
import fr.myprysm.vertx.elasticsearch.action.support.Explanation;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonArray;
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
@DataObject(generateConverter = true)
public class SearchHit {

    private String id;
    private String type;
    private String index;
    private long version = -1;
    private JsonObject source;
    private Map<String, DocumentField> fields;
    private Map<String, List<String>> highlightFields;

    private List<String> matchedQueries;
    private Map<String, SearchHits> innerHits;
    private JsonArray sortValues;

    private NestedIdentity nestedIdentity;
    private Explanation explanation;

    private float score;
    private String clusterAlias;

    public SearchHit(SearchHit other) {
        id = other.id;
        type = other.type;
        index = other.index;
        version = other.version;
        source = other.source;
        fields = other.fields;
        highlightFields = other.highlightFields;
        matchedQueries = other.matchedQueries;
        innerHits = other.innerHits;
        sortValues = other.sortValues;
        nestedIdentity = other.nestedIdentity;
        explanation = other.explanation;
        score = other.score;
        clusterAlias = other.clusterAlias;
    }

    /**
     * Build a new <code>SearchHit</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public SearchHit(JsonObject json) {
        SearchHitConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>SearchHit</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        SearchHitConverter.toJson(this, json);
        return json;
    }
}
