package com.example.cengonline_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Teacher_view extends AppCompatActivity {

    //Our UI elements
    private EditText teacher_login_id;
    private EditText teacher_login_password;
    private EditText teacher_create_id;
    private EditText teacher_create_password;
    private Button teacher_login_button;
    private Button teacher_create_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_view);

        //Temporary ids-passwords.
       final Map<String, String> teacher_login_map = new HashMap<String, String>();
       teacher_login_map.put("admin","admin");
       teacher_login_map.put("1234","teachersifre");
       teacher_login_map.put("12","12");


        //Login screen elements
        teacher_login_id = (EditText) findViewById(R.id.editText_teacher_login_id);
        teacher_login_password = (EditText) findViewById(R.id.editText2_teacher_login_password);
        teacher_login_button = (Button) findViewById(R.id.button_teacher_login);

        //Create screen elements
        teacher_create_id = (EditText) findViewById(R.id.editText2_teacher_login_id);
        teacher_create_password = (EditText)findViewById(R.id.editText3_teacher_login_password);
        teacher_create_button = (Button) findViewById(R.id.button2_teacher_create);


        teacher_login_button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                validateTeacher(teacher_login_id.getText().toString(), teacher_login_password.getText().toString(), teacher_login_map);
            }
        });
        teacher_create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               createTeacherAccount(teacher_create_id.getText().toString(),teacher_create_password.getText().toString(), teacher_login_map);
               Toast.makeText(getApplicationContext(), "The account is created", Toast.LENGTH_LONG).show();
            }
        });
    }

    //This function opens a new activity i.e a new page.
    public void validateTeacher(String id, String password, Map<String,String> teacher_login_map){
        for(Map.Entry<String, String> pairs : teacher_login_map.entrySet()){

            if(id.equals(pairs.getKey()) && password.equals(pairs.getValue())){
                Intent intent = new Intent(Teacher_view.this, Teacher_General_View.class);
                Toast.makeText(this, "Succesfully logged in.", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Check id and password. ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //This function creates a new teacher account.
    public void createTeacherAccount(String id, String password, Map<String, String> teacher_login_map){

        if(!id.isEmpty()  && !password.isEmpty()){
            teacher_login_map.put(id, password);
            Toast.makeText(this, "id and password is added. ", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "You should enter values. ", Toast.LENGTH_LONG).show();
        }

    }
}
