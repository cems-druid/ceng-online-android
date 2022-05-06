package com.example.cengonline_v1.PostsMVVM;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PostViewModel extends AndroidViewModel {
    private PostRepository repository;
    private LiveData<List<Post>> allPosts;

    public PostViewModel(@NonNull Application application) {
        super(application);
        repository = new PostRepository(application);
        allPosts = repository.getAllPosts();
    }

    public void insert(Post post){
        repository.insert(post);
    }
    public void update(Post post){
        repository.update(post);
    }
    public void delete(Post post){
        repository.delete(post);
    }

    public LiveData<List<Post>> getAllPosts(){
        return allPosts;
    }

}
