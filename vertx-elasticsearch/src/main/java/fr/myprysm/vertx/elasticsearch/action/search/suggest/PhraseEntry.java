package fr.myprysm.vertx.elasticsearch.action.search.suggest;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class PhraseEntry extends Entry {

    private List<PhraseOption> options;

    @Override
    public PhraseEntry setText(String text) {
        return (PhraseEntry) super.setText(text);
    }

    @Override
    public PhraseEntry setOffset(int offset) {
        return (PhraseEntry) super.setOffset(offset);
    }

    @Override
    public PhraseEntry setLength(int length) {
        return (PhraseEntry) super.setLength(length);
    }

    public PhraseEntry(PhraseEntry other) {
        super(other);
        options = other.options;
    }

    /**
     * Build a new <code>PhraseEntry</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public PhraseEntry(JsonObject json) {
        super(json);
        PhraseEntryConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>PhraseEntry</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        PhraseEntryConverter.toJson(this, json);
        return json;
    }
}
