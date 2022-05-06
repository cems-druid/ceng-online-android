package com.example.cengonline_v1.AnnouncementMVVM;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cengonline_v1.R;

public class AddEditAnnouncementActivity extends AppCompatActivity {

    //These extra strings might changed into com.example.cengonlive_v1.model or more.

    public static final String EXTRA_ID = "com.example.cengonline_v1.AnnouncementMVVM.EXTRA_ID";
    public static final String EXTRA_TEXT = "com.example.cengonline_v1.AnnouncementMVVM.EXTRA_TEXT";
    public static final String EXTRA_DATE = "com.example.cengonline_v1.AnnouncementMVVM.EXTRA_DATE";

    private TextView editText;
    private TextView editDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcement);

        Toolbar announcement_toolbar = findViewById(R.id.toolbar_announcement);
        setSupportActionBar(announcement_toolbar);

        editText = findViewById(R.id.announcement_edit_text);
        editDate = findViewById(R.id.announcement_edit_date);


        //this one gets nullpointerexception error for some unknown reason.
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black);
        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Announcement");
            editText.setText(intent.getStringExtra(EXTRA_TEXT));
            editDate.setText(intent.getStringExtra(EXTRA_DATE));
        }
        else{
            setTitle("Add Announcement");
        }
    }


    public void saveAnnouncement(){
        String text = editText.getText().toString();
        String date = editDate.getText().toString();

        if(text.trim().isEmpty() || date.trim().isEmpty()){
            Toast.makeText(this, "Please fill both of the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TEXT, text);
        data.putExtra(EXTRA_DATE, date);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);

        if(id!=-1){
            data.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_announcement_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.save_announcement:
                saveAnnouncement();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
