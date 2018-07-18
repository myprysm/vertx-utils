import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.suggest {
  Suggestion
}
import fr.myprysm.vertx.elasticsearch.action.search.suggest {
  TermSuggestion_=TermSuggestion
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.suggest.TermSuggestion */
shared class TermSuggestion() extends Suggestion() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object termSuggestion {

  shared TermSuggestion fromJson(JsonObject json) {
    return TermSuggestion {
    };
  }

  shared object toCeylon extends Converter<TermSuggestion_, TermSuggestion>() {
    shared actual TermSuggestion convert(TermSuggestion_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<TermSuggestion, TermSuggestion_>() {
    shared actual TermSuggestion_ convert(TermSuggestion src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = TermSuggestion_(json);
      return ret;
    }
  }
  shared JsonObject toJson(TermSuggestion obj) => obj.toJson();
}
