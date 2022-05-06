package com.example.cengonline_v1.CoursesMVVM;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Course.class, version = 1)
public abstract class CourseDatabase extends RoomDatabase {

    private static CourseDatabase instance;
    public abstract CourseDao courseDao();

    public static synchronized CourseDatabase getInstance(Context context){
        //if there is not any database regarding the entity, create one.
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), CourseDatabase.class, "course_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallBack).build();
        }

        return instance;
    }

    //This is the initial callback of the database, we done this in order to see some elements in the database.
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };


    //This subclass helps us to put initial entities. It creates it.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private CourseDao courseDao;

        private PopulateDbAsyncTask(CourseDatabase db){
            courseDao = db.courseDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            courseDao.insert(new Course("Programming languages", "This course is for basics of programming languages. "));
            courseDao.insert(new Course("Computer Networks", "This course is for computer networks. "));
            courseDao.insert(new Course("Software Engineering", "This course is for basics of software development. "));
            return null;
        }
    }




}
