package com.example.databaseapp.dataModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course")
public class Course {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="course_id")
    private int courseId;
    @ColumnInfo(name="course_name")
    private String courseName;
    private String description;
    @ColumnInfo(name="credit_hour")
    private int creditHour;

    //---CONSTRUCTOR WITHOUT ARGUMENTS---//
    public Course(){}

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDescription() {
        return description;
    }

    public int getCreditHour() {
        return creditHour;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreditHour(int creditHour) {
        this.creditHour = creditHour;
    }
}
