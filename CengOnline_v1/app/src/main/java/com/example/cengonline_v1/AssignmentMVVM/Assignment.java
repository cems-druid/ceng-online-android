package com.example.cengonline_v1.AssignmentMVVM;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cengonline_v1.CoursesMVVM.Course;

@Entity(tableName = "assignment_table")
public class Assignment {
    @PrimaryKey(autoGenerate = true)
    private int assignment_id;

    private String due_date;
    private String name_assignment;
    private String name_of_course;

    //this attribute is for testing.
    private String text_of_submission;



    public Assignment(String name_assignment, String name_of_course, String due_date){
        this.name_assignment = name_assignment;
        this.name_of_course = name_of_course;
        this.due_date = due_date;
    }


    public Assignment(){

    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getName_assignment() {
        return name_assignment;
    }

    public void setName_assignment(String name_assignment) {
        this.name_assignment = name_assignment;
    }

    public int getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(int assignment_id) {
        this.assignment_id = assignment_id;
    }

    public String getName_of_course() {
        return name_of_course;
    }

    public void setName_of_course(String name_of_course) {
        this.name_of_course = name_of_course;
    }

    public String getText_of_submission() {
        return text_of_submission;
    }

    public void setText_of_submission(String text_of_submission) {
        this.text_of_submission = text_of_submission;
    }
}
