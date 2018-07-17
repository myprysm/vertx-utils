package fr.myprysm.vertx.elasticsearch.action.search.suggest;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class TermSuggestion extends Suggestion {

    public static final String SUGGESTION_NAME = "term";

    private List<TermEntry> entries;

    public TermSuggestion() {
        super(SUGGESTION_NAME, null);
    }

    public TermSuggestion(String name, List<TermEntry> entries) {
        super(SUGGESTION_NAME, name);
        this.entries = entries;
    }

    /**
     * Build a new <code>TermSuggestion</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public TermSuggestion(JsonObject json) {
        super(json);
        TermSuggestionConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>TermSuggestion</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        TermSuggestionConverter.toJson(this, json);
        return json;
    }
}
