package com.example.cengonline_v1.AnnouncementMVVM;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Announcement.class, version = 1)
public abstract class AnnouncementDatabase extends RoomDatabase {

    private static AnnouncementDatabase instance;

    public abstract AnnouncementDao announcementDao();

    public static synchronized AnnouncementDatabase getInstance(Context context){

        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AnnouncementDatabase.class, "announcement_table")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();

        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends  AsyncTask<Void, Void, Void>{
        private AnnouncementDao announcementDao;

        private PopulateDbAsyncTask(AnnouncementDatabase db){
            announcementDao = db.announcementDao();
        }

        //TODO add sample elements for announcements dont forget to create a date object for this.
        @Override
        protected Void doInBackground(Void... voids){

            announcementDao.insert(new Announcement("Sample announcement", "01/01/2020"));
            return null;
        }


    }


}
