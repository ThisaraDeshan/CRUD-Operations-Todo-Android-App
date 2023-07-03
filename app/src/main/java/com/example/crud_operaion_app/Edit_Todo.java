package com.example.crud_operaion_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Edit_Todo extends AppCompatActivity {

    Button btnUpdate;
    EditText updateTitle , updateDesc;
    private Long updateDate;

    DbHandler dbHandler;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        dbHandler = new DbHandler(context);
        btnUpdate = findViewById(R.id.btnUpdate);
        updateTitle = findViewById(R.id.editTxtToDoTitle);
        updateDesc = findViewById(R.id.editTxtToDoDescription);

        final String id = getIntent().getStringExtra("id");
        ToDo toDo = dbHandler.getSingleToDo(Integer.parseInt(id));

        updateTitle.setText(toDo.getTitle());
        updateDesc.setText(toDo.getDescription());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titleText = updateTitle.getText().toString();
                String descText = updateDesc.getText().toString();
                updateDate = System.currentTimeMillis();

                ToDo toDo = new ToDo(Integer.parseInt(id),titleText,descText,updateDate,0);

                dbHandler.updateToDo(toDo);
                startActivity(new Intent(context, MainActivity.class));
            }
        });
    }
}