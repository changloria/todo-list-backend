package com.afs.todo.controller;

import com.afs.todo.entity.TodoItem;
import com.afs.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("todos")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoItem> getAllTodoItems() {
        return new ArrayList<>(todoService.findAll());
    }

    @GetMapping(params = {"done"})
    public List<TodoItem> getTodoDoneList(@RequestParam Boolean done){
        return todoService.findByDone(done);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoItem createEmployee(@RequestBody TodoItem todoRequest){
        return todoService.create(todoRequest);
    }


}
