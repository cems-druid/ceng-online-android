package com.example.cengonline_v1.AnnouncementMVVM;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "announcement_table")
public class Announcement {
    @PrimaryKey(autoGenerate = true)
    private int announcement_id;
    private String date_announcement;
    private String announcement_text;

    public Announcement(String announcement_text, String date_of_announcement){
        this.announcement_text = announcement_text;
        this.date_announcement = date_of_announcement;
    }

    public Announcement(){

    }


    public int getAnnouncement_id(){
        return announcement_id;
    }
    public void setAnnouncement_id(int set_announcement_id){
        this.announcement_id = set_announcement_id;
    }

    public String getDate_announcement() {
        return date_announcement;
    }

    public void setDate_announcement(String date_announcement) {
        this.date_announcement = date_announcement;
    }

    public String getAnnouncement_text() {
        return announcement_text;
    }

    public void setAnnouncement_text(String announcement_text) {
        this.announcement_text = announcement_text;
    }
}
