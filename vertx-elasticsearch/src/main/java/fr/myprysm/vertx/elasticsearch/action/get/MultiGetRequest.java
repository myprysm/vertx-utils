package fr.myprysm.vertx.elasticsearch.action.get;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@DataObject(generateConverter = true)
public class MultiGetRequest extends BaseRequest {


    private String preference;
    private boolean refresh = false;
    private boolean realTime = true;
    private List<GetRequestItem> items;


    public MultiGetRequest addItem(GetRequestItem item) {
        safeItems().add(requireNonNull(item));
        return this;
    }

    private List<GetRequestItem> safeItems() {
        if (items == null) {
            items = new ArrayList<>();
        }

        return items;
    }

    public MultiGetRequest(JsonObject json) {
        super(json);
        MultiGetRequestConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = super.toJson();
        MultiGetRequestConverter.toJson(this, json);
        return json;
    }
}
