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
  ShardInfoFailure_=ShardInfoFailure
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.support.ShardInfoFailure */
shared class ShardInfoFailure() extends Failure() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object shardInfoFailure {

  shared ShardInfoFailure fromJson(JsonObject json) {
    return ShardInfoFailure {
    };
  }

  shared object toCeylon extends Converter<ShardInfoFailure_, ShardInfoFailure>() {
    shared actual ShardInfoFailure convert(ShardInfoFailure_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<ShardInfoFailure, ShardInfoFailure_>() {
    shared actual ShardInfoFailure_ convert(ShardInfoFailure src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = ShardInfoFailure_(json);
      return ret;
    }
  }
  shared JsonObject toJson(ShardInfoFailure obj) => obj.toJson();
}
