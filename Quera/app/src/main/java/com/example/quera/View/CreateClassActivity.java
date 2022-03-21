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
import com.example.quera.Model.Professor;
import com.example.quera.R;

import java.io.BufferedReader;

public class CreateClassActivity extends AppCompatActivity {
    Button createBtn;
    EditText classNameField;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
        createBtn = findViewById(R.id.createBtn);
        classNameField = findViewById(R.id.classNameField);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = classNameField.getText().toString();
                if(name.equals("")){
                    Toast t = Toast.makeText(getApplicationContext(),"Please fill the part",Toast.LENGTH_SHORT);
                    t.show();
                } else {
                    if(checkClassName(name)){
                        Toast t = Toast.makeText(getApplicationContext(),"Please choose a new name",Toast.LENGTH_SHORT);
                        t.show();
                    } else {
                        Intent intent = getIntent();
                        Professor professor = checkUserProfessor(intent.getExtras().getString("professor"));
                        Class newClass = new Class(name,professor.getUsername());
                        professor.addClass(newClass.getID());
                        Toast t = Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT);
                        t.show();
                        finish();
                    }
                }
            }
        });
    }

    private boolean checkClassName(String name) {
        for (Class aClass : Class.classes) {
            if(aClass.getName().equals(name))
                return true;
        }
        return false;
    }

    private Professor checkUserProfessor(String username) {
        for (Professor professor : Professor.professors) {
            if(professor.getUsername().equals(username))
                return professor;
        }
        return null;
    }
}
