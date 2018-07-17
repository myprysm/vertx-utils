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

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class SearchScrollRequest extends BaseRequest {

    private String scrollId;
    private Long keepAlive;


    public SearchScrollRequest(SearchScrollRequest other) {
        super(other);
        scrollId = other.scrollId;
        keepAlive = other.keepAlive;
    }

    /**
     * Build a new <code>SearchScrollRequest</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public SearchScrollRequest(JsonObject json) {
        super(json);
        SearchScrollRequestConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>SearchScrollRequest</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        SearchScrollRequestConverter.toJson(this, json);
        return json;
    }

}
