package fr.myprysm.vertx.elasticsearch.action.search.suggest;

import fr.myprysm.vertx.elasticsearch.action.search.SearchHit;
import fr.myprysm.vertx.elasticsearch.action.support.ScoreDoc;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class CompletionOption extends Option {

    private ScoreDoc doc;
    private SearchHit hit;
    private Map<String, Set<String>> contexts;

    public CompletionOption(CompletionOption other) {
        super(other);
        doc = other.doc;
        hit = other.hit;
        contexts = other.contexts;
    }

    /**
     * Build a new <code>CompletionOption</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public CompletionOption(JsonObject json) {
        super(json);
        CompletionOptionConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>CompletionOption</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        CompletionOptionConverter.toJson(this, json);
        return json;
    }

    @Override
    public CompletionOption setText(String text) {
        return (CompletionOption) super.setText(text);
    }

    @Override
    public CompletionOption setHighlighted(String highlighted) {
        return (CompletionOption) super.setHighlighted(highlighted);
    }

    @Override
    public CompletionOption setScore(float score) {
        return (CompletionOption) super.setScore(score);
    }

    @Override
    public CompletionOption setCollateMatch(Boolean collateMatch) {
        return (CompletionOption) super.setCollateMatch(collateMatch);
    }
}
