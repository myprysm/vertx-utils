# Myprysm Vert.x Utils

A set of utilities to fasten Vert.x application development

## Why

Vert.x is a powerful library, this is not a framework. It is not here to dictate your way of coding. 
This is good because you have to know the components you want to use in order to use them in the good way.

While this approach is really sane for application quality and performance (you know what you do, you start what you need),
you still have to write that boilerplate code to validate options here, extract some data from JSON there,
register your services and their proxies on the service discovery, binding web endpoints...

This set of utilities is here to save up some time by bringing some basic "features", "bundles" to achieve those
repetitive tasks more efficiently.

RxJava on top of that coordinates the heart for blazingly fast asynchronous data streams. 
Please note that while you're not required to use those Reactive Streams you're strongly encouraged to get a look
as once the step has been taken, code readability and stream coordination API will save you hours.

Lombok is also a cool tool when nicely integrated. You can write your Vert.x `DataObject` with it.
Just keep in mind that you will have to write your own setters to generate the automatic `AsciiDoc`.
See the dedicated paragraph.

**NOTE** that this is an opinionated way to build application with Vert.x, **but**

* You're grown enough to take your own decisions
* You know how annoying is to write/generate/arrange that boilerplate code 
    but you still want to somehow stick to Vert.x principles without introducing a bunch of libraries  

## Components

Check individual docs for more details.

* [vertx-parent](vertx-parent) provides some preconfigured dependencies and plugins ready for activation.
* [vertx-test](vertx-test) brings Vert.x + Junit5 along with helpers to assert async results quickly.
* [vertx-json](vertx-json) is all about manipulating JSON trees + some additions for Jackson that original Vert.x did not include.
* [vertx-validation](vertx-validation) is a micro-framework for Vert.x Json API that describes the chain through a simple DSL
using [combinator pattern](https://gtrefs.github.io/code/combinator-pattern/). 
* [vertx-retrofit](vertx-retrofit) brings the amazing [Retrofit](http://square.github.io/retrofit/) 
    with missing basic interceptors for common API Keys and Authorization headers
* [vertx-core](vertx-core) is for configuration and component startup. 
* [vertx-service](vertx-service) starts your services and perform all the registrations (service discovery, health checks)
* [vertx-web](vertx-web) starts your Endpoints (+Vert.x OpenAPI 3.0 support) and reduce the hassle of registering
    all the routes in a centralized class 
    
    
## Miscellaneous

Vert.x codegen support is enabled by default. All the languages but Scala (clash with one dependency) are generated.

### Lombok support

The following Lombok annotations are enough to limit the hassle of writing `DataObject`

```
@Data
@NoArgsConstructor
@AllArgsConstructor
// Not required if you create your own setters for the doc 
// or if you don't care about using a fluent API
@Accessors(chain = true)
@DataObject(generateConverter = true)
```
  
The remaining work is to declare your attributes and the minimum copy constructor + `JsonObject` constructor + `#toJson()`method

```
    // ......
    public WebVerticleOptions(WebVerticleOptions other) {
        useOpenAPI3Router = other.useOpenAPI3Router;
        specs = other.specs;
        enableCors = other.enableCors;
        cors = other.cors;
        enableMetrics = other.enableMetrics;
        enableHealthChecks = other.enableHealthChecks;
        monitoringPath = other.monitoringPath;
    }

    // JsonObject constructor using Vert.x generated converter
    public WebVerticleOptions(JsonObject json) {
        WebVerticleOptionsConverter.fromJson(json, this);
    }
    
    // toJson using Vert.x generated converter
    public JsonObject toJson() {
        JsonObject json = obj();
        WebVerticleOptionsConverter.toJson(this, json);
        return json;
    }
    // ......
```