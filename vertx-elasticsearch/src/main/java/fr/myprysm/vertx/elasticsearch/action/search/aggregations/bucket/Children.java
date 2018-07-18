package fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@DataObject
public class Children extends SingleBucketAggregation {

    public Children(SingleBucketAggregation other) {
        super(other);
    }

    /**
     * Build a new <code>Children</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public Children(JsonObject json) {
        super(json);
    }
}
