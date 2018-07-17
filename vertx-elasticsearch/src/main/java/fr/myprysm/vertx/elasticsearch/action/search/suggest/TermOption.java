package fr.myprysm.vertx.elasticsearch.action.search.suggest;

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
public class TermOption extends Option {

    private int freq;

    public TermOption(TermOption other) {
        super(other);
        freq = other.freq;
    }

    /**
     * Build a new <code>TermOption</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public TermOption(JsonObject json) {
        super(json);
        TermOptionConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>TermOption</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        TermOptionConverter.toJson(this, json);
        return json;
    }

    @Override
    public TermOption setText(String text) {
        return (TermOption) super.setText(text);
    }

    @Override
    public TermOption setHighlighted(String highlighted) {
        return (TermOption) super.setHighlighted(highlighted);
    }

    @Override
    public TermOption setScore(float score) {
        return (TermOption) super.setScore(score);
    }

    @Override
    public TermOption setCollateMatch(Boolean collateMatch) {
        return (TermOption) super.setCollateMatch(collateMatch);
    }
}
