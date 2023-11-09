package at.fhtw.kaipel.todo.rest.error;


import at.fhtw.kaipel.todo.rest.error.sub.ApiFieldError;
import at.fhtw.kaipel.todo.rest.error.sub.ApiSubError;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.ConstraintViolation;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * Represents errors during REST calls.
 * </pre>
 * <p>
 * Contains status, timestamp of the error, (debug) message and a list of {@link ApiSubError} that
 * represents the specific error with further information.
 */
@Data
public class ApiError implements Serializable {
    private int status;
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" )
    private OffsetDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;

    private ApiError() {
        timestamp = OffsetDateTime.now();
    }

    public ApiError( HttpStatus status ) {
        this();
        this.status = status.value();
    }

    public ApiError( HttpStatus status, String message ) {
        this();
        this.status = status.value();
        this.message = message;
    }

    public ApiError( HttpStatus status, Throwable ex ) {
        this();
        this.status = status.value();
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiError( HttpStatus status, String message, Throwable ex ) {
        this();
        this.status = status.value();
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public void addSubError( ApiSubError subError ) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add( subError );
    }

    public void addConstraintsViolationError( Set<ConstraintViolation<?>> constraintViolations ) {
        constraintViolations.forEach( this::addValidationError );
    }

    /**
     * Utility method for adding error of ConstraintViolation. Usually when a @Validated validation fails.
     *
     * @param cv the ConstraintViolation
     */
    private void addValidationError( ConstraintViolation<?> cv ) {
        this.addValidationError(
            cv.getRootBeanClass().getSimpleName(),
            cv.getPropertyPath().toString(),
            cv.getInvalidValue(),
            cv.getMessage() );
    }

    // #######################################################################
    // Validation
    // #######################################################################

    public void addConstraintsViolationError( List<FieldError> fieldErrors ) {
        fieldErrors.forEach( this::addValidationError );
    }

    public void addValidationError( List<ObjectError> globalErrors ) {
        globalErrors.forEach( this::addValidationError );
    }

    private void addValidationError( ObjectError objectError ) {
        this.addValidationError(
            objectError.getObjectName(),
            objectError.getDefaultMessage() );
    }

    private void addValidationError( String object, String field, Object rejectedValue, String message ) {
        addSubError( new ApiFieldError( object, field, rejectedValue, message ) );
    }

    private void addValidationError( String object, String message ) {
        addSubError( new ApiFieldError( object, message ) );
    }

    private void addValidationError( FieldError fieldError ) {
        this.addValidationError(
            fieldError.getObjectName(),
            fieldError.getField(),
            fieldError.getRejectedValue(),
            fieldError.getDefaultMessage() );
    }
}
