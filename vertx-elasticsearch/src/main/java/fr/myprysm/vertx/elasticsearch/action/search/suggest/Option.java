package fr.myprysm.vertx.elasticsearch.action.search.suggest;

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
public abstract class Option {

    private String text;
    private String highlighted;
    private float score;
    private Boolean collateMatch;

    public Option(Option other) {
        text = other.text;
        highlighted = other.highlighted;
        score = other.score;
        collateMatch = other.collateMatch;
    }

    /**
     * Build a new <code>Option</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public Option(JsonObject json) {
        OptionConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>Option</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        OptionConverter.toJson(this, json);
        return json;
    }
}
