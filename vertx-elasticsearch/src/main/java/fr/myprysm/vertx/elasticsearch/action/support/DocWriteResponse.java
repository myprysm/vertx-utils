package fr.myprysm.vertx.elasticsearch.action.support;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.elasticsearch.action.DocWriteResponse.Result;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@DataObject(generateConverter = true)
public class DocWriteResponse extends ReplicationResponse {

    private ShardId shardId;

    private String index;
    private String type;
    private String id;

    private long version;
    private long seqNo;
    private long primaryTerm;
    private boolean forcedRefresh;
    private Result result;

    public DocWriteResponse(DocWriteResponse other) {
        super(other);
        shardId = other.shardId;
        index = other.index;
        type = other.type;
        id = other.id;
        version = other.version;
        seqNo = other.seqNo;
        primaryTerm = other.primaryTerm;
        forcedRefresh = other.forcedRefresh;
        result = other.result;
    }

    public DocWriteResponse(ReplicationResponse other) {
        super(other);
    }

    public DocWriteResponse(JsonObject json) {
        super(json);
        DocWriteResponseConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = super.toJson();
        DocWriteResponseConverter.toJson(this, json);
        return json;
    }

}
