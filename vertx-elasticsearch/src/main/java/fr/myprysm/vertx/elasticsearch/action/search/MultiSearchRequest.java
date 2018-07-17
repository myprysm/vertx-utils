package fr.myprysm.vertx.elasticsearch.action.search;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class MultiSearchRequest extends BaseRequest {


    private Integer maxConcurrentSearchRequests = null;
    private List<SearchRequest> requests;

    private List<SearchRequest> safeRequests() {
        if (requests == null) {
            requests = new ArrayList<>();
        }

        return requests;
    }

    public MultiSearchRequest addRequest(SearchRequest request) {
        safeRequests().add(request);
        return this;
    }

    public List<SearchRequest> getRequests() {
        return safeRequests();
    }

    /**
     * Build a new <code>MultiSearchRequest</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public MultiSearchRequest(JsonObject json) {
        super(json);
        MultiSearchRequestConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>MultiSearchRequest</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        MultiSearchRequestConverter.toJson(this, json);
        return json;
    }
}
