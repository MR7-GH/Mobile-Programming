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
import com.example.quera.Model.Student;
import com.example.quera.R;

import java.util.ArrayList;

public class AddClassActivity extends AppCompatActivity {
    LinearLayout classList;
    EditText classIDField;
    Button joinBtn;
    static Student student;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        classList = findViewById(R.id.classAvailableList);
        classIDField = findViewById(R.id.classIDFieldd);
        joinBtn = findViewById(R.id.joinBtn);
        Intent intent = getIntent();
        student = Student.getStudentByUsername(intent.getExtras().getString("student"));
        setInfo();

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(classIDField.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(),"Please enter the score",Toast.LENGTH_SHORT).show();
                else {
                    int id = Integer.parseInt(classIDField.getText().toString());
                    if(student.getClasses().contains(id))
                        Toast.makeText(getApplicationContext(),"Please enter the score",Toast.LENGTH_SHORT).show();
                    else{
                        student.addClass(id);
                        Class.getClassByID(id).getMembers().add(student);
                        Toast.makeText(getApplicationContext(),"successful",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }

    private void setInfo() {
        ArrayList<Class> classes = new ArrayList<>();
        classes.addAll(Class.classes);
        for (Integer aClass : student.getClasses()) {
           classes.remove(Class.getClassByID(aClass));
        }
        for (Class aClass : classes) {
            TextView t = new TextView(this);
            t.setText(aClass.getName() + "    ID: " + aClass.getID() + "   Teacher: " + aClass.getMaster());
            classList.addView(t);
        }
    }
}
