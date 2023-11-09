package at.fhtw.kaipel.todo.repository;

import at.fhtw.kaipel.todo.domain.TodoItem;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TodoItemRepository  extends JpaRepository<TodoItem, Long>{
}
