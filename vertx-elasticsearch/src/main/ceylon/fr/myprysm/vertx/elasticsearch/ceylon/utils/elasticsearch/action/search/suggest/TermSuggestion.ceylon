import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.suggest {
  TermEntry,
  termEntry_=termEntry,
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
shared class TermSuggestion(
  " Get the suggestion entries.\n"
  shared {TermEntry*}? entries = null,
  String? name = null,
  String? type = null) extends Suggestion(
  name,
  type) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists entries) {
      json.put("entries", JsonArray(entries.map(termEntry_.toJson)));
    }
    return json;
  }
}

shared object termSuggestion {

  shared TermSuggestion fromJson(JsonObject json) {
    {TermEntry*}? entries = json.getArrayOrNull("entries")?.objects?.map(termEntry_.fromJson);
    String? name = json.getStringOrNull("name");
    String? type = json.getStringOrNull("type");
    return TermSuggestion {
      entries = entries;
      name = name;
      type = type;
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
