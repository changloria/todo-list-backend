package com.afs.todo.service;

import com.afs.todo.entity.TodoItem;
import com.afs.todo.exception.NoTodoItemFoundException;
import com.afs.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public TodoItem update(String id, TodoItem updatedTodoItem){
        TodoItem todoItem = todoRepository.findById(id).orElseThrow(NoTodoItemFoundException::new);
        if (updatedTodoItem.getText() != null)
            todoItem.setText(updatedTodoItem.getText());
        if (updatedTodoItem.getDone() != null)
            todoItem.setDone(updatedTodoItem.getDone());
        return todoRepository.save(todoItem);
    }

    public void remove(String id) {
        todoRepository.findById(id).orElseThrow(NoTodoItemFoundException::new);
        todoRepository.deleteById(id);
    }

    public TodoItem findById(String id) {
        return todoRepository.findById(id).orElseThrow(NoTodoItemFoundException::new);
    }
}
