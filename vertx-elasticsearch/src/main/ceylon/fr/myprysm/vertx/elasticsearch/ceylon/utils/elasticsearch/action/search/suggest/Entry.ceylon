import fr.myprysm.vertx.elasticsearch.action.search.suggest {
  Entry_=Entry
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.suggest.Entry */
shared class Entry(
  shared Integer? length = null,
  shared Integer? offset = null,
  shared String? text = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
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

shared object entry {

  shared Entry fromJson(JsonObject json) {
    Integer? length = json.getIntegerOrNull("length");
    Integer? offset = json.getIntegerOrNull("offset");
    String? text = json.getStringOrNull("text");
    return Entry {
      length = length;
      offset = offset;
      text = text;
    };
  }

  shared object toCeylon extends Converter<Entry_, Entry>() {
    shared actual Entry convert(Entry_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }
}
