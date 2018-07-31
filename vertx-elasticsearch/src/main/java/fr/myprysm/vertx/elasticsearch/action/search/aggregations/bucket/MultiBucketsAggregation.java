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
public class MultiBucketsAggregation extends Aggregation {

    Map<String, Bucket> buckets;

    public Bucket getBucketByKey(String term) {
        if (buckets == null) {
            return null;
        }

        return buckets.get(term);

    }

    /**
     * Build a new <code>MultiBucketsAggregation</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param other the <code>JsonObject</code>
     */
    public MultiBucketsAggregation(MultiBucketsAggregation other) {
        super(other);
        buckets = other.buckets;
    }

    /**
     * Build a new <code>MultiBucketsAggregation</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public MultiBucketsAggregation(JsonObject json) {
        super(json);
        MultiBucketsAggregationConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>MultiBucketsAggregation</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        MultiBucketsAggregationConverter.toJson(this, json);
        return json;
    }
}
