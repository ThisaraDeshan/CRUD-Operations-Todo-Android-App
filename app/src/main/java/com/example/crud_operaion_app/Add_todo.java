package com.example.crud_operaion_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_todo extends AppCompatActivity {

    Button AddToDo;
    EditText title,desc;

    DbHandler dbHandler;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);



        AddToDo = findViewById(R.id.btnAddToDoNew);
        title = findViewById(R.id.editTxtTitle);
        desc = findViewById(R.id.editTxtDescription);

        dbHandler = new DbHandler(context);

        AddToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userTitle = title.getText().toString();
                String userDesc = desc.getText().toString();
                long date = System.currentTimeMillis();

                ToDo todo = new ToDo(userTitle,userDesc,date,0);
                dbHandler.AddToDo(todo);
                startActivity(new Intent(context, MainActivity.class));
            }
        });
    }
}