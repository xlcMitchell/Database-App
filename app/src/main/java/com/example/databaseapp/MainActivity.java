package com.example.databaseapp;
//dependencies updated for the use of Room

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    AppSampleDatabase appDatabase;
    StudentDao studentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //creating database instance
        appDatabase = AppSampleDatabase.createDatabaseInstance(this);
        //getting the student DAO interface to perform CRUD operations
        studentDao = appDatabase.studentDao();

        if(studentDao.readAllStudents().isEmpty()){
            Log.d("ST_RECORD","adding student records");
            insertStudentRecords();
        }

        readStudentRecords();

        Button studentBtn = findViewById(R.id.studentCRUDBtn);
        Button courseBtn = findViewById(R.id.courseCRUDBtn);
        Button enrolmentBtn = findViewById(R.id.enrolmentCRUDBtn);

        studentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudentCrudActivity.class);
                startActivity(intent);
            }
        });

        courseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CourseCrudActivity.class);
                startActivity(intent);
            }
        });

        enrolmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EnrolmentCrudActivity.class);
                startActivity(intent);
            }
        });
    }

    private void insertStudentRecords(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            studentDao.insertStudent(new Student(0, "John", "Dargie", format.parse("2001-01-07"), "0212345678"));
            studentDao.insertStudent(new Student(0, "Alice", "Wonder", format.parse("2005-09-14"), "0212345678"));
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    private void readStudentRecords(){
        List<Student> students = studentDao.readAllStudents();
        for (Student s:students) {
            Log.d("ST_RECORD", s.toString());
        }
    }


}