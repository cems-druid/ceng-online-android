package com.example.cengonline_v1.AssignmentMVVM;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AssignmentRepository {

    private AssignmentDao assignmentDao;
    private LiveData<List<Assignment>> allAssignments;

    public AssignmentRepository(Application application){
        AssignmentDatabase assignmentDatabase = AssignmentDatabase.getInstance(application);
        assignmentDao = assignmentDatabase.assignmentDao();
        allAssignments = assignmentDao.getAllAssignments();

    }


    public void insert(Assignment assignment){
        new InsertAssignmentAsyncTask(assignmentDao).execute(assignment);
    }

    public void update(Assignment assignment){
        new UpdateAssignmentAsyncTask(assignmentDao).execute(assignment);
    }

    public void delete(Assignment assignment){
        new DeleteAssignmentAsyncTask(assignmentDao).execute(assignment);
    }

    public LiveData<List<Assignment>> getAllAssignments(){
        return allAssignments;
    }


    private static class InsertAssignmentAsyncTask extends AsyncTask<Assignment, Void, Void> {
        private AssignmentDao assignmentDao;

        private InsertAssignmentAsyncTask(AssignmentDao assignmentDao){
            this.assignmentDao = assignmentDao;
        }

        @Override
        protected Void doInBackground(Assignment... assignments) {
            assignmentDao.insert(assignments[0]);
            return null;
        }
    }


    private static class UpdateAssignmentAsyncTask extends AsyncTask<Assignment, Void, Void> {
        private AssignmentDao assignmentDao;

        private UpdateAssignmentAsyncTask(AssignmentDao assignmentDao){
            this.assignmentDao = assignmentDao;
        }

        @Override
        protected Void doInBackground(Assignment... assignments) {
            assignmentDao.update(assignments[0]);
            return null;
        }
    }


    private static class DeleteAssignmentAsyncTask extends AsyncTask<Assignment, Void, Void> {
        private AssignmentDao assignmentDao;

        private DeleteAssignmentAsyncTask(AssignmentDao assignmentDao){
            this.assignmentDao = assignmentDao;
        }

        @Override
        protected Void doInBackground(Assignment... assignments) {
            assignmentDao.delete(assignments[0]);
            return null;
        }
    }



}
