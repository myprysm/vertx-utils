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
  ShardFailure_=ShardFailure
}
import ceylon.collection {
  HashMap
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  Failure
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.support.ShardFailure */
shared class ShardFailure(
  String? cause = null,
  shared String? index = null,
  shared Integer? shardId = null,
  shared String? status = null) extends Failure(
  cause) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists index) {
      json.put("index", index);
    }
    if (exists shardId) {
      json.put("shardId", shardId);
    }
    if (exists status) {
      json.put("status", status);
    }
    return json;
  }
}

shared object shardFailure {

  shared ShardFailure fromJson(JsonObject json) {
    String? cause = json.getStringOrNull("cause");
    String? index = json.getStringOrNull("index");
    Integer? shardId = json.getIntegerOrNull("shardId");
    String? status = json.getStringOrNull("status");
    return ShardFailure {
      cause = cause;
      index = index;
      shardId = shardId;
      status = status;
    };
  }

  shared object toCeylon extends Converter<ShardFailure_, ShardFailure>() {
    shared actual ShardFailure convert(ShardFailure_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<ShardFailure, ShardFailure_>() {
    shared actual ShardFailure_ convert(ShardFailure src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = ShardFailure_(json);
      return ret;
    }
  }
  shared JsonObject toJson(ShardFailure obj) => obj.toJson();
}
