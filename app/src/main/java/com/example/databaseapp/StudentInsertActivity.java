package com.example.databaseapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.databaseapp.dataModel.AppSampleDatabase;
import com.example.databaseapp.dataModel.Student;
import com.example.databaseapp.dataModel.StudentDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StudentInsertActivity extends AppCompatActivity {

    AppSampleDatabase database;
    StudentDao studentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_insert);

        database = AppSampleDatabase.createDatabaseInstance(getApplicationContext());
        studentDao = database.studentDao();

        findViewById(R.id.saveStudentCancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.saveStudentBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveStudentRecord();
            }
        });
    }

    private void saveStudentRecord(){
        EditText fNameText, lNameText, dateText, mobileText;
        fNameText = findViewById(R.id.saveStudentFirstName);
        lNameText = findViewById(R.id.saveStudentLastName);
        dateText = findViewById(R.id.saveStudentDOB);
        mobileText = findViewById(R.id.saveStudentMobile);
        String fName,lName,date,mobile;
        fName = fNameText.getText().toString();
        lName = lNameText.getText().toString();
        date = dateText.getText().toString();
        mobile = mobileText.getText().toString();

        if(fName.isEmpty() || lName.isEmpty() || date.isEmpty() || mobile.isEmpty()){
            Toast.makeText(this,"You need to enter information to save record",
                    Toast.LENGTH_SHORT).show();
        }else{
            SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyy");
            try{
                Student student = new Student(0,fName,lName,format.parse(date),mobile);
                long id = studentDao.insertStudent(student);
                if(id != -1){
                    Toast.makeText(this,"Student record\n" + student.toString() + "\n sucessfully added",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
            }catch(ParseException e){
                e.printStackTrace();
            }
        }
    }
}