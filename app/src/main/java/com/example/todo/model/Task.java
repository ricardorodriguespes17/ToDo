package com.example.todo.model;

import java.util.Date;

public class Task {

    private String title, description;
    private long date;

    public Task(String title, String description, long date) {
        this.title = title;
        this.description = description;
        this.date = date;
        Data.addTask(this);
    }

    public Task(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
