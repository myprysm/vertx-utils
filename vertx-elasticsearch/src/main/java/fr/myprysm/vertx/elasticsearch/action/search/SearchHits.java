package fr.myprysm.vertx.elasticsearch.action.search;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Iterator;
import java.util.List;

import static java.util.Collections.emptyIterator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class SearchHits implements Iterable<SearchHit> {

    private long totalHits;
    private float maxScore;
    private List<SearchHit> hits;

    public SearchHits(SearchHits other) {
        totalHits = other.totalHits;
        maxScore = other.maxScore;
        hits = other.hits;
    }

    /**
     * Build a new <code>SearchHits</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public SearchHits(JsonObject json) {
        SearchHitsConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>SearchHits</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        SearchHitsConverter.toJson(this, json);
        return json;
    }

    @Override
    public Iterator<SearchHit> iterator() {
        return hits == null ? emptyIterator() : hits.iterator();
    }

    public SearchHit getAt(int index) {
        if (hits == null || index < 0 || index >= hits.size()) {
            throw new IndexOutOfBoundsException("Cannot get SearchHit at index " + index);
        }

        return hits.get(index);
    }
}
