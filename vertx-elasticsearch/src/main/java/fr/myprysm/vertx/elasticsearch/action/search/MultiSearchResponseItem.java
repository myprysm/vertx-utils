package fr.myprysm.vertx.elasticsearch.action.search;

import fr.myprysm.vertx.elasticsearch.action.support.Failure;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class MultiSearchResponseItem {

    private Failure failure;
    private SearchResponse response;

    public MultiSearchResponseItem(MultiSearchResponseItem other) {
        failure = other.failure;
        response = other.response;
    }

    public boolean isFailure() {
        return failure != null;
    }

    /**
     * Build a new <code>MultiSearchResponseItem</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public MultiSearchResponseItem(JsonObject json) {
        MultiSearchResponseItemConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>MultiSearchResponseItem</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        MultiSearchResponseItemConverter.toJson(this, json);
        return json;
    }
}
