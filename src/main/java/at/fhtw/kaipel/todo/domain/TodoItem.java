package at.fhtw.kaipel.todo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table( name = "todo" )
@Entity( name = "todo" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "title", nullable = false )
    private String title;

    @Column( name = "description", nullable = true )
    private String description;

    @Column( name = "dueDate", nullable = false )
    private LocalDateTime dueDate;

    @Column( name = "completed", nullable = false )
    private Boolean completed = false;
}
