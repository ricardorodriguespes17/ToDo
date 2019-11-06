package com.example.todo.controller;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.todo.R;
import com.example.todo.model.Data;
import com.example.todo.model.Task;
import com.example.todo.model.TaskAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private TextView dayComplete, dayWeek;
    private ListView tasksList;
    //Elementos do Menu
    private TextView title, description;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Task t = new Task("Tarefa 1", "Descrição 1", new Date());

        tasksList = findViewById(R.id.tasks_list);
        dayComplete = findViewById(R.id.day_complete_text);
        dayWeek = findViewById(R.id.day_week_text);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newTask();
            }
        });

        renderList();

    }

    private void renderList(){
        ArrayAdapter<Task> arrayAdapter = new TaskAdapter(this);
        tasksList.setAdapter(arrayAdapter);
    }

    private void newTask(){
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(buildMenu());
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String titleTask, descTask;
                Date date = new Date();

                titleTask = title.getText().toString();
                descTask = description.getText().toString();
                date.setTime(datePicker.getDrawingTime());

                new Task(titleTask, descTask, date);

                renderList();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }

    private View buildMenu(){
        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.new_task_layout, null);

        title = view.findViewById(R.id.title_task_menu);
        description = view.findViewById(R.id.description_task_menu);
        datePicker = view.findViewById(R.id.date_picker_menu);

        return view;
    }

}
