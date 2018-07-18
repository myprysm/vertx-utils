import fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix {
  MatrixStatsResult_=MatrixStatsResult
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixStatsResult */
shared class MatrixStatsResult() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object matrixStatsResult {

  shared MatrixStatsResult fromJson(JsonObject json) {
    return MatrixStatsResult {
    };
  }

  shared object toCeylon extends Converter<MatrixStatsResult_, MatrixStatsResult>() {
    shared actual MatrixStatsResult convert(MatrixStatsResult_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<MatrixStatsResult, MatrixStatsResult_>() {
    shared actual MatrixStatsResult_ convert(MatrixStatsResult src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = MatrixStatsResult_(json);
      return ret;
    }
  }
  shared JsonObject toJson(MatrixStatsResult obj) => obj.toJson();
}
