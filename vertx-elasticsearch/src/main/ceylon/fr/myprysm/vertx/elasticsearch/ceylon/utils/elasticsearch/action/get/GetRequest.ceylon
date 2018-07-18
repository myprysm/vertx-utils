import fr.myprysm.vertx.elasticsearch.action.get {
  GetRequest_=GetRequest
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.get.GetRequest */
shared class GetRequest(
  Map<String, String>? headers = null) extends BaseRequest(
  headers) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object getRequest {

  shared GetRequest fromJson(JsonObject json) {
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    return GetRequest {
      headers = headers;
    };
  }

  shared object toCeylon extends Converter<GetRequest_, GetRequest>() {
    shared actual GetRequest convert(GetRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<GetRequest, GetRequest_>() {
    shared actual GetRequest_ convert(GetRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = GetRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(GetRequest obj) => obj.toJson();
}
