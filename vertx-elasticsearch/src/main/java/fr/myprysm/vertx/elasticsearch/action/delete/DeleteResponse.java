package fr.myprysm.vertx.elasticsearch.action.delete;

import fr.myprysm.vertx.elasticsearch.action.support.DocWriteResponse;
import fr.myprysm.vertx.elasticsearch.action.support.ShardId;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@DataObject
public class DeleteResponse extends DocWriteResponse {

    public DeleteResponse(JsonObject json) {
        super(json);
    }

    public DeleteResponse(DocWriteResponse other) {
        super(other);
    }

    public DeleteResponse(DeleteResponse other) {
        super(other);
    }

    public DeleteResponse(ShardId shardId, String index, String type, String id, long version, long seqNo, long primaryTerm, boolean forcedRefresh, org.elasticsearch.action.DocWriteResponse.Result result) {
        super(shardId, index, type, id, version, seqNo, primaryTerm, forcedRefresh, result);
    }
}