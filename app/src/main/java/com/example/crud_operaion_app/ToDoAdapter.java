package com.example.crud_operaion_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ToDoAdapter extends ArrayAdapter<ToDo> {

    private Context context;
    private int resource;
    private List<ToDo> toDos;

    ToDoAdapter(Context context, int resource, List<ToDo> toDos) {
        super(context, resource, toDos);

        this.context = context;
        this.resource = resource;
        this.toDos = toDos;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View raw = inflater.inflate(resource, parent, false);

        TextView title = raw.findViewById(R.id.txtTitle);
        TextView desc = raw.findViewById(R.id.txtDescription);
        ImageView img = raw.findViewById(R.id.onGoing);

        ToDo todo = toDos.get(position);
        title.setText(todo.getTitle());
        desc.setText(todo.getDescription());
        img.setVisibility(raw.INVISIBLE);

        if (todo.getFinished() > 0){
            img.setVisibility(raw.VISIBLE);
        }
        return raw;


    }
}
