import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.suggest {
  Suggestion
}
import fr.myprysm.vertx.elasticsearch.action.search.suggest {
  CompletionSuggestion_=CompletionSuggestion
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.suggest.CompletionSuggestion */
shared class CompletionSuggestion() extends Suggestion() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object completionSuggestion {

  shared CompletionSuggestion fromJson(JsonObject json) {
    return CompletionSuggestion {
    };
  }

  shared object toCeylon extends Converter<CompletionSuggestion_, CompletionSuggestion>() {
    shared actual CompletionSuggestion convert(CompletionSuggestion_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<CompletionSuggestion, CompletionSuggestion_>() {
    shared actual CompletionSuggestion_ convert(CompletionSuggestion src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = CompletionSuggestion_(json);
      return ret;
    }
  }
  shared JsonObject toJson(CompletionSuggestion obj) => obj.toJson();
}
