package com.example.quera.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quera.Model.Class;
import com.example.quera.Model.HomeWork;
import com.example.quera.R;

public class CreateTaskActivity extends AppCompatActivity {
    EditText taskNameField, taskQuestionField;
    Button confirmTaskBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        confirmTaskBtn = findViewById(R.id.confirmTaskBtn);
        taskNameField = findViewById(R.id.taskNameField);
        taskQuestionField = findViewById(R.id.taskQuestionField);
        confirmTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = taskNameField.getText().toString();
                String question = taskQuestionField.getText().toString();
                if(name.equals("") || question.equals(""))
                    Toast.makeText(getApplicationContext(),"Please fill all fields",Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = getIntent();
                    int id = intent.getExtras().getInt("ID");
                    Class aClass = Class.getClassByID(id);
                    if(checkTaskName(aClass,name))
                        Toast.makeText(getApplicationContext(),"Please choose a new name",Toast.LENGTH_SHORT).show();
                    else {
                        HomeWork hw = new HomeWork(name,question,id);
                        aClass.getTasks().add(hw);
                        finish();
                    }
                }
            }
        });
    }

    private boolean checkTaskName(Class aClass, String name) {
        for (HomeWork task : aClass.getTasks()) {
            if(task.getName().equals(name))
                return true;
        }
        return false;
    }
}
