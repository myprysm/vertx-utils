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
import fr.myprysm.vertx.elasticsearch.action.search {
  Clusters_=Clusters
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.Clusters */
shared class Clusters() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object clusters {

  shared Clusters fromJson(JsonObject json) {
    return Clusters {
    };
  }

  shared object toCeylon extends Converter<Clusters_, Clusters>() {
    shared actual Clusters convert(Clusters_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<Clusters, Clusters_>() {
    shared actual Clusters_ convert(Clusters src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = Clusters_(json);
      return ret;
    }
  }
  shared JsonObject toJson(Clusters obj) => obj.toJson();
}
