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
public class TermEntry extends Entry {

    private List<TermOption> options;

    @Override
    public TermEntry setText(String text) {
        return (TermEntry) super.setText(text);
    }

    @Override
    public TermEntry setOffset(int offset) {
        return (TermEntry) super.setOffset(offset);
    }

    @Override
    public TermEntry setLength(int length) {
        return (TermEntry) super.setLength(length);
    }

    public TermEntry(TermEntry other) {
        super(other);
        options = other.options;
    }

    /**
     * Build a new <code>TermEntry</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public TermEntry(JsonObject json) {
        super(json);
        TermEntryConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>TermEntry</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        TermEntryConverter.toJson(this, json);
        return json;
    }
}
