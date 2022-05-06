package com.example.cengonline_v1.PostsMVVM;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PostRepository {

    private PostDao postDao;
    private LiveData<List<Post>> allPosts;

    public PostRepository(Application application){
        PostDatabase database = PostDatabase.getInstance(application);
        postDao = database.postDao();
        allPosts = postDao.getAllPosts();
    }

    public void insert(Post post){
        new InsertPostAsyncTask(postDao).execute(post);
    }

    public void update(Post post){
        new UpdatePostAsyncTask(postDao).execute(post);
    }

    public void delete(Post post){
        new DeletePostAsyncTask(postDao).execute(post);
    }

    public LiveData<List<Post>> getAllPosts(){
        return allPosts;
    }

    //Creating async task because database operations (according to Room) must be done on background.
    //Insert, delete, update will have their own subclasses.

    private static class InsertPostAsyncTask extends AsyncTask<Post, Void, Void> {
        private PostDao postDao;

        private InsertPostAsyncTask(PostDao postDao){
            this.postDao = postDao;
        }

        @Override
        protected Void doInBackground(Post... posts) {
            postDao.insert(posts[0]);
            return null;
        }
    }

    private static class DeletePostAsyncTask extends AsyncTask<Post, Void, Void>{
        private PostDao postDao;

        private DeletePostAsyncTask(PostDao postDao){
            this.postDao = postDao;
        }

        @Override
        protected Void doInBackground(Post... posts) {
            postDao.delete(posts[0]);
            return null;
        }
    }

    private static class UpdatePostAsyncTask extends AsyncTask<Post, Void, Void>{
        private PostDao postDao;

        private UpdatePostAsyncTask(PostDao postDao){
            this.postDao = postDao;
        }

        @Override
        protected Void doInBackground(Post... posts) {
            postDao.update(posts[0]);
            return null;
        }
    }



}
