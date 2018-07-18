import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete {
  DeleteIndexRequest_=DeleteIndexRequest
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexRequest */
" DeleteIndexRequest.\n"
shared class DeleteIndexRequest(
  Map<String, String>? headers = null) extends BaseRequest(
  headers) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object deleteIndexRequest {

  shared DeleteIndexRequest fromJson(JsonObject json) {
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    return DeleteIndexRequest {
      headers = headers;
    };
  }

  shared object toCeylon extends Converter<DeleteIndexRequest_, DeleteIndexRequest>() {
    shared actual DeleteIndexRequest convert(DeleteIndexRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<DeleteIndexRequest, DeleteIndexRequest_>() {
    shared actual DeleteIndexRequest_ convert(DeleteIndexRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = DeleteIndexRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(DeleteIndexRequest obj) => obj.toJson();
}
