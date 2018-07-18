import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.suggest {
  Option
}
import fr.myprysm.vertx.elasticsearch.action.search.suggest {
  TermOption_=TermOption
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.suggest.TermOption */
shared class TermOption(
  shared Boolean? collateMatch = null,
  shared String? highlighted = null,
  shared Float? score = null,
  shared String? text = null) extends Option() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
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

shared object termOption {

  shared TermOption fromJson(JsonObject json) {
    Boolean? collateMatch = json.getBooleanOrNull("collateMatch");
    String? highlighted = json.getStringOrNull("highlighted");
    Float? score = json.getFloatOrNull("score");
    String? text = json.getStringOrNull("text");
    return TermOption {
      collateMatch = collateMatch;
      highlighted = highlighted;
      score = score;
      text = text;
    };
  }

  shared object toCeylon extends Converter<TermOption_, TermOption>() {
    shared actual TermOption convert(TermOption_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<TermOption, TermOption_>() {
    shared actual TermOption_ convert(TermOption src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = TermOption_(json);
      return ret;
    }
  }
  shared JsonObject toJson(TermOption obj) => obj.toJson();
}
