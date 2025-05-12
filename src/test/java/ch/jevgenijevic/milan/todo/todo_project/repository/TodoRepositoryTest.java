package ch.jevgenijevic.milan.todo.todo_project.repository;

import ch.jevgenijevic.milan.todo.todo_project.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void testSaveAndFind() {
        Todo todo = new Todo("Write tests", false);
        todoRepository.save(todo);

        List<Todo> todos = todoRepository.findAll();

        assertThat(todos).isNotEmpty();
        assertThat(todos.get(0).getTitle()).isEqualTo("Write tests");
    }
}
