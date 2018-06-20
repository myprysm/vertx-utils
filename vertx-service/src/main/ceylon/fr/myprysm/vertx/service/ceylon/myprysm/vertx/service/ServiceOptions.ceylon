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
import fr.myprysm.vertx.service {
  ServiceOptions_=ServiceOptions
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.service.ServiceOptions */
shared class ServiceOptions(
  " The event bus address on which the service will be registered.\n <p>\n Must not be blank or null.\n"
  shared String? address = null,
  " Indicates whether the service will be registered on Vert.x Service Discovery.\n <p>\n Please note that this behaviour is enabled by default.\n You have\n"
  shared Boolean? discovery = null,
  " The Service facade/interface name.\n"
  shared String? facade = null,
  " The factory method.\n <p>\n This should the name of a static method available on the <code>facade</code> class,\n accepting as arguments <code>Vertx</code> and a <code>Handler</code>\n that will receive the configured service.\n"
  shared String? factoryMethod = null,
  " Indicates whether Health Check is enabled for the service.\n <p>\n This does not apply to classic Vert.x services instanciated with the <code>factoryMethod</code>.\n This does not apply to a <code>Service</code> that does not implement <code>HealthCheck</code> interface.\n"
  shared Boolean? healthCheck = null,
  " Custom health check name.\n <p>\n It allows to change the default name configured for a <code>Service</code> health check.\n <p>\n It is automatically bound to Vert.x Health Check so name can contain \"/\" to group\n results in a single <code>JsonObject</code>\n"
  shared String? healthCheckName = null,
  " Custom health check timeout.\n <p>\n Must be a positive value, representing a duration in milliseconds.\n Default is 1 second (1000 milliseconds).\n"
  shared Integer? healthCheckTimeout = null,
  " The Service implementation name.\n <p>\n The class must implement the provided <code>facade</code>.\n"
  shared String? implementation = null,
  " The name of the service for service discovery.\n"
  shared String? name = null,
  " Indicates whether the service will be registered.\n <p>\n Please note that this behaviour is enabled by default.\n You have to disable it through configuration.\n <p>\n This disables also <code>discovery</code>.\n"
  shared Boolean? register = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists address) {
      json.put("address", address);
    }
    if (exists discovery) {
      json.put("discovery", discovery);
    }
    if (exists facade) {
      json.put("facade", facade);
    }
    if (exists factoryMethod) {
      json.put("factoryMethod", factoryMethod);
    }
    if (exists healthCheck) {
      json.put("healthCheck", healthCheck);
    }
    if (exists healthCheckName) {
      json.put("healthCheckName", healthCheckName);
    }
    if (exists healthCheckTimeout) {
      json.put("healthCheckTimeout", healthCheckTimeout);
    }
    if (exists implementation) {
      json.put("implementation", implementation);
    }
    if (exists name) {
      json.put("name", name);
    }
    if (exists register) {
      json.put("register", register);
    }
    return json;
  }
}

shared object serviceOptions {

  shared ServiceOptions fromJson(JsonObject json) {
    String? address = json.getStringOrNull("address");
    Boolean? discovery = json.getBooleanOrNull("discovery");
    String? facade = json.getStringOrNull("facade");
    String? factoryMethod = json.getStringOrNull("factoryMethod");
    Boolean? healthCheck = json.getBooleanOrNull("healthCheck");
    String? healthCheckName = json.getStringOrNull("healthCheckName");
    Integer? healthCheckTimeout = json.getIntegerOrNull("healthCheckTimeout");
    String? implementation = json.getStringOrNull("implementation");
    String? name = json.getStringOrNull("name");
    Boolean? register = json.getBooleanOrNull("register");
    return ServiceOptions {
      address = address;
      discovery = discovery;
      facade = facade;
      factoryMethod = factoryMethod;
      healthCheck = healthCheck;
      healthCheckName = healthCheckName;
      healthCheckTimeout = healthCheckTimeout;
      implementation = implementation;
      name = name;
      register = register;
    };
  }

  shared object toCeylon extends Converter<ServiceOptions_, ServiceOptions>() {
    shared actual ServiceOptions convert(ServiceOptions_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<ServiceOptions, ServiceOptions_>() {
    shared actual ServiceOptions_ convert(ServiceOptions src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = ServiceOptions_(json);
      return ret;
    }
  }
  shared JsonObject toJson(ServiceOptions obj) => obj.toJson();
}
