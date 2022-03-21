package com.example.quera.View;
import com.example.quera.Model.Student;
import com.example.quera.Model.Professor;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quera.R;

public class LoginActivity extends AppCompatActivity {
    EditText usernameTxt ,passwordTxt;
    Button loginBtn;
    TextView registerBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameTxt = findViewById(R.id.editTextTextPersonName);
        passwordTxt = findViewById(R.id.editTextTextPassword);
        loginBtn = findViewById(R.id.loginButton);
        registerBtn = findViewById(R.id.registerBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameTxt.getText().toString();
                String password = passwordTxt.getText().toString();
                if(username.equals("") || password.equals("")) {
                    Toast t = Toast.makeText(getApplicationContext(),"Please fill all parts\n" +
                                    "لطفا فیلد ها را پر کنید",
                            Toast.LENGTH_SHORT);
                    t.show();
                } else {
                    Student student = checkUserStudent(username);
                    Professor professor = checkUserProfessor(username);
                    if(professor != null){
                        if(professor.getPassword().equals(password)){
                            Intent intent = new Intent(LoginActivity.this,ProfessorPanelActivity2.class);
                            intent.putExtra("user",professor.getUsername());
                            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            myEdit.putString("user", username);
                            myEdit.commit();
                            startActivity(intent);
                        } else {
                            Toast t = Toast.makeText(getApplicationContext(),"Incorrect password\n" +
                                            "رمز اشتباه است",
                                    Toast.LENGTH_SHORT);
                            t.show();
                        }
                    } else if (student != null){
                        if(student.getPassword().equals(password)){
                            Intent intent = new Intent(LoginActivity.this,StudentPanelActivity.class);
                            intent.putExtra("user",student.getUsername());
                            startActivity(intent);
                        } else {
                            Toast t = Toast.makeText(getApplicationContext(),"Incorrect password\n" +
                                            "رمز اشتباه است",
                                    Toast.LENGTH_SHORT);
                            t.show();
                        }
                    } else {
                        Toast t = Toast.makeText(getApplicationContext(),"This username doesn't exist\n" +
                                        "این یوزرنیم وجود ندارد",
                                Toast.LENGTH_SHORT);
                        t.show();
                    }
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private Student checkUserStudent(String username) {
        for(Student st : Student.students) {
            if(st.getUsername().equals(username))
                return st;
        }
        return null;
    }

    private Professor checkUserProfessor(String username) {
        for (Professor professor : Professor.professors) {
            if(professor.getUsername().equals(username))
                return professor;
        }
        return null;
    }
}
