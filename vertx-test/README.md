# Myprysm Vert.x Test

This is a small set of reusable components that helps to improve velocity in writting
unit tests with Vert.x.

They are intended to be non-obtrusive while bringing some "cool" features or helpers.

They are based on `Junit 5`

## Usage

Add it as a maven dependency to your project along with vertx-junit5 and Junit5:
```
<dependency>
    <groupId>fr.myprysm.vertx</groupId>
    <artifactId>vertx-test</artifactId>
    <version>0.1.4-SNAPSHOT</version>
    <scope>test</scope>
</dependency>
```

## VertxTest

Interface that brings automatically the `Junit 5` `VertxExtension` as well as some class methods
to read resources from classpath (String, JsonObject, JsonArray, absolute path resolution).

## QuickAssert

Interface for quick assertion (sic) on async results with Vert.x. 
Wraps calls automatically in `VertxTestContext#verify(Runnable)` so that you have not to worry anymore about test timeouts. 