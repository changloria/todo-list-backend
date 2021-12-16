package com.afs.todo.service;

import com.afs.todo.entity.TodoItem;
import com.afs.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoItem> findAll() {
        return todoRepository.findAll();
    }

    public TodoItem create(TodoItem newTodoItem) {
        return todoRepository.insert(newTodoItem);
    }

    public List<TodoItem> findByDone(Boolean done) {
        return todoRepository.findAllByDone(done);
    }
}
