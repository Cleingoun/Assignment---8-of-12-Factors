package at.fhtw.kaipel.todo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemDTO {

    private Long id;

    @NotEmpty(message = "The Title for an Todo must be set")
    @Size(min = 1, max = 100)
    private String title;

    @Size(max = 1000)
    private String description;

    private LocalDateTime dueDate;

    private Boolean completed = false;

}
