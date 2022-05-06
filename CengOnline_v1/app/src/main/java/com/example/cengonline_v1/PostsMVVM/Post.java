package com.example.cengonline_v1.PostsMVVM;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "post_table")
public class Post {

    @PrimaryKey(autoGenerate = true)
    private int post_id;

    private String post_text;
    private String post_author;

    public Post(String post_text, String post_author){
        this.post_text = post_text;
        this.post_author = post_author;
    }

    public Post(){

    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getPost_text() {
        return post_text;
    }

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

    public String getPost_author() {
        return post_author;
    }

    public void setPost_author(String post_author) {
        this.post_author = post_author;
    }
}
