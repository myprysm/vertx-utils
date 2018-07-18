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
  ShardId_=ShardId
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.support.ShardId */
shared class ShardId() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object shardId {

  shared ShardId fromJson(JsonObject json) {
    return ShardId {
    };
  }

  shared object toCeylon extends Converter<ShardId_, ShardId>() {
    shared actual ShardId convert(ShardId_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<ShardId, ShardId_>() {
    shared actual ShardId_ convert(ShardId src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = ShardId_(json);
      return ret;
    }
  }
  shared JsonObject toJson(ShardId obj) => obj.toJson();
}
