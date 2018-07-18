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
import fr.myprysm.vertx.elasticsearch.action.update {
  UpdateResponse_=UpdateResponse
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.update.UpdateResponse */
" Update response from Elasticsearch.\n"
shared class UpdateResponse() extends DocWriteResponse() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object updateResponse {

  shared UpdateResponse fromJson(JsonObject json) {
    return UpdateResponse {
    };
  }

  shared object toCeylon extends Converter<UpdateResponse_, UpdateResponse>() {
    shared actual UpdateResponse convert(UpdateResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<UpdateResponse, UpdateResponse_>() {
    shared actual UpdateResponse_ convert(UpdateResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = UpdateResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(UpdateResponse obj) => obj.toJson();
}
