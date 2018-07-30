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
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket {
  GeoHashGrid_=GeoHashGrid
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.aggregations.bucket {
  Bucket,
  bucket_=bucket,
  MultiBucketsAggregation
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.GeoHashGrid */
shared class GeoHashGrid(
  Map<String, Bucket>? buckets = null,
  JsonObject? data = null,
  JsonObject? metaData = null,
  String? name = null,
  String? type = null) extends MultiBucketsAggregation(
  buckets,
  data,
  metaData,
  name,
  type) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object geoHashGrid {

  shared GeoHashGrid fromJson(JsonObject json) {
    Map<String, Bucket>? buckets = if (exists tmp = json.getObjectOrNull("buckets")) then HashMap { for(key->val in tmp) if (is JsonObject val) key->bucket_.fromJson(val) } else null;
    JsonObject? data = json.getObjectOrNull("data");
    JsonObject? metaData = json.getObjectOrNull("metaData");
    String? name = json.getStringOrNull("name");
    String? type = json.getStringOrNull("type");
    return GeoHashGrid {
      buckets = buckets;
      data = data;
      metaData = metaData;
      name = name;
      type = type;
    };
  }

  shared object toCeylon extends Converter<GeoHashGrid_, GeoHashGrid>() {
    shared actual GeoHashGrid convert(GeoHashGrid_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<GeoHashGrid, GeoHashGrid_>() {
    shared actual GeoHashGrid_ convert(GeoHashGrid src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = GeoHashGrid_(json);
      return ret;
    }
  }
  shared JsonObject toJson(GeoHashGrid obj) => obj.toJson();
}
