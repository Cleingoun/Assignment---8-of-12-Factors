package at.fhtw.kaipel.todo.rest.error.sub;


import at.fhtw.kaipel.todo.rest.error.ApiError;

import java.io.Serializable;

/**
 * {@link ApiError} holds sub errors that specify the problem.
 * Multiple SubErrors can occur during one REST call.
 */
public interface ApiSubError extends Serializable {
    String getMessage();
}
