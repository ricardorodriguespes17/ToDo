package com.example.todo.model;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private static List<Task> tasks = new ArrayList<>();

    public static void addTask(Task task){
        tasks.add(task);
    }

    public static List<Task> getTasks() {
        return tasks;
    }

    public static Task getTask(int position){
        return tasks.get(position);
    }

    public static void removeTask(int position){
        tasks.remove(position);
    }


}
