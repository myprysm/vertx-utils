import io.vertx.ceylon.core {
    DeploymentOptions,
    deploymentOptions_=deploymentOptions
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
import fr.myprysm.vertx.core {
    VerticleOptions_=VerticleOptions
}
import ceylon.collection {
    HashMap
}
import io.vertx.core.json {
    JsonObject_=JsonObject,
    JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.core.VerticleOptions */
shared class VerticleOptions(
        shared String? name = null,
        " Sets the verticle options.\n <p>\n This expects a Vert.x <code>DeploymentOptions</code> JSON object\n"
        shared DeploymentOptions? options = null,
        shared String? verticle = null) satisfies BaseDataObject {
    shared actual default JsonObject toJson() {
        value json = JsonObject();
        if (exists name) {
            json.put("name", name);
        }
        if (exists options) {
            json.put("options", options.toJson());
        }
        if (exists verticle) {
            json.put("verticle", verticle);
        }
        return json;
    }
}

shared object verticleOptions {

    shared VerticleOptions fromJson(JsonObject json) {
        String? name = json.getStringOrNull("name");
        DeploymentOptions? options = if (exists tmp = json.getObjectOrNull("options")) then deploymentOptions_.fromJson(tmp) else null;
        String? verticle = json.getStringOrNull("verticle");
        return VerticleOptions {
            name = name;
            options = options;
            verticle = verticle;
        };
    }

    shared object toCeylon extends Converter<VerticleOptions_,VerticleOptions>() {
        shared actual VerticleOptions convert(VerticleOptions_ src) {
            value json = parse(src.toJson().string);
            assert (is JsonObject json);
            return fromJson(json);
        }
    }

    shared object toJava extends Converter<VerticleOptions,VerticleOptions_>() {
        shared actual VerticleOptions_ convert(VerticleOptions src) {
            // Todo : make optimized version without json
            value json = JsonObject_(src.toJson().string);
            value ret = VerticleOptions_(json);
            return ret;
        }
    }

    shared JsonObject toJson(VerticleOptions obj) => obj.toJson();
}
