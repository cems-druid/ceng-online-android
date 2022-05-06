package com.example.cengonline_v1.AssignmentMVVM;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Assignment.class, version = 1)
public abstract class AssignmentDatabase extends RoomDatabase {

    private static AssignmentDatabase instance;
    public abstract AssignmentDao assignmentDao();

    public static synchronized AssignmentDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AssignmentDatabase.class, "assignment_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallBack).build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private AssignmentDao assignmentDao;

        private PopulateDbAsyncTask(AssignmentDatabase db){
            assignmentDao = db.assignmentDao();
        }

        @Override
        protected Void doInBackground(Void... voids){
            assignmentDao.insert(new Assignment("PL final project", "Programming Languages", "24/07/2020"));
            assignmentDao.insert(new Assignment("Network final homework", "Computer Networks", "23/07/2020"));

            return null;
        }

    }


}
