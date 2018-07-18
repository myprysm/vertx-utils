import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete {
  DeleteIndexResponse_=DeleteIndexResponse
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
  AcknowledgedResponse
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexResponse */
" DeleteIndexResponse.\n"
shared class DeleteIndexResponse() extends AcknowledgedResponse() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object deleteIndexResponse {

  shared DeleteIndexResponse fromJson(JsonObject json) {
    return DeleteIndexResponse {
    };
  }

  shared object toCeylon extends Converter<DeleteIndexResponse_, DeleteIndexResponse>() {
    shared actual DeleteIndexResponse convert(DeleteIndexResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<DeleteIndexResponse, DeleteIndexResponse_>() {
    shared actual DeleteIndexResponse_ convert(DeleteIndexResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = DeleteIndexResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(DeleteIndexResponse obj) => obj.toJson();
}
