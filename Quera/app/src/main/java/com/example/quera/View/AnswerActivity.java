package com.example.quera.View;

        import android.app.Activity;
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

public class AnswerActivity extends AppCompatActivity {
    static HomeWork task;
    static Student student;
    TextView memberNameTxt,scoreTxt;
    EditText answerMemberField;
    Button doneBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        memberNameTxt = findViewById(R.id.memberNameTxt2);
        answerMemberField = findViewById(R.id.answerMemberField);
        scoreTxt = findViewById(R.id.scoreTxt);
        doneBtn = findViewById(R.id.doneBtn);
        setInfo();
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answerMemberField.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(),"Please enter the score",Toast.LENGTH_SHORT).show();
                else {
                    task.addAnswer(student.getUsername(),answerMemberField.getText().toString());
                    Intent resultIntent = new Intent();
// TODO Add extras or a data URI to this intent as appropriate.
                    resultIntent.putExtra("some_key", "String data");
                    setResult(Activity.RESULT_OK, resultIntent);
                    finishActivity(RESULT_OK);
                }
            }
        });
    }

    private void setInfo() {
        Intent intent = getIntent();
        task = StudentClassPanelActivity.aClass.getTaskByName(intent.getExtras().getString("task"));
        String username = intent.getExtras().getString("member");
        student = Student.getStudentByUsername(username);
        memberNameTxt.setText(username);
        if(task.getAnswers().containsKey(username)) {
            answerMemberField.setText(task.getAnswers().get(username));
        }
        if(task.getPoints().containsKey(username))
            scoreTxt.setText(String.valueOf(task.getPoints().get(username)));
    }
}

