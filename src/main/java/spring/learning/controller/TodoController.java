package spring.learning.controller;

import spring.learning.models.Todo;
import spring.learning.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
@RestController
@RequestMapping("/api/todo/v1")

// @Slf4j
public class TodoController {
private static final Logger log =
        LoggerFactory.getLogger(TodoController.class);
    @Autowired
    private TodoService todoService;

    // http://localhost:8080/api/todo/v1/create
    @PostMapping("/create")
    ResponseEntity<Todo> createUser(@Valid @RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodo(todo);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);

    }

    // Get by ID - http://localhost:8080/api/todo/v1/1
    @ApiResponses(value = {
    @ApiResponse(responseCode ="200", description = "Data retrived successfully"),
    @ApiResponse(responseCode ="404", description = "Data retrived failed!")
    })
    @GetMapping("/{id}")
    ResponseEntity<Todo> getTodoById(@PathVariable long id) {
        try {
            Todo createTodo = todoService.getTodoById(id);
            return new ResponseEntity<>(todoService.getTodoById(id), HttpStatus.OK);
        } catch (RuntimeException exception) {
            log.warn("Todo not found for id: {}", id);
            log.error("Error fetching Todo with id: {}", id, exception);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get all data http://localhost:8080/api/todo/v1/all
    @GetMapping("/all")
    ResponseEntity<List<Todo>> getAllTodo() {
        return ResponseEntity.ok(todoService.getAllTodo());
    }

    // Update - updates all the fileds like if we gice only 2 fields it will send
    // another 2 fields as null while passing

    @PutMapping("/{id}")
    ResponseEntity<Todo> updateTodoById(@PathVariable long id, @RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.updateTodo(id, todo), HttpStatus.OK);
    }

    // Patch - http://localhost:8080/api/todo/v1/1 - {"description":"Learn
    // SpringBoot" }
    @PatchMapping("/{id}")
    ResponseEntity<Todo> patchTodo(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        return ResponseEntity.ok(
                todoService.patchTodo(id, updates));
    }

    // bulk update - updates only the isCompleted field is true - refer the services
    // file
    @PutMapping("/bulk")
    ResponseEntity<List<Todo>> bulkComplete(
            @RequestBody List<Long> ids) {

        return ResponseEntity.ok(
                todoService.bulkComplete(ids));
    }

    // Delete by id
    @DeleteMapping("/{id}")
    void deleteTodoById(@PathVariable long id) {
        todoService.deleteTodoById(id);
    }

    // Pagination - Get - http://localhost:8080/api/todo/v1/page?page=1&size=4
    @GetMapping("/page")
    ResponseEntity<Page<Todo>> getAllPage(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(todoService.getAllPage(page, size), HttpStatus.OK);
    }
}