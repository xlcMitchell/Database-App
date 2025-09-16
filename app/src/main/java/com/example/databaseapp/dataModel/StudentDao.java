package com.example.databaseapp.dataModel;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM student")
    List<Student> readAllStudents();
    //-1 means record not inserted
        @Insert
        long insertStudent(Student student);
    //0 means no record update
        @Update
    void updateStudent(Student student);
    //0 means no record is deleted
        @Delete
    void deleteStudent(Student student);


}
