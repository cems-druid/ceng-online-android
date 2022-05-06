package com.example.cengonline_v1.PostsMVVM;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cengonline_v1.R;
import com.example.cengonline_v1.Teacher_General_View;


public class AddEditPostActivity extends AppCompatActivity {
    public static final String EXTRA_POST_ID = "com.example.cengonline_v1.PostsMVVM.EXTRA_POST_ID";
    public static final String EXTRA_POST_TEXT = "com.example.cengonline_v1.PostsMVVM.EXTRA_POST_TEXT";
    public static final String EXTRA_POST_AUTHOR = "com.example.cengonline_v1.PostsMVVM.EXTRA_POST_AUTHOR";

    private EditText editTextText;
    private EditText editTextAuthor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);


        Toolbar post_toolbar = findViewById(R.id.toolbar_post);
        setSupportActionBar(post_toolbar);

        editTextText = findViewById(R.id.post_text_edittext);
        editTextAuthor = findViewById(R.id.post_author_edittext);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_POST_ID)){
            setTitle("Edit post");
            editTextText.setText(intent.getStringExtra(EXTRA_POST_TEXT));
            editTextAuthor.setText(intent.getStringExtra(EXTRA_POST_AUTHOR));
        }
        else{
            setTitle("Add post");
        }
    }

    public void savePost(){
        String postText = editTextText.getText().toString();
        String postAuthor = editTextAuthor.getText().toString();

        if(postText.trim().isEmpty() || postAuthor.trim().isEmpty()){
            Toast.makeText(this, "Either text or author field is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent post_data = new Intent();
        post_data.putExtra(EXTRA_POST_TEXT, postText);
        post_data.putExtra(EXTRA_POST_AUTHOR, postAuthor);

        int post_id = getIntent().getIntExtra(EXTRA_POST_ID, -1);
        if(post_id != -1){
            post_data.putExtra(EXTRA_POST_ID, post_id);
        }

        setResult(RESULT_OK, post_data);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_post_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_post:
                savePost();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
