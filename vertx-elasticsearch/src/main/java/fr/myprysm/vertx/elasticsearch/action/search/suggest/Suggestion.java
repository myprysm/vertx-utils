package fr.myprysm.vertx.elasticsearch.action.search.suggest;

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
public abstract class Suggestion {

    private String type;
    private String name;

    public Suggestion(Suggestion other) {
        type = other.type;
        name = other.name;
    }

    /**
     * Get the suggestion entries.
     *
     * @return the suggestion entries
     */
    abstract List<? extends Entry> getEntries();

    @SuppressWarnings("unchecked")
    <T extends Entry> T getEntry(int index) {
        List<? extends Entry> entries = getEntries();
        if (entries == null || index < 0 || index >= entries.size()) {
            throw new ArrayIndexOutOfBoundsException("Cannot get suggestion entry at index " + index);
        }

        return (T) entries.get(index);
    }

    /**
     * Build a new <code>Suggestion</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public Suggestion(JsonObject json) {
        SuggestionConverter.fromJson(json, this);
    }


    /**
     * Transforms the <code>Suggestion</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        SuggestionConverter.toJson(this, json);
        return json;
    }
}


