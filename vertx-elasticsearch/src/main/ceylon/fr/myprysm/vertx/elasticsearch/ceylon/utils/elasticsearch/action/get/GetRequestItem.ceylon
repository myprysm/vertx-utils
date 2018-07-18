import fr.myprysm.vertx.elasticsearch.action.get {
  GetRequestItem_=GetRequestItem
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
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.get.GetRequestItem */
shared class GetRequestItem() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object getRequestItem {

  shared GetRequestItem fromJson(JsonObject json) {
    return GetRequestItem {
    };
  }

  shared object toCeylon extends Converter<GetRequestItem_, GetRequestItem>() {
    shared actual GetRequestItem convert(GetRequestItem_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<GetRequestItem, GetRequestItem_>() {
    shared actual GetRequestItem_ convert(GetRequestItem src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = GetRequestItem_(json);
      return ret;
    }
  }
  shared JsonObject toJson(GetRequestItem obj) => obj.toJson();
}
