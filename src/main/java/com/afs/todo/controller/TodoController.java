package com.afs.todo.controller;

import com.afs.todo.entity.TodoItem;
import com.afs.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("todos")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //find all todoitems
    @GetMapping
    public List<TodoItem> getAllTodoItems() {
        return new ArrayList<>(todoService.findAll());
    }

    //find todoitem by id
    @GetMapping("/{id}")
    public TodoItem getTodoItemById(@PathVariable String id) {
        return todoService.findById(id);
    }

    //find all todoitems by done/undone list
    @GetMapping(params = {"done"})
    public List<TodoItem> getTodoDoneList(@RequestParam Boolean done){
        return todoService.findByDone(done);
    }

    //add a new todoitem
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoItem createTodoItem(@RequestBody TodoItem todoRequest){
        return todoService.create(todoRequest);
    }

    //update done, text in a todoitem
    @PutMapping("/{id}")
    public TodoItem editTodoItem(@PathVariable String id, @RequestBody TodoItem updatedTodoItem){
        return todoService.update(id, updatedTodoItem);
    }

    //delete a todoitem
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeTodoItem(@PathVariable String id){
        todoService.remove(id);
    }

}
