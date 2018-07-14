import fr.myprysm.vertx.elasticsearch.action {
  BaseRequest_=BaseRequest
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.BaseRequest */
" Base DataObject request.\n"
shared class BaseRequest(
  " Add a header to the request.\n"
  shared Map<String, String>? headers = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists headers) {
      json.put("headers", JsonObject(headers));
    }
    return json;
  }
}

shared object baseRequest {

  shared BaseRequest fromJson(JsonObject json) {
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    return BaseRequest {
      headers = headers;
    };
  }

  shared object toCeylon extends Converter<BaseRequest_, BaseRequest>() {
    shared actual BaseRequest convert(BaseRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<BaseRequest, BaseRequest_>() {
    shared actual BaseRequest_ convert(BaseRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = BaseRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(BaseRequest obj) => obj.toJson();
}
