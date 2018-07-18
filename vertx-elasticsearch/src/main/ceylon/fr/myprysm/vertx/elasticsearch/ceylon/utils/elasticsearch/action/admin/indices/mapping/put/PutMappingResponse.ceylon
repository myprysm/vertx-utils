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
import fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put {
  PutMappingResponse_=PutMappingResponse
}
import ceylon.collection {
  HashMap
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  AcknowledgedResponse
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse */
" PutMappingResponse.\n"
shared class PutMappingResponse() extends AcknowledgedResponse() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object putMappingResponse {

  shared PutMappingResponse fromJson(JsonObject json) {
    return PutMappingResponse {
    };
  }

  shared object toCeylon extends Converter<PutMappingResponse_, PutMappingResponse>() {
    shared actual PutMappingResponse convert(PutMappingResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<PutMappingResponse, PutMappingResponse_>() {
    shared actual PutMappingResponse_ convert(PutMappingResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = PutMappingResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(PutMappingResponse obj) => obj.toJson();
}
