package at.fhtw.kaipel.todo.mapper;

import at.fhtw.kaipel.todo.domain.TodoItem;
import at.fhtw.kaipel.todo.dto.TodoItemDTO;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
        //uses = {} 'uses' is used for when you have other Mappers that use this mapper.
)
public interface TodoMapper extends EntityMapper<TodoItemDTO, TodoItem> {
}