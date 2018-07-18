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
import fr.myprysm.vertx.elasticsearch.action.delete {
  DeleteResponse_=DeleteResponse
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.delete.DeleteResponse */
shared class DeleteResponse() extends DocWriteResponse() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object deleteResponse {

  shared DeleteResponse fromJson(JsonObject json) {
    return DeleteResponse {
    };
  }

  shared object toCeylon extends Converter<DeleteResponse_, DeleteResponse>() {
    shared actual DeleteResponse convert(DeleteResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<DeleteResponse, DeleteResponse_>() {
    shared actual DeleteResponse_ convert(DeleteResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = DeleteResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(DeleteResponse obj) => obj.toJson();
}
