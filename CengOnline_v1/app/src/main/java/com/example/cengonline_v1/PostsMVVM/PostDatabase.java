package com.example.cengonline_v1.PostsMVVM;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Post.class, version = 2)
public abstract class PostDatabase extends RoomDatabase {

    private static PostDatabase instance;
    public abstract PostDao postDao();

    public static synchronized PostDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), PostDatabase.class, "post_database")
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
        private PostDao postDao;

        private PopulateDbAsyncTask(PostDatabase db){
            postDao = db.postDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Inital post can be added
            postDao.insert(new Post("Please add your presentation to your website. ", "Cem Sinan"));
            return null;
        }
    }



}
