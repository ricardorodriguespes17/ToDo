package com.example.todo.controller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todo.R;
import com.example.todo.model.Data;
import com.example.todo.model.Dialog;
import com.example.todo.model.Task;
import com.example.todo.model.TaskAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class MainActivity extends AppCompatActivity implements Dialog.OnEdit, Dialog.OnExcluir, Dialog.salvarTarefa {

    private int id_tarefa_selecionada;

    private FloatingActionButton fab;
    private TextView dayComplete, dayWeek;
    private ListView tasksList;
    //Elementos do Menu
    private TextView title, description;
    private MaterialCalendarView datePicker;
    ArrayAdapter<Task> arrayAdapter;

    private Button excluir_tarefa;
    private Button editar_tarefa;

    private EditText titleEdit, descriptionEdit;
    private CalendarView dataEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editar_tarefa = findViewById(R.id.btn_editar_tarefa);
        excluir_tarefa = findViewById(R.id.btn_excluir_tarefa);

        tasksList = findViewById(R.id.tasks_list);
        dayComplete = findViewById(R.id.day_complete_text);
        dayWeek = findViewById(R.id.day_week_text);
        fab = findViewById(R.id.fab);

        renderList();
        definirOnClick();
    }

    private void renderList(){
        arrayAdapter = new TaskAdapter(this);
        tasksList.setAdapter(arrayAdapter);
        tasksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                exibirItem(position);
            }
        });

    }

    public void definirOnClick(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newTask();
            }
        });

    }

    private void newTask(){
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(buildMenu());
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String titleTask, descTask;

                titleTask = title.getText().toString();
                descTask = description.getText().toString();

                long date = datePicker.getSelectedDate().getCalendar().getTime().getTime();

                new Task(titleTask, descTask, date);

                arrayAdapter.notifyDataSetChanged();
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

    public void exibirItem(int position){
        this.id_tarefa_selecionada = position;

        Task selecionada = Data.getTask(position);

        Dialog dialog = new Dialog(selecionada);
        dialog.setOnEdit(this);
        dialog.setOnExcluir(this);
        dialog.setSalvarTarefa(this);
        dialog.show(getSupportFragmentManager(), "");

    }

    @Override
    public void onExcluir() {
        Data.removeTask(id_tarefa_selecionada);
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEdit(Dialog.ViewHolder view) {
        view.title.setEnabled(true);
        view.description.setEnabled(true);
        view.datePicker.setEnabled(true);
    }

    @Override
    public void salvarTarefa(Dialog.ViewHolder view) {
        Data.removeTask(id_tarefa_selecionada);

        Task edit = new Task();

        edit.setTitle(view.title.getText().toString());
        edit.setDescription(view.description.getText().toString());
        edit.setDate(view.datePicker.getSelectedDate().getCalendar().getTime().getTime());

        Data.addTask(edit);
        arrayAdapter.notifyDataSetChanged();

    }
}
