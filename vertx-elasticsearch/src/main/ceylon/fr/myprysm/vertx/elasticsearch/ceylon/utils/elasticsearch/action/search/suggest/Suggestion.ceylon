import fr.myprysm.vertx.elasticsearch.action.search.suggest {
  Suggestion_=Suggestion
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.suggest.Suggestion */
shared class Suggestion(
  shared String? name = null,
  shared String? type = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists name) {
      json.put("name", name);
    }
    if (exists type) {
      json.put("type", type);
    }
    return json;
  }
}

shared object suggestion {

  shared Suggestion fromJson(JsonObject json) {
    String? name = json.getStringOrNull("name");
    String? type = json.getStringOrNull("type");
    return Suggestion {
      name = name;
      type = type;
    };
  }

  shared object toCeylon extends Converter<Suggestion_, Suggestion>() {
    shared actual Suggestion convert(Suggestion_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }
}
