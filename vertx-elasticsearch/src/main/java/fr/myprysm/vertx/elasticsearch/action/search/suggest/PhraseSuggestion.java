package fr.myprysm.vertx.elasticsearch.action.search.suggest;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class PhraseSuggestion extends Suggestion {
    public static final String SUGGESTION_NAME = "phrase";

    private List<PhraseEntry> entries;


    public PhraseSuggestion() {
        super(SUGGESTION_NAME, null);
    }

    public PhraseSuggestion(String name, List<PhraseEntry> entries) {
        super(SUGGESTION_NAME, name);
        this.entries = entries;
    }

    /**
     * Build a new <code>PhraseSuggestion</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public PhraseSuggestion(JsonObject json) {
        super(json);
        PhraseSuggestionConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>PhraseSuggestion</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        PhraseSuggestionConverter.toJson(this, json);
        return json;
    }
}
