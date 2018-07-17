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
import fr.myprysm.vertx.elasticsearch.action.support {
  ScoreDoc_=ScoreDoc
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.support.ScoreDoc */
shared class ScoreDoc(
  shared Integer? doc = null,
  shared Float? score = null,
  shared Integer? shardIndex = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists doc) {
      json.put("doc", doc);
    }
    if (exists score) {
      json.put("score", score);
    }
    if (exists shardIndex) {
      json.put("shardIndex", shardIndex);
    }
    return json;
  }
}

shared object scoreDoc {

  shared ScoreDoc fromJson(JsonObject json) {
    Integer? doc = json.getIntegerOrNull("doc");
    Float? score = json.getFloatOrNull("score");
    Integer? shardIndex = json.getIntegerOrNull("shardIndex");
    return ScoreDoc {
      doc = doc;
      score = score;
      shardIndex = shardIndex;
    };
  }

  shared object toCeylon extends Converter<ScoreDoc_, ScoreDoc>() {
    shared actual ScoreDoc convert(ScoreDoc_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<ScoreDoc, ScoreDoc_>() {
    shared actual ScoreDoc_ convert(ScoreDoc src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = ScoreDoc_(json);
      return ret;
    }
  }
  shared JsonObject toJson(ScoreDoc obj) => obj.toJson();
}
