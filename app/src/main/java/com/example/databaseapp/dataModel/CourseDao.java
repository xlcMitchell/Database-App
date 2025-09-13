package com.example.databaseapp.dataModel;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseDao {
    @Query("SELECT * FROM course")
    List<Course> readAllCourses();
    //-1 means record not inserted
    @Insert
    void insertCourse(Course course);
    //0 means no record update
    @Update
    void updateCourse(Course course);
    //0 means no record is deleted
    @Delete
    void deleteCourse(Course course);


}