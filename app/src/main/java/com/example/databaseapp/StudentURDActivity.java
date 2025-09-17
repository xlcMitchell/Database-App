package com.example.databaseapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import java.util.List;

public class StudentURDActivity extends AppCompatActivity {

    AppSampleDatabase database;
    StudentDao studentDao;
    List<Student> students;
    int index = -1;
    Button editBtn, updateBtn, deleteBtn, previousBtn, nextBtn;
    EditText fNameText, lNameText, dateText, mobileText;
    boolean editing = false;
    SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_urdactivity);

        editBtn = findViewById(R.id.editStudentBtn);
        updateBtn = findViewById(R.id.updateStudentBtn);
        deleteBtn = findViewById(R.id.deleteStudentBtn);
        previousBtn = findViewById(R.id.studentPreviousRecordBtn);
        nextBtn = findViewById(R.id.studentNextRecordBtn);

        fNameText = findViewById(R.id.urdStudentFirstName);
        lNameText = findViewById(R.id.urdStudentLastName);
        dateText = findViewById(R.id.urdStudentDOB);
        mobileText = findViewById(R.id.urdStudentMobile);

        editing = false;
        dateFormat = new SimpleDateFormat("dd/mm/yyy");

        database = AppSampleDatabase.createDatabaseInstance(getApplicationContext());
        studentDao = database.studentDao();
        students = studentDao.readAllStudents();

        if(students.size() > 0){
            index = 0;
        }

        findViewById(R.id.urdStudentCancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previousRecord();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextRecord();
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editRecord();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRecord();
            }
        });


    }

    private void showRecord(){
        if(index == -1){
            Toast.makeText( this,  "No Record to Show", Toast.LENGTH_LONG).show(); finish();
        }else{
            fNameText.setText(students.get(index).getFirstName());
            lNameText.setText(students.get(index).getLastName());
            dateText.setText(dateFormat.format(students.get(index).getBirthDate()));
            mobileText.setText(students.get(index).getMobileNumber());
        }
    }

    private void setEditingRecord (boolean enable){
        updateBtn.setEnabled(enable);
        deleteBtn.setEnabled(!enable);
        previousBtn.setEnabled(!enable);
        nextBtn.setEnabled(!enable);
        fNameText.setEnabled(enable);
        lNameText.setEnabled(enable);
        dateText.setEnabled(enable);
        mobileText.setEnabled(enable);
    }

    private void previousRecord(){
        if(index > 0){ //check that index is not at the beginning of lists first index
            index--;
            showRecord();
        }
    }

    private void nextRecord( ){
        if(index < students.size() - 1){ //check that index is not at the end of students list
            index++;
            showRecord();
        }
    }

    private void editRecord(){
        editing = !editing; //invert editing value
        setEditingRecord(editing);
        setEditingRecord(editing);
        if(editing)
            editBtn.setText("No Edit");
        else
            editBtn.setText("Edit");
    }

    private void updateRecord(){
        String fName, lName, date, mobile;
        fName = fNameText.getText().toString();
        lName = lNameText.getText().toString();
        date = dateText.getText().toString();
        mobile = mobileText.getText().toString();
        students.get(index).setFirstName(fName);
        students.get(index).setLastName(lName);
        try {
            students.get(index).setBirthDate(dateFormat.parse(date));
        }catch(ParseException e) {
            e.printStackTrace();
        }

        students.get(index).setMobileNumber(mobile);
        int result = studentDao.updateStudent(students.get(index));
        if (result != 0) {
            Toast.makeText(this, "Record is updated!!!", Toast.LENGTH_LONG).show();
            editRecord();
            showRecord();
        }else{
            Toast.makeText(this,"Update Error...", Toast.LENGTH_LONG).show();
        }

    }




}