package com.example.cengonline_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class Student_view extends AppCompatActivity {
    private EditText student_login_id;
    private EditText student_login_password;
    private EditText student_create_id;
    private EditText student_create_password;
    private Button student_login_button;
    private Button student_create_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view);


        //Temporary ids-passwords.
        final Map<String, String> student_login_map = new HashMap<String, String>();
        student_login_map.put("admin","admin");
        student_login_map.put("1234","hocam100verinnolur:(");
        student_login_map.put("FF","FF");


        //Login screen elements
        student_login_id = (EditText) findViewById(R.id.editText_student_login_id);
        student_login_password = (EditText) findViewById(R.id.editText2_student_login_password);
        student_login_button = (Button) findViewById(R.id.button_student_login);

        //Create screen elements
        student_create_id = (EditText) findViewById(R.id.editText2_student_login_id);
        student_create_password = (EditText)findViewById(R.id.editText3_student_login_password);
        student_create_button = (Button) findViewById(R.id.button2_student_create);

        student_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateStudent(student_login_id.getText().toString(),
                        student_login_password.getText().toString(),
                        student_login_map);
            }
        });


        student_create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createStudentAccount(student_create_id.getText().toString(),
                        student_create_password.getText().toString(),
                        student_login_map);
            }
        });
    }


    public void validateStudent(String id, String password, Map<String, String> student_login_map){
        for(Map.Entry<String, String> pairs : student_login_map.entrySet()){

            if(id.equals(pairs.getKey()) && password.equals(pairs.getValue())){
                Intent intent = new Intent(Student_view.this, Student_General_View.class);
                Toast.makeText(this, "Succesfully logged in.", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Check id and password. ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void createStudentAccount(String id, String password, Map<String, String> student_login_map){
        if(!id.isEmpty()  && !password.isEmpty()){
            student_login_map.put(id, password);
            Toast.makeText(this, "id and password is added. ", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "You should enter values. ", Toast.LENGTH_LONG).show();
        }

    }

}
