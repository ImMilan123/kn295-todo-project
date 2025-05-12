package ch.jevgenijevic.milan.todo.todo_project.controller;

import ch.jevgenijevic.milan.todo.todo_project.model.Todo;
import ch.jevgenijevic.milan.todo.todo_project.service.TodoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "ToDo Management")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<Todo> getAll() {
        return todoService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Todo> create(@RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.save(todo));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.update(id, todo));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
