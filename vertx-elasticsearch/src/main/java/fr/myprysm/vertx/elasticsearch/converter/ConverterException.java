package fr.myprysm.vertx.elasticsearch.converter;

/**
 * Exception thrown during {@link Converter} conversion.
 */
public class ConverterException extends RuntimeException {

    /**
     * Build a new exception with the message and the parent cause.
     *
     * @param message the message
     * @param cause   the parent cause
     */
    public ConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Build a new exception from the parent cause.
     *
     * @param cause the parent cause
     */
    public ConverterException(Throwable cause) {
        super(cause);
    }

    /**
     * Build a new exception with the message.
     *
     * @param message the message
     */
    public ConverterException(String message) {
        super(message);
    }
}
