package com.example.cengonline_v1.AssignmentMVVM;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cengonline_v1.R;

public class AddEditAssignmentActivity extends AppCompatActivity {


    public static final String EXTRA_ID_ASSGN = "com.example.cengonline_v1.AssignmentMVVM.EXTRA_ID";
    public static final String EXTRA_NAME_ASSGN = "com.example.cengonline_v1.AssignmentMVVM.EXTRA_NAME";
    public static final String EXTRA_COURSE_ASSGN = "com.example.cengonline_v1.AssignmentMVVM.EXTRA_COURSE";
    public static final String EXTRA_DUEDATE_ASSGN = "com.example.cengonline_v1.AssignmentMVVM.EXTRADUEDATE";
    public static final String EXTRA_HOMEWORK_ASSGN = "com.example.cengonline_v1.AssignmentMVVM.EXTRAHOMEWORK";

    private EditText editText_AssignmentName;
    private EditText editText_AssignmentCourse;
    private EditText editText_AssignmentDueDate;
    private EditText editTExt_AssignementHomework;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        Toolbar assignment_toolbar = findViewById(R.id.toolbar_assignment);
        setSupportActionBar(assignment_toolbar);

        editText_AssignmentName = findViewById(R.id.assignment_name_edittext);
        editText_AssignmentCourse = findViewById(R.id.assignment_nameofcourse_edittext);
        editText_AssignmentDueDate = findViewById(R.id.assignment_duedate_edittext);
        editTExt_AssignementHomework = findViewById(R.id.assignment_homework_edittext);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID_ASSGN)){
            setTitle("Edit assignment");
            editText_AssignmentName.setText(intent.getStringExtra(EXTRA_NAME_ASSGN));
            editText_AssignmentCourse.setText(intent.getStringExtra(EXTRA_COURSE_ASSGN));
            editText_AssignmentDueDate.setText(intent.getStringExtra(EXTRA_DUEDATE_ASSGN));
            editTExt_AssignementHomework.setText(intent.getStringExtra(EXTRA_HOMEWORK_ASSGN));
        }
        else{
            setTitle("Add assignment ");
        }
    }

    public void saveAssignment(){
        String assignmentName = editText_AssignmentName.getText().toString();
        String assignmentCourse = editText_AssignmentCourse.getText().toString();
        String assignmentDueDate = editText_AssignmentDueDate.getText().toString();
        String assignmentHomework= editTExt_AssignementHomework.getText().toString();

        if(assignmentName.trim().isEmpty() || assignmentCourse.trim().isEmpty() || assignmentDueDate.trim().isEmpty()){
            Toast.makeText(this, "Fill all fields. ", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent assignment_data = new Intent();
        assignment_data.putExtra(EXTRA_NAME_ASSGN, assignmentName);
        assignment_data.putExtra(EXTRA_COURSE_ASSGN, assignmentCourse);
        assignment_data.putExtra(EXTRA_DUEDATE_ASSGN, assignmentDueDate);
        assignment_data.putExtra(EXTRA_HOMEWORK_ASSGN, assignmentHomework);

        int assignment_id = getIntent().getIntExtra(EXTRA_ID_ASSGN, -1);

        if(assignment_id != -1){
            assignment_data.putExtra(EXTRA_ID_ASSGN, assignment_id);
        }

        setResult(RESULT_OK, assignment_data);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_assignment_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_assignment:
                saveAssignment();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
