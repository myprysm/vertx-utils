import fr.myprysm.vertx.elasticsearch.action.admin.refresh {
  RefreshRequest_=RefreshRequest
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
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action {
  BaseRequest
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.admin.refresh.RefreshRequest */
shared class RefreshRequest(
  Map<String, String>? headers = null,
  shared {String*}? indexs = null) extends BaseRequest(
  headers) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists indexs) {
      json.put("indexs", JsonArray(indexs));
    }
    return json;
  }
}

shared object refreshRequest {

  shared RefreshRequest fromJson(JsonObject json) {
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    {String*}? indexs = json.getArrayOrNull("indexs")?.strings;
    return RefreshRequest {
      headers = headers;
      indexs = indexs;
    };
  }

  shared object toCeylon extends Converter<RefreshRequest_, RefreshRequest>() {
    shared actual RefreshRequest convert(RefreshRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<RefreshRequest, RefreshRequest_>() {
    shared actual RefreshRequest_ convert(RefreshRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = RefreshRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(RefreshRequest obj) => obj.toJson();
}
