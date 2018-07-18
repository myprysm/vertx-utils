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
  DocWriteRequest_=DocWriteRequest
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.support.DocWriteRequest */
shared class DocWriteRequest(
  Map<String, String>? headers = null) extends BaseRequest(
  headers) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object docWriteRequest {

  shared DocWriteRequest fromJson(JsonObject json) {
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    return DocWriteRequest {
      headers = headers;
    };
  }

  shared object toCeylon extends Converter<DocWriteRequest_, DocWriteRequest>() {
    shared actual DocWriteRequest convert(DocWriteRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<DocWriteRequest, DocWriteRequest_>() {
    shared actual DocWriteRequest_ convert(DocWriteRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = DocWriteRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(DocWriteRequest obj) => obj.toJson();
}
