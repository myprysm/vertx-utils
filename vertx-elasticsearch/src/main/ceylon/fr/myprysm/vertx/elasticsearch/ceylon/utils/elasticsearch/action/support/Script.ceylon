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
  Script_=Script
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.support.Script */
shared class Script(
  shared String? idOrCode = null,
  shared String? lang = null,
  shared Map<String, String>? options = null,
  shared JsonObject? params = null,
  shared String? type = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists idOrCode) {
      json.put("idOrCode", idOrCode);
    }
    if (exists lang) {
      json.put("lang", lang);
    }
    if (exists options) {
      json.put("options", JsonObject(options));
    }
    if (exists params) {
      json.put("params", params);
    }
    if (exists type) {
      json.put("type", type);
    }
    return json;
  }
}

shared object script {

  shared Script fromJson(JsonObject json) {
    String? idOrCode = json.getStringOrNull("idOrCode");
    String? lang = json.getStringOrNull("lang");
    Map<String, String>? options = if (exists tmp = json.getObjectOrNull("options")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    JsonObject? params = json.getObjectOrNull("params");
    String? type = json.getStringOrNull("type");
    return Script {
      idOrCode = idOrCode;
      lang = lang;
      options = options;
      params = params;
      type = type;
    };
  }

  shared object toCeylon extends Converter<Script_, Script>() {
    shared actual Script convert(Script_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<Script, Script_>() {
    shared actual Script_ convert(Script src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = Script_(json);
      return ret;
    }
  }
  shared JsonObject toJson(Script obj) => obj.toJson();
}
