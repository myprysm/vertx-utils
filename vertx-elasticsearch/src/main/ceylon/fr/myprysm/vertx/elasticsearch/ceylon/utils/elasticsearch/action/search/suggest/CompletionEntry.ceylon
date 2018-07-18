import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.suggest {
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
  shared Integer? length = null,
  shared Integer? offset = null,
  shared String? text = null) extends Entry() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists length) {
      json.put("length", length);
    }
    if (exists offset) {
      json.put("offset", offset);
    }
    if (exists text) {
      json.put("text", text);
    }
    return json;
  }
}

shared object completionEntry {

  shared CompletionEntry fromJson(JsonObject json) {
    Integer? length = json.getIntegerOrNull("length");
    Integer? offset = json.getIntegerOrNull("offset");
    String? text = json.getStringOrNull("text");
    return CompletionEntry {
      length = length;
      offset = offset;
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
