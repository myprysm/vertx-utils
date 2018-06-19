# Myprysm Vert.x Validation

Micro-framework for Vert.x `JsonObject` validation.

Based on the [combinator pattern](https://gtrefs.github.io/code/combinator-pattern/) to bring a fluent API/DSL
to describe a validation chain.

## Principles

Each validation is a pair of a Predicate and a reason (error message). 
It can be combined with other validations using operators like `and` and `or`.
Once the validation chain has been built, it can be applied on any `JsonObject` to produce a `ValidationResult`. 

## Components

### ValidationResult
This is an immutable object that contains a `boolean` state indicating whether is is valid 
and an `Optional` reason when item is invalid.
They can be lazily combined with usage of operators `and` and `or` through a `Supplier`.
The first error will break the chain.

### JsonValidation

Bunch of utilities to validate that a field:
* is (not) null,
* is from a specific enum
* is a number (within range)
* is a list of a Primitive (+ JsonObject & JsonArray) 
* is a Map (JsonObject) of Primitives (+ JsonObject & JsonArray)
* has some path

Also provides the same kind of combinations than `ValidationResult`. See doc/code for more details.

This does not allow to validate a field within a tree at the moment.

You can still validate nested objects as states the below example with `JsonValidation#nested(String, JsonValidation)`. 

## Example

```
/**
 * Validates the input json is a valid WebVerticleOption.
 *
 * @param json the input json
 * @return the result
 */
public static ValidationResult validate(JsonObject json) {
    return validateOpenAPI()
            .and(WebVerticleOptions::validateCorsOptions)
            .and(isNull("enableMetrics").or(isBoolean("enableMetrics")))
            .and(isNull("enableHealthChecks").or(isBoolean("enableHealthChecks")))
            .and(isNull("monitoringPath").or(matches("monitoringPath", "/.[a-zA-Z0-9\\-\\.\\+_/]+", PATH_MESSAGE)))
            .apply(json);
}


/**
 * Validates Cross Origin Resource Sharing.
 *
 * @return the chain
 */
private static JsonValidation validateCorsOptions() {
    return isNull("enableCors").or(isBoolean("enableCors", false))
            // Combine sub-validators
            .or(nested("cors", CorsOptions::validate));
}

/**
 * Validates OpenAPI 3 configuration.
 * <p>
 * Whenever it is activated it must provide specs file.
 *
 * @return the chain
 */
private static JsonValidation validateOpenAPI() {
    return isNull("useOpenAPI3Router").or(isBoolean("useOpenAPI3Router", false))
            .or(isBoolean("useOpenAPI3Router", true).and(isString("specs", "Specs url should be provided with OpenAPI 3.0 support")));
}
```


This validation chain is actually used in [vertx-web](../vertx-web) to validate the `WebVerticle` options.

## Caveats

While this micro-framework is very performant and pleasant to use