import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.suggest {
  TermOption,
  termOption_=termOption,
  Entry
}
import fr.myprysm.vertx.elasticsearch.action.search.suggest {
  TermEntry_=TermEntry
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.suggest.TermEntry */
shared class TermEntry(
  Integer? length = null,
  Integer? offset = null,
  " Get the suggestion entry options.\n"
  shared {TermOption*}? options = null,
  String? text = null) extends Entry(
  length,
  offset,
  text) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists options) {
      json.put("options", JsonArray(options.map(termOption_.toJson)));
    }
    return json;
  }
}

shared object termEntry {

  shared TermEntry fromJson(JsonObject json) {
    Integer? length = json.getIntegerOrNull("length");
    Integer? offset = json.getIntegerOrNull("offset");
    {TermOption*}? options = json.getArrayOrNull("options")?.objects?.map(termOption_.fromJson);
    String? text = json.getStringOrNull("text");
    return TermEntry {
      length = length;
      offset = offset;
      options = options;
      text = text;
    };
  }

  shared object toCeylon extends Converter<TermEntry_, TermEntry>() {
    shared actual TermEntry convert(TermEntry_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<TermEntry, TermEntry_>() {
    shared actual TermEntry_ convert(TermEntry src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = TermEntry_(json);
      return ret;
    }
  }
  shared JsonObject toJson(TermEntry obj) => obj.toJson();
}
