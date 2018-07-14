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
import fr.myprysm.vertx.elasticsearch.action.update {
  UpdateResponse_=UpdateResponse
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.get {
  GetResponse,
  getResponse_=getResponse
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.update.UpdateResponse */
" Update response from Elasticsearch.\n"
shared class UpdateResponse(
  Boolean? forcedRefresh = null,
  shared GetResponse? getResult = null,
  String? id = null,
  String? index = null,
  Integer? primaryTerm = null,
  String? result = null,
  Integer? seqNo = null,
  ShardId? shardId = null,
  ShardInfo? shardInfo = null,
  shared String? status = null,
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
    if (exists getResult) {
      json.put("getResult", getResult.toJson());
    }
    if (exists status) {
      json.put("status", status);
    }
    return json;
  }
}

shared object updateResponse {

  shared UpdateResponse fromJson(JsonObject json) {
    Boolean? forcedRefresh = json.getBooleanOrNull("forcedRefresh");
    GetResponse? getResult = if (exists tmp = json.getObjectOrNull("getResult")) then getResponse_.fromJson(tmp) else null;
    String? id = json.getStringOrNull("id");
    String? index = json.getStringOrNull("index");
    Integer? primaryTerm = json.getIntegerOrNull("primaryTerm");
    String? result = json.getStringOrNull("result");
    Integer? seqNo = json.getIntegerOrNull("seqNo");
    ShardId? shardId = if (exists tmp = json.getObjectOrNull("shardId")) then shardId_.fromJson(tmp) else null;
    ShardInfo? shardInfo = if (exists tmp = json.getObjectOrNull("shardInfo")) then shardInfo_.fromJson(tmp) else null;
    String? status = json.getStringOrNull("status");
    String? type = json.getStringOrNull("type");
    Integer? version = json.getIntegerOrNull("version");
    return UpdateResponse {
      forcedRefresh = forcedRefresh;
      getResult = getResult;
      id = id;
      index = index;
      primaryTerm = primaryTerm;
      result = result;
      seqNo = seqNo;
      shardId = shardId;
      shardInfo = shardInfo;
      status = status;
      type = type;
      version = version;
    };
  }

  shared object toCeylon extends Converter<UpdateResponse_, UpdateResponse>() {
    shared actual UpdateResponse convert(UpdateResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<UpdateResponse, UpdateResponse_>() {
    shared actual UpdateResponse_ convert(UpdateResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = UpdateResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(UpdateResponse obj) => obj.toJson();
}
