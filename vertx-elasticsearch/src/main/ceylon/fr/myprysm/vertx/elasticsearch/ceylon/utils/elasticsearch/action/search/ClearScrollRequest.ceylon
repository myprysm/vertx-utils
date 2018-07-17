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
  ClearScrollRequest_=ClearScrollRequest
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action {
  BaseRequest
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.ClearScrollRequest */
shared class ClearScrollRequest(
  Map<String, String>? headers = null,
  shared {String*}? scrollIds = null) extends BaseRequest(
  headers) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists scrollIds) {
      json.put("scrollIds", JsonArray(scrollIds));
    }
    return json;
  }
}

shared object clearScrollRequest {

  shared ClearScrollRequest fromJson(JsonObject json) {
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    {String*}? scrollIds = json.getArrayOrNull("scrollIds")?.strings;
    return ClearScrollRequest {
      headers = headers;
      scrollIds = scrollIds;
    };
  }

  shared object toCeylon extends Converter<ClearScrollRequest_, ClearScrollRequest>() {
    shared actual ClearScrollRequest convert(ClearScrollRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<ClearScrollRequest, ClearScrollRequest_>() {
    shared actual ClearScrollRequest_ convert(ClearScrollRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = ClearScrollRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(ClearScrollRequest obj) => obj.toJson();
}
