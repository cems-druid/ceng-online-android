package com.example.cengonline_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import com.example.cengonline_v1.AnnouncementMVVM.AnnouncementMainActivity;
import com.example.cengonline_v1.AssignmentMVVM.AssignmentMainActivity;
import com.example.cengonline_v1.CoursesMVVM.CourseMainActivity;
import com.example.cengonline_v1.PostsMVVM.PostMainActivity;
import com.google.android.material.navigation.NavigationView;

public class Student_General_View extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawer1;
    private Button switch_toPost_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.student_main_drawerlayout);

        drawer1 = findViewById(R.id.student_drawer_layout);

        Toolbar student_toolbar = findViewById(R.id.toolbar_student);
        setSupportActionBar(student_toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer1, student_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer1.addDrawerListener(toggle);
        toggle.syncState();

        switch_toPost_button = findViewById(R.id.button_switchTo_post);
        switch_toPost_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent post_intent = new Intent(Student_General_View.this, PostMainActivity.class);
                startActivity(post_intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_announcement:
                Intent announcement_intent = new Intent(Student_General_View.this, AnnouncementMainActivity.class);
                startActivity(announcement_intent);
                break;
            case R.id.nav_assignment:
                Intent assignment_intent = new Intent(Student_General_View.this, AssignmentMainActivity.class);
                startActivity(assignment_intent);
                break;
            case R.id.nav_courses:
                Intent course_intent = new Intent(Student_General_View.this, CourseMainActivity.class);
                startActivity(course_intent);
                break;
        }
        drawer1.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer1.isDrawerOpen(GravityCompat.START)) {
            drawer1.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
