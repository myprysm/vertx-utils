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
shared class Suggestion() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object suggestion {

  shared Suggestion fromJson(JsonObject json) {
    return Suggestion {
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
