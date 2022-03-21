package com.example.quera.View;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.quera.Model.Professor;
import com.example.quera.Model.Student;
import com.example.quera.R;

import java.util.ArrayList;

public class StudentPanelActivity extends AppCompatActivity {
    private static Student student;
    private static String username;
    Button addClassBtn,showClassBtn;
    TextView information,refreshBtn;
    EditText editNumber;
    LinearLayout classList;
    @Override
    protected synchronized void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_panel);
        editNumber = findViewById(R.id.showNumField);
        refreshBtn = findViewById(R.id.refreshBtn2);
        addClassBtn = findViewById(R.id.addClassBtn);
        showClassBtn = findViewById(R.id.showClassBtn);
        information = findViewById(R.id.studentInformation);
        classList = findViewById(R.id.classesLinearLayout2);
        Intent intent = getIntent();
        username = intent.getExtras().getString("user");
        student = checkUserStudent(username);

        setInfo();
        showClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editNumber.getText().toString().equals("")){
                    Toast t = Toast.makeText(getApplicationContext(),"Please Enter an ID",Toast.LENGTH_SHORT);
                    t.show();
                } else if (student.getClasses().contains(Integer.parseInt(editNumber.getText().toString()))){
                    Intent intent1 = new Intent(StudentPanelActivity.this,StudentClassPanelActivity.class);
                    intent1.putExtra("ID",Integer.parseInt(editNumber.getText().toString()));
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putInt("id", Integer.parseInt(editNumber.getText().toString()));
                    myEdit.commit();
                    intent1.putExtra("student",student.getUsername());
                    startActivityForResult(intent1,0);
                } else {
                    Toast t = Toast.makeText(getApplicationContext(),"Please Enter a correct ID",Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
        addClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(StudentPanelActivity.this,AddClassActivity.class);
                intent1.putExtra("student",student.getUsername());
                startActivityForResult(intent1,0);
            }
        });
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setInfo();
            }
        });
    }

    private void setInfo() {
        information.setText("Welcome " + student.getName() + "\n Here are your classes");
        ArrayList<Integer> classIDs = student.getClasses();
        ArrayList<String> classNames = new ArrayList<>();
        classList.removeAllViews();
        if(!classIDs.isEmpty())
            for (Integer classID : classIDs) {
                System.out.println(classID);
                classNames.add(Class.getClassByID(classID).getName() + "   ID: " + classID);
                TextView t = new TextView(this);
                t.setText(Class.getClassByID(classID).getName() + "   ID: " + classID);
                classList.addView(t);
            }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(student == null) {
            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
            username = sharedPreferences.getString("user", "");
            student = checkUserStudent(username);
        }
        setInfo();
    }

    private Student checkUserStudent(String username) {
        for (Student professor : Student.students) {
            if(professor.getUsername().equals(username))
                return professor;
        }
        return null;
    }
}
