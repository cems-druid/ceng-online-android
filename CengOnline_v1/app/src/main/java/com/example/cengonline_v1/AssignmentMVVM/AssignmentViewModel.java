package com.example.cengonline_v1.AssignmentMVVM;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AssignmentViewModel extends AndroidViewModel {

    private AssignmentRepository assignmentRepository;
    private LiveData<List<Assignment>> allAssignments;

    public AssignmentViewModel(@NonNull Application application){
        super(application);
        assignmentRepository = new AssignmentRepository(application);
        allAssignments = assignmentRepository.getAllAssignments();
    }

    public void insert(Assignment assignment){
        assignmentRepository.insert(assignment);
    }

    public void update(Assignment assignment){
        assignmentRepository.update(assignment);
    }

    public void delete(Assignment assignment){
        assignmentRepository.delete(assignment);
    }

    public LiveData<List<Assignment>> getAllAssignments(){
        return allAssignments;
    }


}
