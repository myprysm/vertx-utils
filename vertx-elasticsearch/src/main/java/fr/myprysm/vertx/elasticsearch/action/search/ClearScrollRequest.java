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
public class ClearScrollRequest extends BaseRequest {

    private List<String> scrollIds;


    private List<String> safeScrollIds() {
        if (scrollIds == null) {
            scrollIds = new ArrayList<>();
        }

        return scrollIds;
    }

    public ClearScrollRequest addScrollId(String scrollId) {
        safeScrollIds().add(scrollId);
        return this;
    }

    /**
     * Build a new <code>ClearScrollRequest</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public ClearScrollRequest(JsonObject json) {
        super(json);
        ClearScrollRequestConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>ClearScrollRequest</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        ClearScrollRequestConverter.toJson(this, json);
        return json;
    }
}
