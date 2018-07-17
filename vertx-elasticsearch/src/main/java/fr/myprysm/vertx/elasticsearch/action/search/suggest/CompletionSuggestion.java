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
public class CompletionSuggestion extends Suggestion {

    public static final String SUGGESTION_NAME = "completion";
    private List<CompletionEntry> entries;


    public CompletionSuggestion() {
        super(SUGGESTION_NAME, null);
    }

    public CompletionSuggestion(String name, List<CompletionEntry> entries) {
        super(SUGGESTION_NAME, name);
        this.entries = entries;
    }

    /**
     * Build a new <code>CompletionSuggestion</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public CompletionSuggestion(JsonObject json) {
        super(json);
        CompletionSuggestionConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>CompletionSuggestion</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        CompletionSuggestionConverter.toJson(this, json);
        return json;
    }
}
