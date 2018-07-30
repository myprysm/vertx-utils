import fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix {
  MatrixStats_=MatrixStats
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.aggregations {
  Aggregation
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
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.aggregations.matrix {
  MatrixStatsResult,
  matrixStatsResult_=matrixStatsResult
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixStats */
shared class MatrixStats(
  JsonObject? data = null,
  shared Integer? docCount = null,
  JsonObject? metaData = null,
  String? name = null,
  shared Map<String, MatrixStatsResult>? results = null,
  String? type = null) extends Aggregation(
  data,
  metaData,
  name,
  type) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists docCount) {
      json.put("docCount", docCount);
    }
    if (exists results) {
      json.put("results", JsonObject{for(k->v in results) k->matrixStatsResult_.toJson(v)});
    }
    return json;
  }
}

shared object matrixStats {

  shared MatrixStats fromJson(JsonObject json) {
    JsonObject? data = json.getObjectOrNull("data");
    Integer? docCount = json.getIntegerOrNull("docCount");
    JsonObject? metaData = json.getObjectOrNull("metaData");
    String? name = json.getStringOrNull("name");
    Map<String, MatrixStatsResult>? results = if (exists tmp = json.getObjectOrNull("results")) then HashMap { for(key->val in tmp) if (is JsonObject val) key->matrixStatsResult_.fromJson(val) } else null;
    String? type = json.getStringOrNull("type");
    return MatrixStats {
      data = data;
      docCount = docCount;
      metaData = metaData;
      name = name;
      results = results;
      type = type;
    };
  }

  shared object toCeylon extends Converter<MatrixStats_, MatrixStats>() {
    shared actual MatrixStats convert(MatrixStats_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<MatrixStats, MatrixStats_>() {
    shared actual MatrixStats_ convert(MatrixStats src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = MatrixStats_(json);
      return ret;
    }
  }
  shared JsonObject toJson(MatrixStats obj) => obj.toJson();
}
