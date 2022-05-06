package com.example.cengonline_v1.model;

import com.example.cengonline_v1.CoursesMVVM.Course;

public class Student extends Person {

    private Course[] courses;

    public Student(String name, String surname, String id){
    super.setName(name);
    super.setSurname(surname);
    super.setId(id);
    }

    public void takeCourses(int number_of_courses){
        Course[] courses = new Course[number_of_courses];
    }



}
