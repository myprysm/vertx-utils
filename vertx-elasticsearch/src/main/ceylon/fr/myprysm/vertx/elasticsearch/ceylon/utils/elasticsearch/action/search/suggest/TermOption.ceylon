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
  Boolean? collateMatch = null,
  shared Integer? freq = null,
  String? highlighted = null,
  Float? score = null,
  String? text = null) extends Option(
  collateMatch,
  highlighted,
  score,
  text) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists freq) {
      json.put("freq", freq);
    }
    return json;
  }
}

shared object termOption {

  shared TermOption fromJson(JsonObject json) {
    Boolean? collateMatch = json.getBooleanOrNull("collateMatch");
    Integer? freq = json.getIntegerOrNull("freq");
    String? highlighted = json.getStringOrNull("highlighted");
    Float? score = json.getFloatOrNull("score");
    String? text = json.getStringOrNull("text");
    return TermOption {
      collateMatch = collateMatch;
      freq = freq;
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
