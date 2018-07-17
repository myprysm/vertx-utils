package fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class RangeBucket extends Bucket {

    private String key;
    private String toAsString;
    private String fromAsString;


    /**
     * Build a new <code>RangeBucket</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public RangeBucket(JsonObject json) {
        super(json);
        RangeBucketConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>RangeBucket</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        RangeBucketConverter.toJson(this, json);
        return json;
    }
}
