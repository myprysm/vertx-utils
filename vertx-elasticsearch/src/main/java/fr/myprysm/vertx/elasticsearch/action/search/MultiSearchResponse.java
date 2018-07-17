package fr.myprysm.vertx.elasticsearch.action.search;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Iterator;
import java.util.List;

import static java.util.Collections.emptyIterator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class MultiSearchResponse implements Iterable<MultiSearchResponseItem> {

    private List<MultiSearchResponseItem> responses;

    public MultiSearchResponse(MultiSearchResponse other) {
        responses = other.responses;
    }

    /**
     * Build a new <code>MultiSearchResponse</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public MultiSearchResponse(JsonObject json) {
        MultiSearchResponseConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>MultiSearchResponse</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        MultiSearchResponseConverter.toJson(this, json);
        return json;
    }


    @Override
    public Iterator<MultiSearchResponseItem> iterator() {
        return responses == null ? emptyIterator() : responses.iterator();
    }
}
