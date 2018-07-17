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
import fr.myprysm.vertx.elasticsearch.action.search {
  Suggestion_=Suggestion
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

  shared object toJava extends Converter<Suggestion, Suggestion_>() {
    shared actual Suggestion_ convert(Suggestion src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = Suggestion_(json);
      return ret;
    }
  }
  shared JsonObject toJson(Suggestion obj) => obj.toJson();
}
