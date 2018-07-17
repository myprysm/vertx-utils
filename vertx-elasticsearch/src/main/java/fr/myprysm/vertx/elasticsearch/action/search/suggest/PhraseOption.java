package fr.myprysm.vertx.elasticsearch.action.search.suggest;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class PhraseOption extends Option {

    public PhraseOption(PhraseOption other) {
        super(other);
    }

    /**
     * Build a new <code>PhraseOption</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public PhraseOption(JsonObject json) {
        super(json);
        PhraseOptionConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>PhraseOption</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        PhraseOptionConverter.toJson(this, json);
        return json;
    }

    @Override
    public PhraseOption setText(String text) {
        return (PhraseOption) super.setText(text);
    }

    @Override
    public PhraseOption setHighlighted(String highlighted) {
        return (PhraseOption) super.setHighlighted(highlighted);
    }

    @Override
    public PhraseOption setScore(float score) {
        return (PhraseOption) super.setScore(score);
    }

    @Override
    public PhraseOption setCollateMatch(Boolean collateMatch) {
        return (PhraseOption) super.setCollateMatch(collateMatch);
    }
}
