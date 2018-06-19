import fr.myprysm.vertx.web {
    WebVerticleOptions_=WebVerticleOptions
}
import fr.myprysm.vertx.web.ceylon.myprysm.vertx.web {
    CorsOptions,
    corsOptions_=corsOptions
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
/* Generated from fr.myprysm.vertx.web.WebVerticleOptions */
shared class WebVerticleOptions(
        shared CorsOptions? cors = null,
        shared Boolean? enableCors = null,
        shared Boolean? enableHealthChecks = null,
        shared Boolean? enableMetrics = null,
        shared String? monitoringPath = null,
        shared String? specs = null,
        shared Boolean? useOpenAPI3Router = null) satisfies BaseDataObject {
    shared actual default JsonObject toJson() {
        value json = JsonObject();
        if (exists cors) {
            json.put("cors", cors.toJson());
        }
        if (exists enableCors) {
            json.put("enableCors", enableCors);
        }
        if (exists enableHealthChecks) {
            json.put("enableHealthChecks", enableHealthChecks);
        }
        if (exists enableMetrics) {
            json.put("enableMetrics", enableMetrics);
        }
        if (exists monitoringPath) {
            json.put("monitoringPath", monitoringPath);
        }
        if (exists specs) {
            json.put("specs", specs);
        }
        if (exists useOpenAPI3Router) {
            json.put("useOpenAPI3Router", useOpenAPI3Router);
        }
        return json;
    }
}

shared object webVerticleOptions {

    shared WebVerticleOptions fromJson(JsonObject json) {
        CorsOptions? cors = if (exists tmp = json.getObjectOrNull("cors")) then corsOptions_.fromJson(tmp) else null;
        Boolean? enableCors = json.getBooleanOrNull("enableCors");
        Boolean? enableHealthChecks = json.getBooleanOrNull("enableHealthChecks");
        Boolean? enableMetrics = json.getBooleanOrNull("enableMetrics");
        String? monitoringPath = json.getStringOrNull("monitoringPath");
        String? specs = json.getStringOrNull("specs");
        Boolean? useOpenAPI3Router = json.getBooleanOrNull("useOpenAPI3Router");
        return WebVerticleOptions {
            cors = cors;
            enableCors = enableCors;
            enableHealthChecks = enableHealthChecks;
            enableMetrics = enableMetrics;
            monitoringPath = monitoringPath;
            specs = specs;
            useOpenAPI3Router = useOpenAPI3Router;
        };
    }

    shared object toCeylon extends Converter<WebVerticleOptions_,WebVerticleOptions>() {
        shared actual WebVerticleOptions convert(WebVerticleOptions_ src) {
            value json = parse(src.toJson().string);
            assert (is JsonObject json);
            return fromJson(json);
        }
    }

    shared object toJava extends Converter<WebVerticleOptions,WebVerticleOptions_>() {
        shared actual WebVerticleOptions_ convert(WebVerticleOptions src) {
            // Todo : make optimized version without json
            value json = JsonObject_(src.toJson().string);
            value ret = WebVerticleOptions_(json);
            return ret;
        }
    }

    shared JsonObject toJson(WebVerticleOptions obj) => obj.toJson();
}
