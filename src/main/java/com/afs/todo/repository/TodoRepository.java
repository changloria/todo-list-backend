package com.afs.todo.repository;

import com.afs.todo.entity.TodoItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends MongoRepository<TodoItem, String> {
    List<TodoItem> findAllByDone(Boolean done);
}
