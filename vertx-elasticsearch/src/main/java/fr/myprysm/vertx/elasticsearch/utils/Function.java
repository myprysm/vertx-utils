package fr.myprysm.vertx.elasticsearch.utils;

/**
 * Function that applies on input item to produce an output item.
 * <p>
 * Can throw any Exception.
 *
 * @param <In>  input type argument
 * @param <Out> output type argument
 */
@FunctionalInterface
public interface Function<In, Out> {
    Out apply(In input) throws Exception;
}
