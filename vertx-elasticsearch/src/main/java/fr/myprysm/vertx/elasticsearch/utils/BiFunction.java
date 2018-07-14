package fr.myprysm.vertx.elasticsearch.utils;

/**
 * Function that applies on both input items to produce an output item.
 * <p>
 * Can throw any Exception.
 *
 * @param <In1> first input type argument
 * @param <In2> second input type argument
 * @param <Out> output type argument
 */
@FunctionalInterface
public interface BiFunction<In1, In2, Out> {
    /**
     * Apply on both input arguments to output a result.
     *
     * @param input1 first input argument
     * @param input2 second input argument
     * @return output object
     * @throws Exception any kind of exception
     */
    Out apply(In1 input1, In2 input2) throws Exception;
}
