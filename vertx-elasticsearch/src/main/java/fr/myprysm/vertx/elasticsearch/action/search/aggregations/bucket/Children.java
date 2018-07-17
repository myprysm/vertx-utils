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
public class Children extends Aggregation {

    private long docCount;
    private Map<String, Aggregation> aggregations;

    public Children(Children other) {
        super(other);
        docCount = other.docCount;
        aggregations = other.aggregations;
    }

    @SuppressWarnings("unchecked")
    public <T extends Aggregation> T getAggregationByName(String name) {
        if (aggregations == null) {
            return null;
        }

        return (T) aggregations.get(name);

    }

    /**
     * Build a new <code>Children</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public Children(JsonObject json) {
        super(json);
        ChildrenConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>Children</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        ChildrenConverter.toJson(this, json);
        return json;
    }
}
