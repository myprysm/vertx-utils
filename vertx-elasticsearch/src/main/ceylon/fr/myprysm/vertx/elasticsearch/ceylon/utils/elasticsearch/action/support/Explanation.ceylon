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
  Explanation_=Explanation
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.support.Explanation */
shared class Explanation() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object explanation {

  shared Explanation fromJson(JsonObject json) {
    return Explanation {
    };
  }

  shared object toCeylon extends Converter<Explanation_, Explanation>() {
    shared actual Explanation convert(Explanation_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<Explanation, Explanation_>() {
    shared actual Explanation_ convert(Explanation src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = Explanation_(json);
      return ret;
    }
  }
  shared JsonObject toJson(Explanation obj) => obj.toJson();
}
