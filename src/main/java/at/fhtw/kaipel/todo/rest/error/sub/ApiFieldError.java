package at.fhtw.kaipel.todo.rest.error.sub;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Expresses validation problems during rest calls
 */
@Data
@EqualsAndHashCode( callSuper = false )
@AllArgsConstructor
public class ApiFieldError implements ApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public ApiFieldError( String object, String message ) {
        this.object = object;
        this.message = message;
    }
}