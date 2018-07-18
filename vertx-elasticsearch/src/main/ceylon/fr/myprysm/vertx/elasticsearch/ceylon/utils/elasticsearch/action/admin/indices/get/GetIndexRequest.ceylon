import fr.myprysm.vertx.elasticsearch.action.admin.indices.get {
  GetIndexRequest_=GetIndexRequest
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest */
shared class GetIndexRequest(
  Map<String, String>? headers = null) extends BaseRequest(
  headers) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object getIndexRequest {

  shared GetIndexRequest fromJson(JsonObject json) {
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    return GetIndexRequest {
      headers = headers;
    };
  }

  shared object toCeylon extends Converter<GetIndexRequest_, GetIndexRequest>() {
    shared actual GetIndexRequest convert(GetIndexRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<GetIndexRequest, GetIndexRequest_>() {
    shared actual GetIndexRequest_ convert(GetIndexRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = GetIndexRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(GetIndexRequest obj) => obj.toJson();
}
