package fr.myprysm.vertx.elasticsearch.utils;

/**
 * Consumer that accepts three arguments as parameters
 * <p>
 * Can throw any Exception.
 *
 * @param <In1> first type argument
 * @param <In2> second type argument
 * @param <In3> third type argument
 */
@FunctionalInterface
public interface TriConsumer<In1, In2, In3> {
    /**
     * Consumes the arguments.
     *
     * @param first  first argument
     * @param second second argument
     * @param third  third argument
     * @throws Exception any kind of exception
     */
    void consume(In1 first, In2 second, In3 third) throws Exception;
}
