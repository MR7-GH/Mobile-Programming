package com.example.quera.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quera.Model.HomeWork;
import com.example.quera.Model.Student;
import com.example.quera.R;

import org.w3c.dom.Text;

public class CheckAnswersActivity extends AppCompatActivity {
    static HomeWork task;
    static Student student;
    TextView memberNameTxt,answerMemberTxt;
    EditText scoreField;
    Button doneBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_answers);
        memberNameTxt = findViewById(R.id.memberNameTxt);
        answerMemberTxt = findViewById(R.id.answerMemberTxt);
        scoreField = findViewById(R.id.scoreField);
        doneBtn = findViewById(R.id.doneBtn);
        setInfo();
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(scoreField.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(),"Please enter the score",Toast.LENGTH_SHORT).show();
                else {
                    task.addPoint(student.getUsername(),Integer.parseInt(scoreField.getText().toString()));
                    finish();
                }
            }
        });
    }

    private void setInfo() {
        task = EditTaskActivity.task;
        Intent intent = getIntent();
        String username = intent.getExtras().getString("member");
        student = Student.getStudentByUsername(username);
        memberNameTxt.setText(username);
        if(task.getAnswers().containsKey(username)) {
            answerMemberTxt.setText(task.getAnswers().get(username));
        } else {
            answerMemberTxt.setText("Student hasn't submit any answer yet!");
        }
    }
}
