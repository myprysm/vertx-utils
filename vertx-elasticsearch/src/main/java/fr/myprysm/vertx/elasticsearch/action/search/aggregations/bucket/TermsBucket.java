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
public class TermsBucket extends Bucket {


    private Number keyAsNumber;
    private long docCountError;

    public TermsBucket(TermsBucket other) {
        super(other);
        keyAsNumber = other.keyAsNumber;
        docCountError = other.docCountError;
    }

    /**
     * Build a new <code>TermsBucket</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public TermsBucket(JsonObject json) {
        super(json);
        TermsBucketConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>TermsBucket</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        TermsBucketConverter.toJson(this, json);
        return json;
    }
}
