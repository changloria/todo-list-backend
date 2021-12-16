package com.afs.todo.exception;

public class NoTodoItemFoundException extends RuntimeException{
    public NoTodoItemFoundException() {
        super("None of the Todo Item is found");
    }
}
