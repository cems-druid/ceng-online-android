package com.example.cengonline_v1.PostsMVVM;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cengonline_v1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PostMainActivity extends AppCompatActivity {

    public static final int ADD_POST_REQUEST = 11;
    public static final int EDIT_POST_REQUEST = 12;

    public PostViewModel postViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_layout);

        FloatingActionButton buttonAddPost = findViewById(R.id.post_floatingactionbutton);
        buttonAddPost.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostMainActivity.this, AddEditPostActivity.class);
                startActivityForResult(intent, ADD_POST_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.post_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final PostAdapter post_adapter = new PostAdapter();
        recyclerView.setAdapter(post_adapter);

        postViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(PostViewModel.class);

        postViewModel.getAllPosts().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                post_adapter.setPosts(posts);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                postViewModel.delete(post_adapter.getPostAt(viewHolder.getAdapterPosition()));
                Toast.makeText(PostMainActivity.this, "Post is deleted. ", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

      post_adapter.setOnItemClickListener(new PostAdapter.OnItemClickListener() {
          @Override
          public void onItemClick(Post post) {
              Intent edit_intent = new Intent(PostMainActivity.this, AddEditPostActivity.class);
              edit_intent.putExtra(AddEditPostActivity.EXTRA_POST_ID, post.getPost_id());
              edit_intent.putExtra(AddEditPostActivity.EXTRA_POST_TEXT, post.getPost_text());
              edit_intent.putExtra(AddEditPostActivity.EXTRA_POST_AUTHOR, post.getPost_author());
              startActivityForResult(edit_intent, EDIT_POST_REQUEST);
          }
      });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_POST_REQUEST && resultCode == RESULT_OK ){
            Intent is_Teacher = getIntent();
            if(is_Teacher.getStringExtra("IS_TEACHER").equals("YES")){
                String text = data.getStringExtra(AddEditPostActivity.EXTRA_POST_TEXT);
                String author = data.getStringExtra(AddEditPostActivity.EXTRA_POST_AUTHOR);

                Post post = new Post(text, author);
                postViewModel.insert(post);
                Toast.makeText(this, "Post is saved", Toast.LENGTH_SHORT).show();
            }



        }
        else if(requestCode == EDIT_POST_REQUEST && resultCode == RESULT_OK ){
            int post_id = data.getIntExtra(AddEditPostActivity.EXTRA_POST_ID, -1);
            if(post_id == -1){
                Toast.makeText(this, "ID cannot be -1. ", Toast.LENGTH_SHORT).show();
            }

            Intent is_Teacher = getIntent();
            if(is_Teacher.getStringExtra("IS_TEACHER").equals("YES")){
                String text = data.getStringExtra(AddEditPostActivity.EXTRA_POST_TEXT);
                String author = data.getStringExtra(AddEditPostActivity.EXTRA_POST_AUTHOR);

                Post post = new Post(text, author);
                post.setPost_id(post_id);
                postViewModel.update(post);
                Toast.makeText(this, "Post is updated. ", Toast.LENGTH_SHORT).show();
            }


        }
        else{
            Toast.makeText(this, "Weird things are happening...", Toast.LENGTH_SHORT).show();
        }

    }
}
