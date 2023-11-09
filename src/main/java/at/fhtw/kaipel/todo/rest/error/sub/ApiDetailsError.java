package at.fhtw.kaipel.todo.rest.error.sub;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode( callSuper = false )
@AllArgsConstructor
public class ApiDetailsError  implements ApiSubError {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }
}
