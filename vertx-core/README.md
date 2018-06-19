# Myprysm Vert.x Core

(Micro)Core for a common way to start "main" verticles with Vert.x
and load configuration from file.

## Components

### Launcher

This `Launcher` is the default Vert.x `Launcher` + it binds RxJava2 schedulers to Vert.x.
As Reactive Streams are broadly used this allow to use the Event Loop as well as the workers
and avoid threading pollution within the application.

While you are not required to use this Launcher, you are strongly encouraged to start your 
application through this way if you expect to used RxJava2.

### StarterVerticle

Starts your verticles along with the `ConfigService`. 
Any failure during startup stops immediately the application.

It expects a `YAML` file as configuration with two sections [as described below](#configuration-file).
Configuration key is `path`.


### ConfigService

This service loads the `YAML` configuration file provided to the `StarterVerticle`.
It is responsible for merging provided environment/system properties with the values from the file.

It holds the verticles registry with all the configuration properties.

Please note that at the moment this service is not capable to merge array properties, this will come in later releases.
Property not found and type conversion issue are considered as errors and are emitted.

Any component in the application can refer to it by using a Vert.x `Service Proxy`
through the helper method `Utils#configServiceProxy(Vertx)`. 

### Configuration File

The configuration file is divided in two sections:
* verticles: describes the living components in your application
* configuration: provides the mandatory values for your components to work.

A verticle description is a named entry in the `verticle` object.
It needs a `verticle` class name with options. 
The options are none other than Vert.x `DeploymentOptions`, check the doc for more informations

Configuration is an optional entry in the file.

```
verticles:
  some-verticle:
    verticle: fr.myprysm.vertx.SomeVerticle
    options:
      instances: 3

  some-other-verticle:
    verticle: fr.myprysm.vertx.SomeOtherVerticle
    options:
      config:
        port:          4000
        otherProperty: some value

configuration:
  some:
    key: value
```

### ConfigTestHelper

This helper fastens the creation of a new ConfigService instance for test purpose.
It proposes to load alternate config files, bind on a different address.

When initialized, the service instance and the proxy consumer are provided.
