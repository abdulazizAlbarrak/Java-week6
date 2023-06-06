package com.example.week6day1.Repository;

import com.example.week6day1.Model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer> {
    //public Todo findTodoByUserId(Integer userId);
    public List<Todo> findTodoByUserId(Integer userId);
    public Todo findTodoById(Integer id);
}
