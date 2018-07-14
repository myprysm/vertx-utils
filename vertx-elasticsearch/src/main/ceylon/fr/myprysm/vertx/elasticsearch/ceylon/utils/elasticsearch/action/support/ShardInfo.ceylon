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
import fr.myprysm.vertx.elasticsearch.action.support {
  ShardInfo_=ShardInfo
}
import ceylon.collection {
  HashMap
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  ShardInfoFailure,
  shardInfoFailure_=shardInfoFailure
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.support.ShardInfo */
shared class ShardInfo(
  shared Integer? failed = null,
  shared {ShardInfoFailure*}? failures = null,
  shared Integer? successful = null,
  shared Integer? total = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists failed) {
      json.put("failed", failed);
    }
    if (exists failures) {
      json.put("failures", JsonArray(failures.map(shardInfoFailure_.toJson)));
    }
    if (exists successful) {
      json.put("successful", successful);
    }
    if (exists total) {
      json.put("total", total);
    }
    return json;
  }
}

shared object shardInfo {

  shared ShardInfo fromJson(JsonObject json) {
    Integer? failed = json.getIntegerOrNull("failed");
    {ShardInfoFailure*}? failures = json.getArrayOrNull("failures")?.objects?.map(shardInfoFailure_.fromJson);
    Integer? successful = json.getIntegerOrNull("successful");
    Integer? total = json.getIntegerOrNull("total");
    return ShardInfo {
      failed = failed;
      failures = failures;
      successful = successful;
      total = total;
    };
  }

  shared object toCeylon extends Converter<ShardInfo_, ShardInfo>() {
    shared actual ShardInfo convert(ShardInfo_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<ShardInfo, ShardInfo_>() {
    shared actual ShardInfo_ convert(ShardInfo src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = ShardInfo_(json);
      return ret;
    }
  }
  shared JsonObject toJson(ShardInfo obj) => obj.toJson();
}
