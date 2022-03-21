package com.example.quera.View;

        import android.app.Activity;
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
        import com.example.quera.R;

        import java.util.ArrayList;

public class StudentClassPanelActivity extends AppCompatActivity {
    Button showTaskBtn;
    TextView classInformation,refreshBtn;
    EditText showTaskNumField;
    LinearLayout tasksList;
    static Class aClass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class_panel);
        showTaskBtn = findViewById(R.id.showTaskBtn);
        classInformation = findViewById(R.id.classInformation2);
        refreshBtn = findViewById(R.id.refreshBtn);
        showTaskNumField = findViewById(R.id.showTaskNumField);
        tasksList = findViewById(R.id.tasksLinearLayout2);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setInfo();
            }
        });

        showTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = showTaskNumField.getText().toString();
                if(name.equals(""))
                    Toast.makeText(getApplicationContext(),"Please enter a name",Toast.LENGTH_SHORT);
                else {
                    if(checkExistence(name)){
                        Intent intent = new Intent(StudentClassPanelActivity.this,AnswerActivity.class);
                        intent.putExtra("ID",aClass.getID());
                        intent.putExtra("task",name);
                        startActivityForResult(intent,0);
                        
                    } else
                        Toast.makeText(getApplicationContext(),"Please enter a correct name",Toast.LENGTH_SHORT);
                }
            }
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    String returnValue = data.getStringExtra("some_key");
                    System.out.println("Injaaaa");
                }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setInfo();
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
