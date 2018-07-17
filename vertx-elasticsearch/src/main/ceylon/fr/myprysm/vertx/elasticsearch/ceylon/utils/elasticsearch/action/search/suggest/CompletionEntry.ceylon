import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.suggest {
  CompletionOption,
  completionOption_=completionOption,
  Entry
}
import fr.myprysm.vertx.elasticsearch.action.search.suggest {
  CompletionEntry_=CompletionEntry
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.suggest.CompletionEntry */
shared class CompletionEntry(
  Integer? length = null,
  Integer? offset = null,
  " Get the suggestion entry options.\n"
  shared {CompletionOption*}? options = null,
  String? text = null) extends Entry(
  length,
  offset,
  text) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists options) {
      json.put("options", JsonArray(options.map(completionOption_.toJson)));
    }
    return json;
  }
}

shared object completionEntry {

  shared CompletionEntry fromJson(JsonObject json) {
    Integer? length = json.getIntegerOrNull("length");
    Integer? offset = json.getIntegerOrNull("offset");
    {CompletionOption*}? options = json.getArrayOrNull("options")?.objects?.map(completionOption_.fromJson);
    String? text = json.getStringOrNull("text");
    return CompletionEntry {
      length = length;
      offset = offset;
      options = options;
      text = text;
    };
  }

  shared object toCeylon extends Converter<CompletionEntry_, CompletionEntry>() {
    shared actual CompletionEntry convert(CompletionEntry_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<CompletionEntry, CompletionEntry_>() {
    shared actual CompletionEntry_ convert(CompletionEntry src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = CompletionEntry_(json);
      return ret;
    }
  }
  shared JsonObject toJson(CompletionEntry obj) => obj.toJson();
}
