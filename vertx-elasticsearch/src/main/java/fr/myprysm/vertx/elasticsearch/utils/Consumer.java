package fr.myprysm.vertx.elasticsearch.utils;

/**
 * Consumer that accepts one argument as parameter.
 * <p>
 * Can throw any Exception.
 *
 * @param <In> first type argument
 */
@FunctionalInterface
public interface Consumer<In> {

    /**
     * Consumes the argument.
     *
     * @param item the argument
     * @throws Exception any kind of exception
     */
    void consume(In item) throws Exception;
}
