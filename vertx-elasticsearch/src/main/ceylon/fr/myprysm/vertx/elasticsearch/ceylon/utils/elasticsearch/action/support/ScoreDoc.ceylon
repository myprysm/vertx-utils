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
shared class ScoreDoc() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object scoreDoc {

  shared ScoreDoc fromJson(JsonObject json) {
    return ScoreDoc {
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
