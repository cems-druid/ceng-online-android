package com.example.cengonline_v1.model;

import com.example.cengonline_v1.CoursesMVVM.Course;

public class Teacher extends Person {
    private String field;
    private Course courses;

    public Teacher(String name, String surname, String Id, String field){
    super.setName(name);
    super.setSurname(surname);
    super.setId(Id);
    this.field = field;
    }

    public Teacher(){

    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Course getCourses() {
        return courses;
    }

    public void setCourses(Course courses) {
        this.courses = courses;
    }
}
