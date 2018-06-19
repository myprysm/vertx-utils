# Myprysm Vert.x Web

Set of helpers to quickly bind endpoints to a Vert.x HTTP Server through a Router.

WebVerticle will lookup the classpath to find `Endpoint` (and `OpenAPI3Endpoint`) implementations and register/configure them.

Note that this is an experimental module at the moment, breaking changes can occur at every release.
The project will follow [SEMVER](https://semver.org/).

## Endpoint

An `Endpoint` is a bit like a [Service](../vertx-service/README.md), a lightweight component, 
automatically instanciated during `WebVerticle` startup. 

It expects to have a public default constructor and should be automatically initialized through
the `AbstractEndpoint` class (or directly implement `Endpoint` for some \[unreasonable\] reason...).

`AbstractEndpoint` (as well as `AbstractOpenAPIEndpoint`) can quickly `#setContentJson` on a request.

## Open API 3.0 Support

You can enable the Open API 3.0 support by toggling the flag `useOpenAPIRouter` to `true` 
and provide a valid `specs` file (can be retrieved from remote URL, see Vert.x Web API Contract documentation).

Operations **must** extend `AbstractOpenAPIEndpoint`, the operation will be automatically bound to the
appropriate spec.

It will also expose the following route:

* `/swagger.json` the service specs

## Cross Origin Resource Sharing

Vert.x Cross Origin Resource Sharing options are fully supported with the `WebVerticle` options.
Toggle the flag `enableCors` along with your custom `cors` options.


## Monitoring path

You can customize the default monitoring path (`/__`) by providing a valid `monitoringPath`.
This will affect the routes described below.

## Metrics

Metrics are disabled by default. You can enable them with the flag `enableMetrics`. 
This will instanciate a metrics handler that will register all the request path and measure the throughput
with the Vert.x Dropwizard `ThroughputTimer`.

Following paths are exposed:

* `/__/metrics/vertx` all the metrics exposed by vertx
* `/__/metrics/requests` [ThroughputTimer](https://vertx.io/docs/vertx-dropwizard-metrics/java/#throughput_timer) per endpoint
* `/__/metrics/hystrix` SSE stream for Hystrix Dashboard

This will expose also the monitoring endpoint.

## Health Checks

This will enable the endpoint for health checks configured with [vertx-service](../vertx-service/README.md).

The following route will be exposed:

* `/__/hc` service healthcheck

This will expose also the monitoring endpoint.

### Miscellaneous

No ordering is currently supported for `Endpoint` registration. 
This will come in next releases.

No support currently for security handlers on OpenAPI 3.0. Next releases...