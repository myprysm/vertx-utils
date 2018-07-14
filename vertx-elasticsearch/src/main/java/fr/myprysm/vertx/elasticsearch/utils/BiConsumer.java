package fr.myprysm.vertx.elasticsearch.utils;

/**
 * Consumer that accepts two arguments as parameters
 * <p>
 * Can throw any Exception.
 *
 * @param <In1> first type argument
 * @param <In2> second type argument
 */
@FunctionalInterface
public interface BiConsumer<In1, In2> {
    /**
     * Consumes the arguments.
     *
     * @param first  first argument
     * @param second second argument
     * @throws Exception any kind of exception
     */
    void consume(In1 first, In2 second) throws Exception;
}
