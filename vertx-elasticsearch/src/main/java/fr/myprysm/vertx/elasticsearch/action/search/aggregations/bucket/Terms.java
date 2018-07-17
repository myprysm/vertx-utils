package fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket;

import fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class Terms extends Aggregation {

    private long docCountError;
    private long sumOfOtherDocCounts;
    private Map<String, TermsBucket> buckets;

    @SuppressWarnings("unchecked")
    public <T extends TermsBucket> T getBucketByKey(String term) {
        if (buckets == null) {
            return null;
        }

        return (T) buckets.get(term);

    }


    /**
     * Build a new <code>Terms</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public Terms(JsonObject json) {
        super(json);
        TermsConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>Terms</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        TermsConverter.toJson(this, json);
        return json;
    }
}
