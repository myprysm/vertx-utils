import fr.myprysm.vertx.elasticsearch.action.index {
  IndexResponse_=IndexResponse
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
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  DocWriteResponse
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.index.IndexResponse */
shared class IndexResponse() extends DocWriteResponse() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object indexResponse {

  shared IndexResponse fromJson(JsonObject json) {
    return IndexResponse {
    };
  }

  shared object toCeylon extends Converter<IndexResponse_, IndexResponse>() {
    shared actual IndexResponse convert(IndexResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<IndexResponse, IndexResponse_>() {
    shared actual IndexResponse_ convert(IndexResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = IndexResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(IndexResponse obj) => obj.toJson();
}
