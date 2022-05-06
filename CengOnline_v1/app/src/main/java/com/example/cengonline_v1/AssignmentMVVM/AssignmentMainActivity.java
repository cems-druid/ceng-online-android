package com.example.cengonline_v1.AssignmentMVVM;

import android.content.Intent;
import android.os.Bundle;
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

public class AssignmentMainActivity extends AppCompatActivity {

    public static final int ADD_ASSIGNMENT_REQUEST = 5;
    public static final int EDIT_ASSIGNMENT_REQUEST = 6;

    private AssignmentViewModel assignmentViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignments_layout);

        FloatingActionButton buttonAddAssignment = findViewById(R.id.assignment_add_button);
        buttonAddAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssignmentMainActivity.this, AddEditAssignmentActivity.class);
                startActivityForResult(intent,ADD_ASSIGNMENT_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.assignment_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final AssignmentAdapter assignment_adapter = new AssignmentAdapter();
        recyclerView.setAdapter(assignment_adapter);

        assignmentViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(this.getApplication()))
                .get(AssignmentViewModel.class);

        assignmentViewModel.getAllAssignments().observe(this, new Observer<List<Assignment>>() {
            @Override
            public void onChanged(List<Assignment> assignments) {
                assignment_adapter.setAssignments(assignments);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                assignmentViewModel.delete(assignment_adapter.getAssignmentAt(viewHolder.getAdapterPosition()));
                Toast.makeText(AssignmentMainActivity.this, "Assignment is deleted.", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        assignment_adapter.setOnItemClickListener(new AssignmentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Assignment assignment) {
                Intent edit_intent = new Intent(AssignmentMainActivity.this, AddEditAssignmentActivity.class);

                edit_intent.putExtra(AddEditAssignmentActivity.EXTRA_ID_ASSGN, assignment.getAssignment_id());
                edit_intent.putExtra(AddEditAssignmentActivity.EXTRA_NAME_ASSGN, assignment.getName_assignment());
                edit_intent.putExtra(AddEditAssignmentActivity.EXTRA_COURSE_ASSGN, assignment.getName_of_course());
                edit_intent.putExtra(AddEditAssignmentActivity.EXTRA_DUEDATE_ASSGN, assignment.getDue_date());

                startActivityForResult(edit_intent, EDIT_ASSIGNMENT_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_ASSIGNMENT_REQUEST && resultCode == RESULT_OK){

            Intent is_Teacher = getIntent();
            if(is_Teacher.getStringExtra("IS_TEACHER").equals("YES")){
                String assgn_name = data.getStringExtra(AddEditAssignmentActivity.EXTRA_NAME_ASSGN);
                String assgn_coursename = data.getStringExtra(AddEditAssignmentActivity.EXTRA_COURSE_ASSGN);
                String assgn_duedate = data.getStringExtra(AddEditAssignmentActivity.EXTRA_DUEDATE_ASSGN);

                Assignment assignment = new Assignment(assgn_name, assgn_coursename, assgn_duedate);
                assignmentViewModel.insert(assignment);

            }



            Toast.makeText(this, "Assignment is added. ", Toast.LENGTH_SHORT).show();
        }
        else if(requestCode == EDIT_ASSIGNMENT_REQUEST && resultCode == RESULT_OK ){
            Assignment assignment = new Assignment();
            Intent is_Teacher = getIntent();

            int assignment_id = data.getIntExtra(AddEditAssignmentActivity.EXTRA_ID_ASSGN, -1);
            if(assignment_id == -1){
                Toast.makeText(this, "Id cannot be -1. ", Toast.LENGTH_SHORT).show();
            }

            if(is_Teacher.getStringExtra("IS_TEACHER").equals("YES")) {
                String assgn_name = data.getStringExtra(AddEditAssignmentActivity.EXTRA_NAME_ASSGN);
                String assgn_coursename = data.getStringExtra(AddEditAssignmentActivity.EXTRA_COURSE_ASSGN);
                String assgn_duedate = data.getStringExtra(AddEditAssignmentActivity.EXTRA_DUEDATE_ASSGN);
                assignment.setName_assignment(assgn_name);
                assignment.setName_of_course(assgn_coursename);
                assignment.setDue_date(assgn_duedate);
            }
            String assgn_homework = data.getStringExtra(AddEditAssignmentActivity.EXTRA_HOMEWORK_ASSGN);
            assignment.setName_assignment(assgn_homework);

            assignmentViewModel.update(assignment);

            Toast.makeText(this, "Course is updated. ", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Nothing happened ?", Toast.LENGTH_SHORT).show();
        }

    }
}
