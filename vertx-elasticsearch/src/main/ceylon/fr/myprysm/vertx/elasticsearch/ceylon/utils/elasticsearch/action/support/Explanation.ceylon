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
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  Explanation,
  explanation_=explanation
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.support.Explanation */
shared class Explanation(
  shared String? description = null,
  shared {Explanation*}? details = null,
  shared Boolean? match = null,
  shared Float? \ivalue = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists description) {
      json.put("description", description);
    }
    if (exists details) {
      json.put("details", JsonArray(details.map(explanation_.toJson)));
    }
    if (exists match) {
      json.put("match", match);
    }
    if (exists \ivalue) {
      json.put("value", \ivalue);
    }
    return json;
  }
}

shared object explanation {

  shared Explanation fromJson(JsonObject json) {
    String? description = json.getStringOrNull("description");
    {Explanation*}? details = json.getArrayOrNull("details")?.objects?.map(this.fromJson);
    Boolean? match = json.getBooleanOrNull("match");
    Float? \ivalue = json.getFloatOrNull("value");
    return Explanation {
      description = description;
      details = details;
      match = match;
      \ivalue = \ivalue;
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
