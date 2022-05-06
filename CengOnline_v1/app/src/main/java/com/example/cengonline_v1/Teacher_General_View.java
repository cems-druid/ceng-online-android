package com.example.cengonline_v1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cengonline_v1.AnnouncementMVVM.AnnouncementMainActivity;
import com.example.cengonline_v1.AssignmentMVVM.AssignmentMainActivity;
import com.example.cengonline_v1.CoursesMVVM.CourseMainActivity;
import com.example.cengonline_v1.PostsMVVM.PostMainActivity;
import com.google.android.material.navigation.NavigationView;


public class Teacher_General_View extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String IS_TEACHER = "YES";
    private DrawerLayout drawer;
    private Button switchToPost_button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Toast.makeText(this, "Welcome", Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_main_drawerlayout);

        Toolbar toolbar1 = findViewById(R.id.toolbar_teacher);
        setSupportActionBar(toolbar1);

        drawer = findViewById(R.id.teacher_drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar1, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        switchToPost_button = findViewById(R.id.button_switchTo_post);
        switchToPost_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent post_intent = new Intent(Teacher_General_View.this, PostMainActivity.class);
                post_intent.putExtra("IS_TEACHER",IS_TEACHER);

                startActivity(post_intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_announcement:
                Intent announcement_intent = new Intent(Teacher_General_View.this, AnnouncementMainActivity.class);
                announcement_intent.putExtra("IS_TEACHER", IS_TEACHER);

                startActivity(announcement_intent);
                break;
            case R.id.nav_assignment:
                Intent assignment_intent = new Intent(Teacher_General_View.this, AssignmentMainActivity.class);
                assignment_intent.putExtra("IS_TEACHER", IS_TEACHER);
                startActivity(assignment_intent);
                break;
            case R.id.nav_courses:
                Intent course_intent = new Intent(Teacher_General_View.this, CourseMainActivity.class);
                course_intent.putExtra("IS_TEACHER", IS_TEACHER);
                startActivity(course_intent);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*  Button button_read, button_save;
    TextView tv1;
    EditText eT1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__general__view);

        tv1 = (TextView)findViewById(R.id.textView4);
        eT1 = (EditText)findViewById(R.id.editText);
        button_read = (Button)findViewById(R.id.button4);
        button_save = (Button)findViewById(R.id.button3);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFile();
            }
        });
        button_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
            }
        });
    }

    public void writeFile(){
        String textToSave=eT1.getText().toString();
        try {

            FileOutputStream fileoutputStream = openFileOutput("example1.txt", MODE_PRIVATE);
            fileoutputStream.write(textToSave.getBytes());
            fileoutputStream.close();
            Toast.makeText(getApplicationContext(),"Text is saved", Toast.LENGTH_LONG).show();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readFile(){
        try {

            FileInputStream fileinputstream = openFileInput("example1.txt");
            InputStreamReader inputstreamreader = new InputStreamReader(fileinputstream);

            BufferedReader buffered_reader = new BufferedReader(inputstreamreader);
            StringBuffer string_buffer = new StringBuffer();

            String lines;
            while((lines = buffered_reader.readLine()) != null){
                string_buffer.append((lines + "\n"));
            }

            tv1.setText(string_buffer.toString());

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }*/
}
