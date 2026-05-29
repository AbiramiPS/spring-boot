package spring.learning.service;

import spring.learning.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import javax.print.attribute.standard.PageRanges;
import java.lang.reflect.Field;
import org.springframework.util.ReflectionUtils;
import spring.learning.repository.TodoRepository;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    public Todo updateTodo(Long id, Todo todo) {
        todo.setId(id);
        return todoRepository.save(todo);
    }

    public List<Todo> bulkComplete(List<Long> ids) {
        List<Todo> todos = todoRepository.findAllById(ids);
        for (Todo todo : todos) {
            todo.setIsCompleted(true);
        }
        return todoRepository.saveAll(todos);
    }

    public Todo patchTodo(Long id, Map<String, Object> updates) {
        Todo todo = getTodoById(id);
        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Todo.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, todo, value);
            }
        });

        return todoRepository.save(todo);
    }

    public void deleteTodo(Todo todo) {
        todoRepository.delete(todo);
    }

    public void deleteTodoById(Long id) {
        todoRepository.delete(getTodoById(id));

    }

    // Pagination
    public Page<Todo> getAllPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return todoRepository.findAll(pageable);
    }

}
