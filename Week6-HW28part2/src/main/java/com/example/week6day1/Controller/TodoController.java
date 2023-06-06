package com.example.week6day1.Controller;

import com.example.week6day1.Model.Myuser;
import com.example.week6day1.Model.Todo;
import com.example.week6day1.Service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/get")
    public ResponseEntity getTodos(@AuthenticationPrincipal Myuser myuser){
        List<Todo> todoList=todoService.getTodos(myuser.getId());
        return ResponseEntity.status(200).body(todoList);
    }
    @PostMapping("/add")
    public ResponseEntity addTodo(@AuthenticationPrincipal Myuser myuser, @RequestBody Todo todo){
        todoService.addTodo(myuser.getId(), todo);
        return ResponseEntity.status(200).body("Todo added");
    }
    @DeleteMapping("/delete/{todoId}")
    public ResponseEntity deleteTodo(@AuthenticationPrincipal Myuser myuser,@PathVariable Integer todoId){
        todoService.deleteTodo(myuser.getId(),todoId);
        return ResponseEntity.status(200).body("Todo deleted");
    }
    @PutMapping("/update/{todoId}")
    public ResponseEntity updateTodo(@AuthenticationPrincipal Myuser myuser,@PathVariable Integer todoId, @RequestBody Todo todo){
        todoService.updateTodo(myuser.getId(), todoId, todo);
        return ResponseEntity.status(200).body("Todo updated");
    }
}
