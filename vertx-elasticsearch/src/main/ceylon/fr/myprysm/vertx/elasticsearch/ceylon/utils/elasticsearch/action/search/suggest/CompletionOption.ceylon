import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.suggest {
  Option
}
import fr.myprysm.vertx.elasticsearch.action.search.suggest {
  CompletionOption_=CompletionOption
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
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search {
  SearchHit,
  searchHit_=searchHit
}
import ceylon.collection {
  HashMap
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  ScoreDoc,
  scoreDoc_=scoreDoc
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.suggest.CompletionOption */
shared class CompletionOption(
  Boolean? collateMatch = null,
  shared ScoreDoc? doc = null,
  String? highlighted = null,
  shared SearchHit? hit = null,
  Float? score = null,
  String? text = null) extends Option(
  collateMatch,
  highlighted,
  score,
  text) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists doc) {
      json.put("doc", doc.toJson());
    }
    if (exists hit) {
      json.put("hit", hit.toJson());
    }
    return json;
  }
}

shared object completionOption {

  shared CompletionOption fromJson(JsonObject json) {
    Boolean? collateMatch = json.getBooleanOrNull("collateMatch");
    ScoreDoc? doc = if (exists tmp = json.getObjectOrNull("doc")) then scoreDoc_.fromJson(tmp) else null;
    String? highlighted = json.getStringOrNull("highlighted");
    SearchHit? hit = if (exists tmp = json.getObjectOrNull("hit")) then searchHit_.fromJson(tmp) else null;
    Float? score = json.getFloatOrNull("score");
    String? text = json.getStringOrNull("text");
    return CompletionOption {
      collateMatch = collateMatch;
      doc = doc;
      highlighted = highlighted;
      hit = hit;
      score = score;
      text = text;
    };
  }

  shared object toCeylon extends Converter<CompletionOption_, CompletionOption>() {
    shared actual CompletionOption convert(CompletionOption_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<CompletionOption, CompletionOption_>() {
    shared actual CompletionOption_ convert(CompletionOption src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = CompletionOption_(json);
      return ret;
    }
  }
  shared JsonObject toJson(CompletionOption obj) => obj.toJson();
}
