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
  Failure_=Failure
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.support.Failure */
shared class Failure(
  shared String? cause = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists cause) {
      json.put("cause", cause);
    }
    return json;
  }
}

shared object failure {

  shared Failure fromJson(JsonObject json) {
    String? cause = json.getStringOrNull("cause");
    return Failure {
      cause = cause;
    };
  }

  shared object toCeylon extends Converter<Failure_, Failure>() {
    shared actual Failure convert(Failure_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<Failure, Failure_>() {
    shared actual Failure_ convert(Failure src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = Failure_(json);
      return ret;
    }
  }
  shared JsonObject toJson(Failure obj) => obj.toJson();
}
