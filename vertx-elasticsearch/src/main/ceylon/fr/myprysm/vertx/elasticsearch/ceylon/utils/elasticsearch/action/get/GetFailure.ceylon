import fr.myprysm.vertx.elasticsearch.action.get {
  GetFailure_=GetFailure
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
  Failure
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.get.GetFailure */
shared class GetFailure() extends Failure() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object getFailure {

  shared GetFailure fromJson(JsonObject json) {
    return GetFailure {
    };
  }

  shared object toCeylon extends Converter<GetFailure_, GetFailure>() {
    shared actual GetFailure convert(GetFailure_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<GetFailure, GetFailure_>() {
    shared actual GetFailure_ convert(GetFailure src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = GetFailure_(json);
      return ret;
    }
  }
  shared JsonObject toJson(GetFailure obj) => obj.toJson();
}
