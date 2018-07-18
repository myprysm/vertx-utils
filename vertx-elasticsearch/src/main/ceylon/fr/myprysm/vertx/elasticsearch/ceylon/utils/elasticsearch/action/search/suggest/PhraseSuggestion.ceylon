import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.suggest {
  Suggestion
}
import fr.myprysm.vertx.elasticsearch.action.search.suggest {
  PhraseSuggestion_=PhraseSuggestion
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.suggest.PhraseSuggestion */
shared class PhraseSuggestion() extends Suggestion() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object phraseSuggestion {

  shared PhraseSuggestion fromJson(JsonObject json) {
    return PhraseSuggestion {
    };
  }

  shared object toCeylon extends Converter<PhraseSuggestion_, PhraseSuggestion>() {
    shared actual PhraseSuggestion convert(PhraseSuggestion_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<PhraseSuggestion, PhraseSuggestion_>() {
    shared actual PhraseSuggestion_ convert(PhraseSuggestion src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = PhraseSuggestion_(json);
      return ret;
    }
  }
  shared JsonObject toJson(PhraseSuggestion obj) => obj.toJson();
}
