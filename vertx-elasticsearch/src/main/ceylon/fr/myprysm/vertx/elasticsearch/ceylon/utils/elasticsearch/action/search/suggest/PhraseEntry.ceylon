import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.suggest {
  PhraseOption,
  phraseOption_=phraseOption,
  Entry
}
import fr.myprysm.vertx.elasticsearch.action.search.suggest {
  PhraseEntry_=PhraseEntry
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.suggest.PhraseEntry */
shared class PhraseEntry(
  Integer? length = null,
  Integer? offset = null,
  " Get the suggestion entry options.\n"
  shared {PhraseOption*}? options = null,
  String? text = null) extends Entry(
  length,
  offset,
  text) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists options) {
      json.put("options", JsonArray(options.map(phraseOption_.toJson)));
    }
    return json;
  }
}

shared object phraseEntry {

  shared PhraseEntry fromJson(JsonObject json) {
    Integer? length = json.getIntegerOrNull("length");
    Integer? offset = json.getIntegerOrNull("offset");
    {PhraseOption*}? options = json.getArrayOrNull("options")?.objects?.map(phraseOption_.fromJson);
    String? text = json.getStringOrNull("text");
    return PhraseEntry {
      length = length;
      offset = offset;
      options = options;
      text = text;
    };
  }

  shared object toCeylon extends Converter<PhraseEntry_, PhraseEntry>() {
    shared actual PhraseEntry convert(PhraseEntry_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<PhraseEntry, PhraseEntry_>() {
    shared actual PhraseEntry_ convert(PhraseEntry src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = PhraseEntry_(json);
      return ret;
    }
  }
  shared JsonObject toJson(PhraseEntry obj) => obj.toJson();
}
