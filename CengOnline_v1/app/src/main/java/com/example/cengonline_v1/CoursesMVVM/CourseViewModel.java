package com.example.cengonline_v1.CoursesMVVM;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {
    private CourseRepository repository;
    private LiveData<List<Course>> allCourses;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        repository = new CourseRepository(application);
        allCourses = repository.getAllCourses();
    }

    public void insert(Course course){
        repository.insert(course);
    }

    public void delete(Course course){
        repository.delete(course);
    }

    public void update(Course course){
        repository.update(course);
    }

    public LiveData<List<Course>> getAllCourses(){
        return allCourses;
    }

}
