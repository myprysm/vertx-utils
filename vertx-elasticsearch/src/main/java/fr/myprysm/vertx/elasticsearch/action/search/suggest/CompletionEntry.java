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
public class CompletionEntry extends Entry {

    private List<CompletionOption> options;

    @Override
    public CompletionEntry setText(String text) {
        return (CompletionEntry) super.setText(text);
    }

    @Override
    public CompletionEntry setOffset(int offset) {
        return (CompletionEntry) super.setOffset(offset);
    }

    @Override
    public CompletionEntry setLength(int length) {
        return (CompletionEntry) super.setLength(length);
    }

    public CompletionEntry(CompletionEntry other) {
        super(other);
        options = other.options;
    }

    /**
     * Build a new <code>CompletionEntry</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public CompletionEntry(JsonObject json) {
        super(json);
        CompletionEntryConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>CompletionEntry</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        CompletionEntryConverter.toJson(this, json);
        return json;
    }
}
