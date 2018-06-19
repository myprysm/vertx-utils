import fr.myprysm.vertx.web {
    CorsOptions_=CorsOptions
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
import io.vertx.ceylon.core.http {
    HttpMethod,
    httpMethod_=httpMethod
}
import ceylon.collection {
    HashMap
}
import io.vertx.core.json {
    JsonObject_=JsonObject,
    JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.web.CorsOptions */
" See Vert.x documentation for Cross Origin Resource Sharing.\n"
shared class CorsOptions(
        shared Boolean? allowCredentials = null,
        shared {String*}? allowedHeaders = null,
        shared {HttpMethod*}? allowedMethods = null,
        shared String? allowedOrigins = null,
        shared {String*}? exposedHeaders = null,
        shared Integer? maxAgeSeconds = null) satisfies BaseDataObject {
    shared actual default JsonObject toJson() {
        value json = JsonObject();
        if (exists allowCredentials) {
            json.put("allowCredentials", allowCredentials);
        }
        if (exists allowedHeaders) {
            throw Exception("not yet implemented");
        }
        if (exists allowedMethods) {
            throw Exception("not yet implemented");
        }
        if (exists allowedOrigins) {
            json.put("allowedOrigins", allowedOrigins);
        }
        if (exists exposedHeaders) {
            throw Exception("not yet implemented");
        }
        if (exists maxAgeSeconds) {
            json.put("maxAgeSeconds", maxAgeSeconds);
        }
        return json;
    }
}

shared object corsOptions {

    shared CorsOptions fromJson(JsonObject json) {
        Boolean? allowCredentials = json.getBooleanOrNull("allowCredentials");
        {String*}? allowedHeaders = null /* java.lang.String not handled */;
        {HttpMethod*}? allowedMethods = null /* io.vertx.core.http.HttpMethod not handled */;
        String? allowedOrigins = json.getStringOrNull("allowedOrigins");
        {String*}? exposedHeaders = null /* java.lang.String not handled */;
        Integer? maxAgeSeconds = json.getIntegerOrNull("maxAgeSeconds");
        return CorsOptions {
            allowCredentials = allowCredentials;
            allowedHeaders = allowedHeaders;
            allowedMethods = allowedMethods;
            allowedOrigins = allowedOrigins;
            exposedHeaders = exposedHeaders;
            maxAgeSeconds = maxAgeSeconds;
        };
    }

    shared object toCeylon extends Converter<CorsOptions_,CorsOptions>() {
        shared actual CorsOptions convert(CorsOptions_ src) {
            value json = parse(src.toJson().string);
            assert (is JsonObject json);
            return fromJson(json);
        }
    }

    shared object toJava extends Converter<CorsOptions,CorsOptions_>() {
        shared actual CorsOptions_ convert(CorsOptions src) {
            // Todo : make optimized version without json
            value json = JsonObject_(src.toJson().string);
            value ret = CorsOptions_(json);
            return ret;
        }
    }

    shared JsonObject toJson(CorsOptions obj) => obj.toJson();
}
