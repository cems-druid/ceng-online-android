package com.example.cengonline_v1.CoursesMVVM;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_table")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int course_id;

    private String name;
    private String course_info;


    public Course(String name, String info) {
        this.name = name;
        this.course_info = info;
    }

    public Course(){

    }

    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse_info() {
        return course_info;
    }

    public void setCourse_info(String course_info) {
        this.course_info = course_info;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
