package com.example.cengonline_v1.CoursesMVVM;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cengonline_v1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CourseMainActivity extends AppCompatActivity {

    public static final int ADD_COURSE_REQUEST = 3;
    public static final int EDIT_COURSE_REQUEST = 4;

    private CourseViewModel courseViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses_layout);

        FloatingActionButton buttonAddCourse = findViewById(R.id.course_floatingactionbutton);
        buttonAddCourse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CourseMainActivity.this, AddEditCourseActivity.class);
                startActivityForResult(intent, ADD_COURSE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.course_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final CourseAdapter course_adapter = new CourseAdapter();
        recyclerView.setAdapter(course_adapter);


        courseViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(this.getApplication()))
                .get(CourseViewModel.class);
        
        courseViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                course_adapter.setCourses(courses);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                courseViewModel.delete(course_adapter.getCourseAt(viewHolder.getAdapterPosition()));
                Toast.makeText(CourseMainActivity.this, "Course is deleted.", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);


        course_adapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Course course) {
                Intent edit_intent = new Intent(CourseMainActivity.this, AddEditCourseActivity.class);
                edit_intent.putExtra(AddEditCourseActivity.EXTRA_ID, course.getCourse_id());
                edit_intent.putExtra(AddEditCourseActivity.EXTRA_NAME, course.getName());
                edit_intent.putExtra(AddEditCourseActivity.EXTRA_INFO, course.getCourse_info());
                startActivityForResult(edit_intent, EDIT_COURSE_REQUEST);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_COURSE_REQUEST && resultCode == RESULT_OK ){
            Intent is_Teacher = getIntent();
            if(is_Teacher.getStringExtra("IS_TEACHER").equals("YES")){

                String name = data.getStringExtra(AddEditCourseActivity.EXTRA_NAME);
                String info = data.getStringExtra(AddEditCourseActivity.EXTRA_INFO);

                Course course = new Course(name, info);
                courseViewModel.insert(course);

                Toast.makeText(this, "Course is saved.", Toast.LENGTH_SHORT).show();
            }

        }
        else if(requestCode == EDIT_COURSE_REQUEST && resultCode == RESULT_OK ){
            int course_id = data.getIntExtra(AddEditCourseActivity.EXTRA_ID, -1);
            Intent is_Teacher = getIntent();


            if(course_id == -1){
                Toast.makeText(this, "ID cannot be -1. ", Toast.LENGTH_SHORT).show();
            }

            if(is_Teacher.getStringExtra("IS_TEACHER").equals("YES")){
                String name = data.getStringExtra(AddEditCourseActivity.EXTRA_NAME);
                String info = data.getStringExtra(AddEditCourseActivity.EXTRA_INFO);
                Course course = new Course(name, info);
                course.setCourse_id(course_id);
                courseViewModel.update(course);
                Toast.makeText(this, "The course is updated. ", Toast.LENGTH_SHORT).show();
            }


        }
        else{
            Toast.makeText(this, "nothing did happen ??", Toast.LENGTH_SHORT).show();
        }
    }


}
