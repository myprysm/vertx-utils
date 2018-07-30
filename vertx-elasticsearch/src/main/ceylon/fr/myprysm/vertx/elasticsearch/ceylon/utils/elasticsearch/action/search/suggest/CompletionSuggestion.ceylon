import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.suggest {
  CompletionEntry,
  completionEntry_=completionEntry,
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
shared class CompletionSuggestion(
  " Get the suggestion entries.\n"
  shared {CompletionEntry*}? entries = null,
  String? name = null,
  String? type = null) extends Suggestion(
  name,
  type) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists entries) {
      json.put("entries", JsonArray(entries.map(completionEntry_.toJson)));
    }
    return json;
  }
}

shared object completionSuggestion {

  shared CompletionSuggestion fromJson(JsonObject json) {
    {CompletionEntry*}? entries = json.getArrayOrNull("entries")?.objects?.map(completionEntry_.fromJson);
    String? name = json.getStringOrNull("name");
    String? type = json.getStringOrNull("type");
    return CompletionSuggestion {
      entries = entries;
      name = name;
      type = type;
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
