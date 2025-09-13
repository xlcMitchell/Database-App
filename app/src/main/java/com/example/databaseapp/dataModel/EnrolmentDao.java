package com.example.databaseapp.dataModel;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EnrolmentDao {
    @Query("SELECT * FROM enrolment")
    List<Course> readAllEnrolments();
    //-1 means record not inserted
    @Insert
    void insertEnrolment(Enrolment enrolment);
    //0 means no record update
    @Update
    void updateEnrolment(Enrolment enrolment);
    //0 means no record is deleted
    @Delete
    void deleteEnrolment(Enrolment enrolment);


}