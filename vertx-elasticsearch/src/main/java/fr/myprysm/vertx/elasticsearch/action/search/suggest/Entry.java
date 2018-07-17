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
public abstract class Entry {

    private String text;
    private int offset;
    private int length;


    /**
     * Get the suggestion entry options.
     *
     * @return the suggestion entry options
     */
    abstract List<? extends Option> getOptions();

    public Entry(Entry other) {
        text = other.text;
        offset = other.offset;
        length = other.length;
    }

    /**
     * Build a new <code>Entry</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public Entry(JsonObject json) {
        EntryConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>Entry</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        EntryConverter.toJson(this, json);
        return json;
    }
}
