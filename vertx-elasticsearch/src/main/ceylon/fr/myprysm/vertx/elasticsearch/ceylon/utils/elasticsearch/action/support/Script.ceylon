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
shared class Script() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object script {

  shared Script fromJson(JsonObject json) {
    return Script {
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
