import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.suggest {
  PhraseEntry,
  phraseEntry_=phraseEntry,
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
shared class PhraseSuggestion(
  " Get the suggestion entries.\n"
  shared {PhraseEntry*}? entries = null,
  String? name = null,
  String? type = null) extends Suggestion(
  name,
  type) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists entries) {
      json.put("entries", JsonArray(entries.map(phraseEntry_.toJson)));
    }
    return json;
  }
}

shared object phraseSuggestion {

  shared PhraseSuggestion fromJson(JsonObject json) {
    {PhraseEntry*}? entries = json.getArrayOrNull("entries")?.objects?.map(phraseEntry_.fromJson);
    String? name = json.getStringOrNull("name");
    String? type = json.getStringOrNull("type");
    return PhraseSuggestion {
      entries = entries;
      name = name;
      type = type;
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
