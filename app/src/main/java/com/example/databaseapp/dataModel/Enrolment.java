package com.example.databaseapp.dataModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "enrolment", foreignKeys={
        @ForeignKey(entity = Student.class,
                parentColumns = "student_id",
                childColumns = "student_id"),
        @ForeignKey(
                entity= Course.class,
                parentColumns = "course_id",
                childColumns = "course_id"
        )

})
public class Enrolment {
    @PrimaryKey
    @ColumnInfo(name = "enrolment_id")
    private int enrolmentId;
    @ColumnInfo(name = "student_id")
    private int studentId;
    @ColumnInfo(name = "course_id")
    private int courseId;

    public Enrolment(int enrolmentId, int studentId, int courseId) {
        this.enrolmentId = enrolmentId;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Enrolment{" +
                "enrolmentId=" + enrolmentId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }

    public void setEnrolmentId(int enrolmentId) {
        this.enrolmentId = enrolmentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getEnrolmentId() {
        return enrolmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }
}

