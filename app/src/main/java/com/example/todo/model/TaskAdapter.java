package com.example.todo.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.todo.R;

public class TaskAdapter extends ArrayAdapter<Task> {
    private Context context;

    public TaskAdapter(Context context) {
        super(context, R.layout.row_task_list, Data.getTasks());
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = li.inflate(R.layout.row_task_list, parent, false);

        TextView title, description;

        title = rowView.findViewById(R.id.title_row);
        description = rowView.findViewById(R.id.description_row);

        Task task = Data.getTasks().get(position);

        title.setText(task.getTitle());
        description.setText(task.getDescription());

        return rowView;
    }
}
