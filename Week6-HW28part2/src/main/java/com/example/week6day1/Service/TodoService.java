package com.example.week6day1.Service;

import com.example.week6day1.Model.Myuser;
import com.example.week6day1.Model.Todo;
import com.example.week6day1.Repository.MyuserRepository;
import com.example.week6day1.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final MyuserRepository myuserRepository;
    public List<Todo> getTodos(Integer id) {
        return todoRepository.findTodoByUserId(id);
    }
    public void addTodo(Integer userId, Todo todo){
        todo.setMyuser(myuserRepository.findMyuserById(userId));
        todoRepository.save(todo);
    }

    public void deleteTodo(Integer userId,Integer todoId) {
        if (todoRepository.findTodoById(todoId)==null)
            throw new RuntimeException("todo not found");
        if (userId!=todoRepository.findTodoById(todoId).getMyuser().getId())
            throw new RuntimeException("wrong id");
        todoRepository.delete(todoRepository.findTodoById(todoId));
    }
    public void updateTodo(Integer userId,Integer todoId,Todo todo){
        Todo old = todoRepository.findTodoById(todoId);
        if (old==null)
            throw new RuntimeException("todo not found");
        if (userId!= old.getMyuser().getId())
            throw new RuntimeException("wrong id");
        old.setMssg(todo.getMssg());
        todoRepository.save(old);
    }
}
