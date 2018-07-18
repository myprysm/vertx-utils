import fr.myprysm.vertx.elasticsearch.action.admin.indices.create {
  CreateIndexRequest_=CreateIndexRequest
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexRequest */
shared class CreateIndexRequest(
  Map<String, String>? headers = null,
  shared Map<String, JsonObject>? mappings = null) extends BaseRequest(
  headers) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists mappings) {
      json.put("mappings", JsonObject(mappings));
    }
    return json;
  }
}

shared object createIndexRequest {

  shared CreateIndexRequest fromJson(JsonObject json) {
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    Map<String, JsonObject>? mappings = if (exists tmp = json.getObjectOrNull("mappings")) then HashMap { for(key->val in tmp) if (is JsonObject val) key->val } else null;
    return CreateIndexRequest {
      headers = headers;
      mappings = mappings;
    };
  }

  shared object toCeylon extends Converter<CreateIndexRequest_, CreateIndexRequest>() {
    shared actual CreateIndexRequest convert(CreateIndexRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<CreateIndexRequest, CreateIndexRequest_>() {
    shared actual CreateIndexRequest_ convert(CreateIndexRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = CreateIndexRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(CreateIndexRequest obj) => obj.toJson();
}
