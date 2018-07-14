import ceylon.json {
  JsonObject=Object,
  JsonArray=Array,
  parse
}
import io.vertx.lang.ceylon {
  BaseDataObject,
  Converter,
  ToJava
}
import fr.myprysm.vertx.elasticsearch.action.delete {
  DeleteResponse_=DeleteResponse
}
import ceylon.collection {
  HashMap
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  ShardId,
  shardId_=shardId,
  ShardInfo,
  shardInfo_=shardInfo,
  DocWriteResponse
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.delete.DeleteResponse */
shared class DeleteResponse(
  Boolean? forcedRefresh = null,
  String? id = null,
  String? index = null,
  Integer? primaryTerm = null,
  String? result = null,
  Integer? seqNo = null,
  ShardId? shardId = null,
  ShardInfo? shardInfo = null,
  String? type = null,
  Integer? version = null) extends DocWriteResponse(
  forcedRefresh,
  id,
  index,
  primaryTerm,
  result,
  seqNo,
  shardId,
  shardInfo,
  type,
  version) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object deleteResponse {

  shared DeleteResponse fromJson(JsonObject json) {
    Boolean? forcedRefresh = json.getBooleanOrNull("forcedRefresh");
    String? id = json.getStringOrNull("id");
    String? index = json.getStringOrNull("index");
    Integer? primaryTerm = json.getIntegerOrNull("primaryTerm");
    String? result = json.getStringOrNull("result");
    Integer? seqNo = json.getIntegerOrNull("seqNo");
    ShardId? shardId = if (exists tmp = json.getObjectOrNull("shardId")) then shardId_.fromJson(tmp) else null;
    ShardInfo? shardInfo = if (exists tmp = json.getObjectOrNull("shardInfo")) then shardInfo_.fromJson(tmp) else null;
    String? type = json.getStringOrNull("type");
    Integer? version = json.getIntegerOrNull("version");
    return DeleteResponse {
      forcedRefresh = forcedRefresh;
      id = id;
      index = index;
      primaryTerm = primaryTerm;
      result = result;
      seqNo = seqNo;
      shardId = shardId;
      shardInfo = shardInfo;
      type = type;
      version = version;
    };
  }

  shared object toCeylon extends Converter<DeleteResponse_, DeleteResponse>() {
    shared actual DeleteResponse convert(DeleteResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<DeleteResponse, DeleteResponse_>() {
    shared actual DeleteResponse_ convert(DeleteResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = DeleteResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(DeleteResponse obj) => obj.toJson();
}
