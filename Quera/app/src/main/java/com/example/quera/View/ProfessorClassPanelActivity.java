package com.example.quera.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quera.Model.Class;
import com.example.quera.Model.HomeWork;
import com.example.quera.Model.Professor;
import com.example.quera.R;

import java.util.ArrayList;

public class ProfessorClassPanelActivity extends AppCompatActivity {
    Button createTaskBtn,editTaskBtn;
    TextView classInformation,refreshBtn;
    EditText editTaskNumField;
    LinearLayout tasksList;
    static Class aClass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_class_panel);
        createTaskBtn = findViewById(R.id.createTaskBtn);
        editTaskBtn = findViewById(R.id.editTaskBtn);
        classInformation = findViewById(R.id.classInformation);
        refreshBtn = findViewById(R.id.refreshBtn);
        editTaskNumField = findViewById(R.id.editTaskNumField);
        tasksList = findViewById(R.id.tasksLinearLayout);
        setInfo();

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setInfo();
            }
        });

        createTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfessorClassPanelActivity.this,CreateTaskActivity.class);
                intent.putExtra("ID",aClass.getID());
                startActivity(intent);
            }
        });

        editTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTaskNumField.getText().toString();
                if(name.equals(""))
                    Toast.makeText(getApplicationContext(),"Please enter a name",Toast.LENGTH_SHORT);
                else {
                    if(checkExistence(name)){
                        Intent intent = new Intent(ProfessorClassPanelActivity.this,EditTaskActivity.class);
                        intent.putExtra("ID",aClass.getID());
                        intent.putExtra("task",name);
                        startActivityForResult(intent,0);
                    } else
                        Toast.makeText(getApplicationContext(),"Please enter a correct name",Toast.LENGTH_SHORT);
                }
            }
        });

    }

    private boolean checkExistence(String name) {
        for (HomeWork task : aClass.getTasks()) {
            if(name.equals(task.getName()))
                return true;
        }
        return false;
    }

    private void setInfo() {
        tasksList.removeAllViews();
        Intent intent = getIntent();
        int id = intent.getExtras().getInt("ID");
        aClass = Class.getClassByID(id);
        classInformation.setText("Teacher : \n Professor " + aClass.getMaster());
        ArrayList<HomeWork> tasks = aClass.getTasks();
        for (HomeWork task : tasks) {
            TextView t = new TextView(this);
            t.setText(task.getName());
            t.setTextSize(15);
            tasksList.addView(t);
        }
    }

}
