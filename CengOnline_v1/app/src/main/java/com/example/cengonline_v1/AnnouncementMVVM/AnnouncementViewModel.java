package com.example.cengonline_v1.AnnouncementMVVM;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AnnouncementViewModel extends AndroidViewModel {

    private AnnouncementRepository repository;
    private LiveData<List<Announcement>> allAnnouncements;

    public AnnouncementViewModel(@NonNull Application application){
        super(application);

        repository = new AnnouncementRepository(application);
        allAnnouncements = repository.getAllAnnouncements();
    }

    public void insert(Announcement announcement){
        repository.insert(announcement);
    }

    public void update(Announcement announcement){
        repository.update(announcement);
    }

    public void delete(Announcement announcement){
        repository.delete(announcement);
    }

    public LiveData<List<Announcement>> getAllAnnouncements(){
        return allAnnouncements;
    }


}
