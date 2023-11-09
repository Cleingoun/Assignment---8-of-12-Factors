package at.fhtw.kaipel.todo.rest.exception;

/**
 * Stacktrace creation is costly. This implementation disables it. Has to be derived from RuntimeException, otherwise
 * IDE will not pick up exception handling with {@link org.springframework.web.bind.annotation.ControllerAdvice}
 * <p>
 * {@link at.fhtw.kaipel.todo.rest.error.ApiError} is intended as a clearer replacement, which also provides
 * api users with clearer error structures and messages.
 *
 * @see <a href="https://www.baeldung.com/java-exceptions-performance">Performance Effects of Exceptions in Java</a>
 */
public class NoStacktraceException extends RuntimeException {

    public NoStacktraceException() {
    }

    public NoStacktraceException( String message ) {
        this( message, null, false );
    }

    public NoStacktraceException( String message, Throwable cause ) {
        this( message, cause, false );
    }

    public NoStacktraceException( String message, Throwable cause, boolean enableSuppression ) {
        super( message, cause, enableSuppression, false );
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
