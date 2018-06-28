# Myprysm Vert.x Service

Start your services and perform your registrations automatically.

At the center, the `ServiceVerticle`. The satellites: your services.

Provides:
* Common way to register named services
* Service Discovery (un)registration
* HealthCheck registration

## Components

### Service

A service is an implementation of `Service` interface, fastened with `AbstractService` class.
It provides one `configure` hook during startup, and one `close` hook during teardown.

When you configure a service you should provide at least:

* `address`: the address on which the `ServiceBinder` will register the Vert.x `ServiceProxyHandler`.
* `facade`: this is the `interface` describing your service, as shown in [Vert.x Service Proxy documentation](https://vertx.io/docs/vertx-service-proxy/java/)

You should also provide either a `factoryMethod` or an `implementation` class in order to build the service.

`implementation` must extend `AbstractService` (or directly implement `Service` for some \[unreasonable\] reason...)
and provide a public default constructor.

When the `discovery` flag is set to `false`, then the service is not registered on Vert.x Service Discovery.
Please note that the address is still registered, 
meaning that the service can still be accessible from other nodes in cluster mode.
Use an alternate address for those services that should stay local to the service instance.

When the `registration` flag is set to `false`, then `discovery` is automatically disabled.

### HealthCheck

Service status is critical, even more in a distributed infrastructure.
`ServiceVerticle` provides the ability to bind a `HealthCheck` to a `Service` 
with options for a custom name and a custom timeout.

`HealthCheck` implementations must provide a default `name` and a `#beat()`.
The beat will return a Vert.x `Handler` that will(/can) respond asynchronously to the status.

You have to take care by yourself that the `Future` that will be handled is not yet already completed
if you want to keep a proper log trace.

## Configuration

Service description are passed as named entries in the services configuration map:

```
verticles:
  service:
    verticle: fr.myprysm.vertx.service.ServiceVerticle
    options:
      config:
        services:
          # Service name is used for Service Discovery registration
          service-1:
            address:        my.service.package:service-1
            facade:         fr.myprysm.vertx.service.SimpleService
            implementation: fr.myprysm.vertx.service.SimpleSuccessServiceImpl
            
          # This service is not registered automatically with a proxy
          # This allows different purposes for services as Vert.x is injected
          # likely for batches or other technical/background tasks
          unregistered-service:
            facade:         fr.myprysm.vertx.service.SimpleService
            implementation: fr.myprysm.vertx.service.SimpleSuccessServiceImpl
            register:       false
            healthCheck:    false
            
          # This service is not registered on Vert.x Service Discovery while a proxy is still created
          service-2:
            address:        my.service.package:service-2
            facade:         fr.myprysm.vertx.service.SimpleService
            implementation: fr.myprysm.vertx.service.SimpleSuccessServiceImpl
            discovery:      false
```  