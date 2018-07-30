import fr.myprysm.vertx.elasticsearch.action.search.suggest {
  Option_=Option
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.suggest.Option */
shared class Option(
  shared Boolean? collateMatch = null,
  shared String? highlighted = null,
  shared Float? score = null,
  shared String? text = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists collateMatch) {
      json.put("collateMatch", collateMatch);
    }
    if (exists highlighted) {
      json.put("highlighted", highlighted);
    }
    if (exists score) {
      json.put("score", score);
    }
    if (exists text) {
      json.put("text", text);
    }
    return json;
  }
}

shared object option {

  shared Option fromJson(JsonObject json) {
    Boolean? collateMatch = json.getBooleanOrNull("collateMatch");
    String? highlighted = json.getStringOrNull("highlighted");
    Float? score = json.getFloatOrNull("score");
    String? text = json.getStringOrNull("text");
    return Option {
      collateMatch = collateMatch;
      highlighted = highlighted;
      score = score;
      text = text;
    };
  }

  shared object toCeylon extends Converter<Option_, Option>() {
    shared actual Option convert(Option_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }
}
