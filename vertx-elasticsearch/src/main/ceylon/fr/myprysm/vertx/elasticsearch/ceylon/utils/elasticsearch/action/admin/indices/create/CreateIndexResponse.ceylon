import fr.myprysm.vertx.elasticsearch.action.admin.indices.create {
  CreateIndexResponse_=CreateIndexResponse
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexResponse */
shared class CreateIndexResponse(
  Boolean? acknowledged = null,
  shared String? index = null,
  shared Boolean? shardsAcknowledged = null) extends AcknowledgedResponse(
  acknowledged) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists index) {
      json.put("index", index);
    }
    if (exists shardsAcknowledged) {
      json.put("shardsAcknowledged", shardsAcknowledged);
    }
    return json;
  }
}

shared object createIndexResponse {

  shared CreateIndexResponse fromJson(JsonObject json) {
    Boolean? acknowledged = json.getBooleanOrNull("acknowledged");
    String? index = json.getStringOrNull("index");
    Boolean? shardsAcknowledged = json.getBooleanOrNull("shardsAcknowledged");
    return CreateIndexResponse {
      acknowledged = acknowledged;
      index = index;
      shardsAcknowledged = shardsAcknowledged;
    };
  }

  shared object toCeylon extends Converter<CreateIndexResponse_, CreateIndexResponse>() {
    shared actual CreateIndexResponse convert(CreateIndexResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<CreateIndexResponse, CreateIndexResponse_>() {
    shared actual CreateIndexResponse_ convert(CreateIndexResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = CreateIndexResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(CreateIndexResponse obj) => obj.toJson();
}
