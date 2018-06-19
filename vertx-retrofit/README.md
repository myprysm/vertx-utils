# Myprysm Vert.x Retrofit

Brings support for the amazing [Retrofit](http://square.github.io/retrofit/) with Vert.x.

* Binds Jackson `JsonObject` & `JsonArray` deserializers (Vert.x does not do it by default)
* Provides a `ServiceFactory` with a `Builder` that can create new Retrofit services 
    with an API Key interceptor or an Authorization header interceptor