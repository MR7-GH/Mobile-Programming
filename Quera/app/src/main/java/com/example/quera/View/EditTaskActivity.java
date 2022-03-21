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
import com.example.quera.Model.Student;
import com.example.quera.R;

import java.util.ArrayList;

public class EditTaskActivity extends AppCompatActivity {

    TextView changeTaskNameBtn,taskInformation;
    LinearLayout membersList;
    EditText memberNameField,changeNameField;
    Button checkMemberBtn;
    static Class aClass;
    static HomeWork task;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        changeTaskNameBtn = findViewById(R.id.changeTaskNameBtn);
        taskInformation = findViewById(R.id.taskInformation);
        membersList = findViewById(R.id.membersList);
        memberNameField = findViewById(R.id.memberNameField);
        checkMemberBtn = findViewById(R.id.checkMemberBtn);
        changeNameField = findViewById(R.id.changeNameField);
        Intent intent = getIntent();
        if(aClass == null)
            aClass = Class.getClassByID(intent.getExtras().getInt("ID"));
        task = aClass.getTaskByName(intent.getExtras().getString("task"));
        setInfo();

        checkMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = memberNameField.getText().toString();
                if(username.equals(""))
                    Toast.makeText(getApplicationContext(),"Please enter a name",Toast.LENGTH_SHORT).show();
                else {
                    if(checkMemberExist(username)) {
                        Intent intent1 = new Intent(EditTaskActivity.this,CheckAnswersActivity.class);
                        intent1.putExtra("member",username);
                        startActivityForResult(intent1,0);
                    } else
                        Toast.makeText(getApplicationContext(),"Please enter a name",Toast.LENGTH_SHORT).show();
                }
            }
        });

        changeTaskNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = changeNameField.getText().toString();
                if(name.equals(""))
                    Toast.makeText(getApplicationContext(),"Please enter a name",Toast.LENGTH_SHORT).show();
                else {
                    if(checkTaskExist(name))
                        Toast.makeText(getApplicationContext(),"Please enter a new name",Toast.LENGTH_SHORT).show();
                    else {
                        task.setName(name);
                        setInfo();
                    }
                }
            }
        });
    }

    private boolean checkTaskExist(String name) {
        for (HomeWork aClassTask : aClass.getTasks()) {
            if(aClassTask.getName().equals(name))
                return true;
        }
        return false;
    }

    private boolean checkMemberExist(String username) {
        for (Student member : aClass.getMembers()) {
            if(username.equals(member.getUsername()))
                return true;
        }
        return false;
    }

    private void setInfo() {
        membersList.removeAllViews();
        String taskName = task.getName();
        taskInformation.setText(taskName + "\n" + aClass.getMaster());
        ArrayList<Student> members = aClass.getMembers();
        for (Student member : members) {
            TextView t = new TextView(this);
            t.setText(member.getName());
            membersList.addView(t);
        }
    }
}
