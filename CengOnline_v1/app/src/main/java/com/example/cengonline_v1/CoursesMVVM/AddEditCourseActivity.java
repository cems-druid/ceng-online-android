package com.example.cengonline_v1.CoursesMVVM;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cengonline_v1.R;

public class AddEditCourseActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.example.cengonline_v1.CoursesMVVM.EXTRA_ID";
    public static final String EXTRA_NAME = "com.example.cengonline_v1.CoursesMVVM.EXTRA_NAME";
    public static final String EXTRA_INFO = "com.example.cengonline_v1.CoursesMVVM.EXTRA_INFO";

    private EditText editTextName;
    private EditText editTextInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        Toolbar course_toolbar = findViewById(R.id.toolbar_course);
        setSupportActionBar(course_toolbar);

        editTextName = findViewById(R.id.course_name_edittext);
        editTextInfo = findViewById(R.id.course_info_edittext);



        //Checks if adding or editing is done
        Intent intent = getIntent();

        //we only use id when we update
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit course");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            editTextInfo.setText(intent.getStringExtra(EXTRA_INFO));
        }
        else{
            setTitle("Add course");
        }
    }

    public void saveCourse(){
        String courseName = editTextName.getText().toString();
        String courseInfo = editTextInfo.getText().toString();
    
        if(courseName.trim().isEmpty() || courseInfo.trim().isEmpty()){
            Toast.makeText(this, "Either name or info is empty.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent course_data = new Intent();
        course_data.putExtra(EXTRA_NAME, courseName);
        course_data.putExtra(EXTRA_INFO, courseInfo);

        int course_id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(course_id != -1){
            course_data.putExtra(EXTRA_ID, course_id);
        }

        setResult(RESULT_OK, course_data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_course_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.save_course:
                saveCourse();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
