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
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.support.ShardInfo */
shared class ShardInfo() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object shardInfo {

  shared ShardInfo fromJson(JsonObject json) {
    return ShardInfo {
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
