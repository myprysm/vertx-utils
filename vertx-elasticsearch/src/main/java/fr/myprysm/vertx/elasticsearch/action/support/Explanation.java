package fr.myprysm.vertx.elasticsearch.action.support;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class Explanation {

    private boolean match;
    private float value;
    private String description;
    private List<Explanation> details;

    public Explanation(Explanation other) {
        match = other.match;
        value = other.value;
        description = other.description;
        details = other.details;
    }

    /**
     * Build a new <code>Explanation</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public Explanation(JsonObject json) {
        ExplanationConverter.fromJson(json, this);
    }


    /**
     * Transforms the <code>Explanation</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        ExplanationConverter.toJson(this, json);
        return json;
    }
}
