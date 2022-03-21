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
import com.example.quera.Model.Professor;
import com.example.quera.R;

import java.util.ArrayList;

public class ProfessorPanelActivity2 extends AppCompatActivity {
    private static Professor professor;
    private static String username;
    Button createClassBtn,editClassBtn;
    TextView information,refreshBtn;
    EditText editNumber;
    LinearLayout classList;
    @Override
    protected synchronized void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_panel2);
        editNumber = findViewById(R.id.editNumField);
        refreshBtn = findViewById(R.id.refreshBtn);
        createClassBtn = findViewById(R.id.createClassBtn);
        editClassBtn = findViewById(R.id.editClassBtn);
        information = findViewById(R.id.professorInformation);
        classList = findViewById(R.id.classesLinearLayout);
        Intent intent = getIntent();
        if(username == null){
            username = intent.getExtras().getString("user");
        }
        if(professor == null)
            professor = checkUserProfessor(username);
        setInfo();
        editClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editNumber.getText().toString().equals("")){
                    Toast t = Toast.makeText(getApplicationContext(),"Please Enter an ID",Toast.LENGTH_SHORT);
                    t.show();
                } else if (professor.getClasses().contains(Integer.parseInt(editNumber.getText().toString()))){
                    Intent intent1 = new Intent(ProfessorPanelActivity2.this,ProfessorClassPanelActivity.class);
                    intent1.putExtra("ID",Integer.parseInt(editNumber.getText().toString()));
                    intent1.putExtra("master",professor.getUsername());
                    startActivityForResult(intent1,0);
                } else {
                    Toast t = Toast.makeText(getApplicationContext(),"Please Enter a correct ID",Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
        createClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ProfessorPanelActivity2.this,CreateClassActivity.class);
                intent1.putExtra("professor",professor.getUsername());
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
        information.setText("Welcome Professor " + professor.getName() + "\n Here are your classes");
        ArrayList<Integer> classIDs = professor.getClasses();
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


    private Professor checkUserProfessor(String username) {
        for (Professor professor : Professor.professors) {
            if(professor.getUsername().equals(username))
                return professor;
        }
        return null;
    }
}
