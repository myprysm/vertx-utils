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
shared class MatrixStatsResult(
  shared Map<String, Float>? correlation = null,
  shared Integer? count = null,
  shared Map<String, Float>? covariance = null,
  shared Float? kurtosis = null,
  shared Float? mean = null,
  shared String? name = null,
  shared Float? skewness = null,
  shared Float? variance = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists correlation) {
      json.put("correlation", JsonObject(correlation));
    }
    if (exists count) {
      json.put("count", count);
    }
    if (exists covariance) {
      json.put("covariance", JsonObject(covariance));
    }
    if (exists kurtosis) {
      json.put("kurtosis", kurtosis);
    }
    if (exists mean) {
      json.put("mean", mean);
    }
    if (exists name) {
      json.put("name", name);
    }
    if (exists skewness) {
      json.put("skewness", skewness);
    }
    if (exists variance) {
      json.put("variance", variance);
    }
    return json;
  }
}

shared object matrixStatsResult {

  shared MatrixStatsResult fromJson(JsonObject json) {
    Map<String, Float>? correlation = if (exists tmp = json.getObjectOrNull("correlation")) then HashMap { for(key->val in tmp) if (is Float val) key->val } else null;
    Integer? count = json.getIntegerOrNull("count");
    Map<String, Float>? covariance = if (exists tmp = json.getObjectOrNull("covariance")) then HashMap { for(key->val in tmp) if (is Float val) key->val } else null;
    Float? kurtosis = json.getFloatOrNull("kurtosis");
    Float? mean = json.getFloatOrNull("mean");
    String? name = json.getStringOrNull("name");
    Float? skewness = json.getFloatOrNull("skewness");
    Float? variance = json.getFloatOrNull("variance");
    return MatrixStatsResult {
      correlation = correlation;
      count = count;
      covariance = covariance;
      kurtosis = kurtosis;
      mean = mean;
      name = name;
      skewness = skewness;
      variance = variance;
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
