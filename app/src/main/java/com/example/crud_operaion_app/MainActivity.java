package com.example.crud_operaion_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button Add;
    ListView listView;
    TextView count;
    DbHandler dbHandler;
    Context context;

    List<ToDo> todos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        Add = findViewById(R.id.btnAddNew);
        listView = findViewById(R.id.listView);
        count = findViewById(R.id.countTodo);
        dbHandler = new DbHandler(context);

        todos = dbHandler.getAllToDo();

        ToDoAdapter adapter = new ToDoAdapter(context,R.layout.single_todo,todos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ToDo todo = todos.get(i);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(todo.getTitle());
                builder.setMessage(todo.getDescription());

                builder.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        todo.setFinished(System.currentTimeMillis());
                        dbHandler.updateToDo(todo);
                        startActivity(new Intent(context, MainActivity.class));
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dbHandler.deleteToDo(todo.getId());
                        startActivity(new Intent(context, MainActivity.class));

                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent a = new Intent(context, Edit_Todo.class);
                        a.putExtra("id",String.valueOf(todo.getId()));
                        startActivity(a);
                    }
                });
                builder.show();

            }
        });

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Add_todo.class));
            }
        });

        int countTodo = dbHandler.countToDo();
        count.setText("You Have " +countTodo+ " todos");


    }
}