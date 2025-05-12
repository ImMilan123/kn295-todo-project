package ch.jevgenijevic.milan.todo.todo_project.service;

import ch.jevgenijevic.milan.todo.todo_project.model.Todo;
import ch.jevgenijevic.milan.todo.todo_project.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

    public Todo update(Long id, Todo updated) {
        return todoRepository.findById(id).map(todo -> {
            todo.setTitle(updated.getTitle());
            todo.setCompleted(updated.isCompleted());
            return todoRepository.save(todo);
        }).orElseThrow(() -> new RuntimeException("Todo not found"));
    }
}
