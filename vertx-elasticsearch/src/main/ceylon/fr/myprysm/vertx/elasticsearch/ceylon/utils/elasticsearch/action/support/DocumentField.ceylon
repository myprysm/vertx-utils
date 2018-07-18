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
  DocumentField_=DocumentField
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.support.DocumentField */
shared class DocumentField() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object documentField {

  shared DocumentField fromJson(JsonObject json) {
    return DocumentField {
    };
  }

  shared object toCeylon extends Converter<DocumentField_, DocumentField>() {
    shared actual DocumentField convert(DocumentField_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<DocumentField, DocumentField_>() {
    shared actual DocumentField_ convert(DocumentField src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = DocumentField_(json);
      return ret;
    }
  }
  shared JsonObject toJson(DocumentField obj) => obj.toJson();
}
