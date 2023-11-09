package at.fhtw.kaipel.todo.rest.exception;

import lombok.Getter;

/**
 * Exception when resource and update id do not match =>  ambivalent instructions, which should be updated?
 * Might also be a user error
 */
@Getter
public final class UpdateMismatchException extends NoStacktraceException {
    private final String entityId;
    private final String resourceId;

    public UpdateMismatchException( String entityId, String resourceId ) {
        super( "Entity id does not match with resource id", null, false );
        this.entityId = entityId;
        this.resourceId = resourceId;
    }
}
