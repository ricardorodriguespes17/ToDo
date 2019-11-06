package com.example.todo.model;

import java.util.Date;

public class Task {

    private String title, description;
    private Date date;

    public Task(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
        Data.addTask(this);
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
