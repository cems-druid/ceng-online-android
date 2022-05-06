package com.example.cengonline_v1.AnnouncementMVVM;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AnnouncementRepository {

    private AnnouncementDao announcementDao;
    private LiveData<List<Announcement>> allAnnouncements;

    public AnnouncementRepository(Application application){
        AnnouncementDatabase database = AnnouncementDatabase.getInstance(application);
        announcementDao = database.announcementDao();
        allAnnouncements = announcementDao.getAllAnnouncements();
    }

    public void insert(Announcement announcement){
        new InsertAnnouncementAsyncTask(announcementDao).execute(announcement);
    }

    public void update(Announcement announcement){
        new UpdateAnnouncementAsyncTask(announcementDao).execute(announcement);
    }

    public void delete(Announcement announcement){
        new DeleteAnnouncementAsyncTask(announcementDao).execute(announcement);
    }


    public LiveData<List<Announcement>> getAllAnnouncements() {
        return allAnnouncements;
    }


    public static class InsertAnnouncementAsyncTask extends AsyncTask<Announcement, Void, Void>{

        private AnnouncementDao announcementDao;
        private InsertAnnouncementAsyncTask(AnnouncementDao announcementDao) {
            this.announcementDao = announcementDao;
        }
        @Override
        protected Void doInBackground(Announcement... announcements){
            announcementDao.insert(announcements[0]);
            return null;
        }
    }

    public static class UpdateAnnouncementAsyncTask extends AsyncTask<Announcement, Void, Void>{

        private AnnouncementDao announcementDao;
        private UpdateAnnouncementAsyncTask(AnnouncementDao announcementDao) {
            this.announcementDao = announcementDao;
        }
        @Override
        protected Void doInBackground(Announcement... announcements){
            announcementDao.update(announcements[0]);
            return null;
        }
    }

    public static class DeleteAnnouncementAsyncTask extends AsyncTask<Announcement, Void, Void>{

        private AnnouncementDao announcementDao;
        private DeleteAnnouncementAsyncTask(AnnouncementDao announcementDao) {
            this.announcementDao = announcementDao;
        }
        @Override
        protected Void doInBackground(Announcement... announcements){
            announcementDao.delete(announcements[0]);
            return null;
        }
    }




}
