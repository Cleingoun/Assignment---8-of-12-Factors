package at.fhtw.kaipel.todo.rest.service;

import at.fhtw.kaipel.todo.domain.TodoItem;
import at.fhtw.kaipel.todo.dto.TodoItemDTO;
import at.fhtw.kaipel.todo.mapper.TodoMapper;
import at.fhtw.kaipel.todo.repository.TodoItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {

    private final TodoItemRepository todoItemRepository;

    private final TodoMapper todoMapper;

    public TodoItemService(TodoItemRepository todoItemRepository, TodoMapper todoMapper) {
        this.todoItemRepository = todoItemRepository;
        this.todoMapper = todoMapper;
    }

    public List<TodoItemDTO> findAll() {
        return todoMapper.toDTOList(todoItemRepository.findAll());
    }

    public Optional<TodoItemDTO> findById(Long id) {
        return todoItemRepository.findById(id)
                .map(todoMapper::toDTO);
    }

    public TodoItemDTO create(TodoItemDTO todoItemDTO) {
        TodoItem todoItem = todoMapper.toEntity(todoItemDTO);
        TodoItem savedTodoItem = todoItemRepository.save(todoItem);
        return todoMapper.toDTO(savedTodoItem);
    }

    public void update(Long id, TodoItemDTO todoItemDTO) {
        TodoItem todoItem = todoMapper.toEntity(todoItemDTO);
        if (!todoItemRepository.existsById(id)) {
            throw new EntityNotFoundException("TodoItem not found with id " + id);
        }
        todoItem.setId(id); // Ensure the id is set to the entity to be updated
        todoItemRepository.save(todoItem);
    }

    public void deleteById(Long id) {
        if (!todoItemRepository.existsById(id)) {
            throw new EntityNotFoundException("TodoItem not found with id " + id);
        }
        todoItemRepository.deleteById(id);
    }
}
