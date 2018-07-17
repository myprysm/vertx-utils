package fr.myprysm.vertx.elasticsearch.action.search.aggregations;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class Aggregation {
    private String type;
    private String name;
    private JsonObject metaData;
    private JsonObject data;

    public Aggregation(Aggregation other) {
        type = other.type;
        name = other.name;
        metaData = other.metaData;
        data = other.data;
    }

    /**
     * Build a new <code>Aggregation</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public Aggregation(JsonObject json) {
        AggregationConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>Aggregation</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        AggregationConverter.toJson(this, json);
        return json;
    }
}
