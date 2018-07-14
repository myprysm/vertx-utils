package fr.myprysm.vertx.elasticsearch.action.index;

import fr.myprysm.vertx.elasticsearch.action.support.DocWriteResponse;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.elasticsearch.rest.RestStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@DataObject(generateConverter = true)
public class IndexResponse extends DocWriteResponse {

    private RestStatus status;


    public IndexResponse(JsonObject json) {
        super(json);
    }

    public IndexResponse(DocWriteResponse other) {
        super(other);
    }

    public IndexResponse(IndexResponse other) {
        super(other);
        status = other.status;
    }

}
