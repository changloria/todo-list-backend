package com.afs.todo.dto;

public class TodoResponse {
    private String text;
    private String id;
    private boolean done;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public TodoResponse(String text, String id, boolean done) {
        this.text = text;
        this.id = id;
        this.done = done;
    }
}
