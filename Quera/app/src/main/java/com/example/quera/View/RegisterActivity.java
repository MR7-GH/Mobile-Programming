package com.example.quera.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quera.Model.Professor;
import com.example.quera.Model.Student;
import com.example.quera.Model.User;
import com.example.quera.R;

public class RegisterActivity extends AppCompatActivity {
    Button registerBtn;
    Switch professorSwitch;
    EditText usernameField,passwordField,confirmField,nameField;
    TextView changerTxtView;
    EditText info;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changerTxtView = findViewById(R.id.changerTxtView);
        info = findViewById(R.id.ST_UNIField);
        registerBtn = findViewById(R.id.registerConfirmBtn);
        usernameField = findViewById(R.id.registerUsernameField);
        passwordField = findViewById(R.id.registerPasswordField);
        confirmField = findViewById(R.id.registerConfirmPassField);
        professorSwitch = findViewById(R.id.professorSwitch);
        nameField = findViewById(R.id.registerNameField);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();
                String confirm = confirmField.getText().toString();
                String information = info.getText().toString();
                String fullName = nameField.getText().toString();
                if(username.equals("") || password.equals("") || confirm.equals("") || information.equals("") || fullName.equals("")){
                    Toast t = Toast.makeText(getApplicationContext(),"Please fill all parts",Toast.LENGTH_SHORT);
                    t.show();
                } else {
                    if(checkUserExist(username)) {
                        Toast t = Toast.makeText(getApplicationContext(),"This username has been chosen",Toast.LENGTH_SHORT);
                        t.show();
                    } else {
                        if (!confirm.equals(password)) {
                            Toast t = Toast.makeText(getApplicationContext(),"Password and confirm aren't match",Toast.LENGTH_SHORT);
                            t.show();
                        } else {
                            if(professorSwitch.isChecked()) {
                                Professor professor = new Professor(fullName,username,password,information);
                            } else {
                                Student student = new Student(fullName,username,password,information);
                            }
                            Toast t = Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT);
                            t.show();
                            finish();
                        }
                    }
                }
            }
        });
        professorSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(professorSwitch.isChecked()) {
                    changerTxtView.setText("University");
                    info.setHint("Enter your university");
                    changerTxtView.setPadding(100,0,0,0);
                } else {
                    changerTxtView.setText("Student number");
                    info.setHint("Enter your number");
                    changerTxtView.setPadding(0,0,0,0);
                }
            }
        });
    }

    private boolean checkUserExist(String username) {
        for(User user:Professor.professors)
            if(user.getUsername().equals(username))
                return true;
        for (User user : Student.students)
            if(user.getUsername().equals(username))
                return true;
        return false;
    }
}
