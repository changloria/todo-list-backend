package com.afs.todo.dto;

public class TodoRequest {
    private String id;
    private String text;
    private Boolean done;

    public TodoRequest(String id, String text, Boolean done) {
        this.id = id;
        this.text = text;
        this.done = done;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }




}
