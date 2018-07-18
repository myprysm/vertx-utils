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
  DocWriteResponse_=DocWriteResponse
}
import ceylon.collection {
  HashMap
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  ReplicationResponse
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.support.DocWriteResponse */
shared class DocWriteResponse() extends ReplicationResponse() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object docWriteResponse {

  shared DocWriteResponse fromJson(JsonObject json) {
    return DocWriteResponse {
    };
  }

  shared object toCeylon extends Converter<DocWriteResponse_, DocWriteResponse>() {
    shared actual DocWriteResponse convert(DocWriteResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<DocWriteResponse, DocWriteResponse_>() {
    shared actual DocWriteResponse_ convert(DocWriteResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = DocWriteResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(DocWriteResponse obj) => obj.toJson();
}
