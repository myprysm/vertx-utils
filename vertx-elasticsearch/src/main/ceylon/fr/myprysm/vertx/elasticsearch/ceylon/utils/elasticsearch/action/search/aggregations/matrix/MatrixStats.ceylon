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
  JsonObject? metaData = null,
  String? name = null,
  String? type = null) extends Aggregation(
  data,
  metaData,
  name,
  type) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object matrixStats {

  shared MatrixStats fromJson(JsonObject json) {
    JsonObject? data = json.getObjectOrNull("data");
    JsonObject? metaData = json.getObjectOrNull("metaData");
    String? name = json.getStringOrNull("name");
    String? type = json.getStringOrNull("type");
    return MatrixStats {
      data = data;
      metaData = metaData;
      name = name;
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
