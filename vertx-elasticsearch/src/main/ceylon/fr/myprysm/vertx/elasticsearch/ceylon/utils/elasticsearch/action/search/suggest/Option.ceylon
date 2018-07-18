import fr.myprysm.vertx.elasticsearch.action.search.suggest {
  Option_=Option
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.suggest.Option */
shared class Option() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object option {

  shared Option fromJson(JsonObject json) {
    return Option {
    };
  }

  shared object toCeylon extends Converter<Option_, Option>() {
    shared actual Option convert(Option_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }
}
