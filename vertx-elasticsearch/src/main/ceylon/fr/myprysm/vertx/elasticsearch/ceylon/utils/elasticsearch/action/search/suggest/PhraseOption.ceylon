import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.suggest {
  Option
}
import fr.myprysm.vertx.elasticsearch.action.search.suggest {
  PhraseOption_=PhraseOption
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.suggest.PhraseOption */
shared class PhraseOption(
  Boolean? collateMatch = null,
  String? highlighted = null,
  Float? score = null,
  String? text = null) extends Option(
  collateMatch,
  highlighted,
  score,
  text) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object phraseOption {

  shared PhraseOption fromJson(JsonObject json) {
    Boolean? collateMatch = json.getBooleanOrNull("collateMatch");
    String? highlighted = json.getStringOrNull("highlighted");
    Float? score = json.getFloatOrNull("score");
    String? text = json.getStringOrNull("text");
    return PhraseOption {
      collateMatch = collateMatch;
      highlighted = highlighted;
      score = score;
      text = text;
    };
  }

  shared object toCeylon extends Converter<PhraseOption_, PhraseOption>() {
    shared actual PhraseOption convert(PhraseOption_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<PhraseOption, PhraseOption_>() {
    shared actual PhraseOption_ convert(PhraseOption src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = PhraseOption_(json);
      return ret;
    }
  }
  shared JsonObject toJson(PhraseOption obj) => obj.toJson();
}
