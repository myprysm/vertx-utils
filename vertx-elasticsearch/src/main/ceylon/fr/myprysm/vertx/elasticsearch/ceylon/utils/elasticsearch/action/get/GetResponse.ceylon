import fr.myprysm.vertx.elasticsearch.action.get {
  GetResponse_=GetResponse
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
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  DocumentField,
  documentField_=documentField
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.get.GetResponse */
shared class GetResponse(
  shared Boolean? \iexists = null,
  shared Map<String, DocumentField>? fields = null,
  shared String? id = null,
  shared String? index = null,
  shared JsonObject? source = null,
  shared String? type = null,
  shared Integer? version = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists \iexists) {
      json.put("exists", \iexists);
    }
    if (exists fields) {
      json.put("fields", JsonObject{for(k->v in fields) k->documentField_.toJson(v)});
    }
    if (exists id) {
      json.put("id", id);
    }
    if (exists index) {
      json.put("index", index);
    }
    if (exists source) {
      json.put("source", source);
    }
    if (exists type) {
      json.put("type", type);
    }
    if (exists version) {
      json.put("version", version);
    }
    return json;
  }
}

shared object getResponse {

  shared GetResponse fromJson(JsonObject json) {
    Boolean? \iexists = json.getBooleanOrNull("exists");
    Map<String, DocumentField>? fields = if (exists tmp = json.getObjectOrNull("fields")) then HashMap { for(key->val in tmp) if (is JsonObject val) key->documentField_.fromJson(val) } else null;
    String? id = json.getStringOrNull("id");
    String? index = json.getStringOrNull("index");
    JsonObject? source = json.getObjectOrNull("source");
    String? type = json.getStringOrNull("type");
    Integer? version = json.getIntegerOrNull("version");
    return GetResponse {
      \iexists = \iexists;
      fields = fields;
      id = id;
      index = index;
      source = source;
      type = type;
      version = version;
    };
  }

  shared object toCeylon extends Converter<GetResponse_, GetResponse>() {
    shared actual GetResponse convert(GetResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<GetResponse, GetResponse_>() {
    shared actual GetResponse_ convert(GetResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = GetResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(GetResponse obj) => obj.toJson();
}
