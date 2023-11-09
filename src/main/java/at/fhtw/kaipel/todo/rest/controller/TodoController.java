package at.fhtw.kaipel.todo.rest.controller;

import at.fhtw.kaipel.todo.dto.TodoItemDTO;
import at.fhtw.kaipel.todo.rest.error.ApiError;
import at.fhtw.kaipel.todo.rest.exception.UpdateMismatchException;
import at.fhtw.kaipel.todo.rest.service.TodoItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.function.Predicate.not;

@RestController
@RequestMapping("/api/v1")
public class TodoController {

    private final TodoItemService testService;

    public TodoController(TodoItemService testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/todos")
    @Operation(summary = "Get all Todos", responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TodoItemDTO.class)))),
            @ApiResponse(description = "No Todos Found", responseCode = "404", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<List<TodoItemDTO>> getTodos() {
        return Optional.ofNullable(testService.findAll())
                .filter(not(List::isEmpty))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/todos/{id}", produces = "application/json")
    @Operation(summary = "Get a specific Todo", responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TodoItemDTO.class))),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<TodoItemDTO> getTodoById(@NotNull @PathVariable Long id) {
        return ResponseEntity.of(testService.findById(id));
    }

    @PostMapping(value = "/todos", produces = "application/json")
    @Operation(summary = "Create a new Todo", responses = {
            @ApiResponse(description = "Created", responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TodoItemDTO.class)))
    })
    public ResponseEntity<TodoItemDTO> createTodo(@RequestBody TodoItemDTO todoItemDTO) {
        TodoItemDTO newTodo = testService.create(todoItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }


    @PutMapping(value = "/todos/{id}", produces = "application/json")
    @Operation(summary = "Update a specific Todo", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json")),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Object> updateTodoById(@PathVariable Long id, @Valid @RequestBody TodoItemDTO todoItemDTO) {
        if (!Objects.equals(id, todoItemDTO.getId())) {
            throw new UpdateMismatchException(id.toString(), todoItemDTO.getId().toString());
        }
        testService.update(id, todoItemDTO);
        return ResponseEntity.ok().build();
    }



    @DeleteMapping(value = "/todos/{id}", produces = "application/json")
    @Operation(summary = "Delete a specific Todo", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json")),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Object> deleteTodoById(@PathVariable long id) {
        testService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}