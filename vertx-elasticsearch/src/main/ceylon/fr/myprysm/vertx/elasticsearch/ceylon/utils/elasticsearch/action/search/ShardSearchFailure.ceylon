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
import fr.myprysm.vertx.elasticsearch.action.search {
  ShardSearchFailure_=ShardSearchFailure
}
import ceylon.collection {
  HashMap
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  ShardId,
  shardId_=shardId,
  Failure
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.support.ShardFailure */
shared class ShardSearchFailure(
  String? cause = null,
  shared String? index = null,
  shared ShardId? shardId = null,
  shared String? status = null) extends Failure(
  cause) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists index) {
      json.put("index", index);
    }
    if (exists shardId) {
      json.put("shardId", shardId.toJson());
    }
    if (exists status) {
      json.put("status", status);
    }
    return json;
  }
}

shared object shardSearchFailure {

  shared ShardSearchFailure fromJson(JsonObject json) {
    String? cause = json.getStringOrNull("cause");
    String? index = json.getStringOrNull("index");
    ShardId? shardId = if (exists tmp = json.getObjectOrNull("shardId")) then shardId_.fromJson(tmp) else null;
    String? status = json.getStringOrNull("status");
    return ShardSearchFailure {
      cause = cause;
      index = index;
      shardId = shardId;
      status = status;
    };
  }

  shared object toCeylon extends Converter<ShardSearchFailure_, ShardSearchFailure>() {
    shared actual ShardSearchFailure convert(ShardSearchFailure_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<ShardSearchFailure, ShardSearchFailure_>() {
    shared actual ShardSearchFailure_ convert(ShardSearchFailure src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = ShardSearchFailure_(json);
      return ret;
    }
  }
  shared JsonObject toJson(ShardSearchFailure obj) => obj.toJson();
}
