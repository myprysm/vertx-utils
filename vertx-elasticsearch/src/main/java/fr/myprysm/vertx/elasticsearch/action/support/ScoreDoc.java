package fr.myprysm.vertx.elasticsearch.action.support;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class ScoreDoc {

    /**
     * The score of this document for the query.
     */
    private float score;

    /**
     * A hit document's number.
     *
     * @see IndexSearcher#doc(int)
     */
    private int doc;

    /**
     * Only set by {@link TopDocs#merge}
     */
    private int shardIndex;

    public ScoreDoc(ScoreDoc other) {
        score = other.score;
        doc = other.doc;
        shardIndex = other.shardIndex;
    }

    /**
     * Build a new <code>ScoreDoc</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public ScoreDoc(JsonObject json) {
        ScoreDocConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>ScoreDoc</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        ScoreDocConverter.toJson(this, json);
        return json;
    }
}
