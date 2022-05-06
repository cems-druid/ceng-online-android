package com.example.cengonline_v1.AnnouncementMVVM;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AnnouncementDao {

    @Insert
    public void insert(Announcement announcement);
    @Update
    public void update(Announcement announcement);
    @Delete
    public void delete(Announcement announcement);

    @Query("SELECT * FROM announcement_table") //just gets from the database
    LiveData<List<Announcement>> getAllAnnouncements();








}
