package com.example.cengonline_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button_teachers, button_students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_teachers = (Button)findViewById(R.id.button);
        button_teachers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent teacher_login_intent = new Intent(MainActivity.this, Teacher_view.class);
                startActivity(teacher_login_intent);

            }
        });

        button_students = (Button)findViewById(R.id.button_studentView);
        button_students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent student_login_intent = new Intent(MainActivity.this, Student_view.class);
                startActivity(student_login_intent);
            }
        });

    }






}
