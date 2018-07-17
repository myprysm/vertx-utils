import fr.myprysm.vertx.elasticsearch.action.admin.refresh {
  RefreshResponse_=RefreshResponse
}
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
import ceylon.collection {
  HashMap
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  ShardFailure,
  shardFailure_=shardFailure
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.admin.refresh.RefreshResponse */
shared class RefreshResponse(
  shared Integer? failedShards = null,
  shared {ShardFailure*}? shardFailures = null,
  shared String? status = null,
  shared Integer? successfulShards = null,
  shared Integer? totalShards = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists failedShards) {
      json.put("failedShards", failedShards);
    }
    if (exists shardFailures) {
      json.put("shardFailures", JsonArray(shardFailures.map(shardFailure_.toJson)));
    }
    if (exists status) {
      json.put("status", status);
    }
    if (exists successfulShards) {
      json.put("successfulShards", successfulShards);
    }
    if (exists totalShards) {
      json.put("totalShards", totalShards);
    }
    return json;
  }
}

shared object refreshResponse {

  shared RefreshResponse fromJson(JsonObject json) {
    Integer? failedShards = json.getIntegerOrNull("failedShards");
    {ShardFailure*}? shardFailures = json.getArrayOrNull("shardFailures")?.objects?.map(shardFailure_.fromJson);
    String? status = json.getStringOrNull("status");
    Integer? successfulShards = json.getIntegerOrNull("successfulShards");
    Integer? totalShards = json.getIntegerOrNull("totalShards");
    return RefreshResponse {
      failedShards = failedShards;
      shardFailures = shardFailures;
      status = status;
      successfulShards = successfulShards;
      totalShards = totalShards;
    };
  }

  shared object toCeylon extends Converter<RefreshResponse_, RefreshResponse>() {
    shared actual RefreshResponse convert(RefreshResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<RefreshResponse, RefreshResponse_>() {
    shared actual RefreshResponse_ convert(RefreshResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = RefreshResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(RefreshResponse obj) => obj.toJson();
}
