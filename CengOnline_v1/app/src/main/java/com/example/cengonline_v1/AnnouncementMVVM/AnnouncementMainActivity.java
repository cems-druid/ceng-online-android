package com.example.cengonline_v1.AnnouncementMVVM;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cengonline_v1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AnnouncementMainActivity extends AppCompatActivity {
    //TODO: temporarily created. If the fragments are not useful for the operaiton this will be used.

    //These constants are for state numbers, similar to ids, they need to be different.
    public static final int ADD_ANNOUNCEMENT_REQUEST = 1;
    public static final int EDIT_ANNOUNCEMENT_REQUEST = 2;
    private AnnouncementViewModel announcementViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcements_layout);


        //Floating button operations
        FloatingActionButton buttonAddAnnouncement = findViewById(R.id.announcement_add_button);

        buttonAddAnnouncement.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent_button = new Intent(AnnouncementMainActivity.this, AddEditAnnouncementActivity.class);
                startActivityForResult(intent_button, ADD_ANNOUNCEMENT_REQUEST);
            }
        });


        //Recycler view creation.
        RecyclerView recyclerView = findViewById(R.id.announcement_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final AnnouncementAdapter adapter = new AnnouncementAdapter();
        recyclerView.setAdapter(adapter);

        announcementViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(this.getApplication()))
                .get(AnnouncementViewModel.class);

        announcementViewModel.getAllAnnouncements().observe(this, new Observer<List<Announcement>>() {
            @Override
            public void onChanged(List<Announcement> announcements) {
                adapter.setAnnouncements(announcements);
            }
        });







        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                announcementViewModel.delete(adapter.getAnnouncementAt(viewHolder.getAdapterPosition()));
                Toast.makeText(AnnouncementMainActivity.this, "Announcement deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);



        //This part is the update, change it with long clicking
        adapter.setOnItemClickListener(new AnnouncementAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(Announcement announcement) {
                Intent intent = new Intent(AnnouncementMainActivity.this, AddEditAnnouncementActivity.class);
                intent.putExtra(AddEditAnnouncementActivity.EXTRA_ID, announcement.getAnnouncement_id());
                intent.putExtra(AddEditAnnouncementActivity.EXTRA_TEXT, announcement.getAnnouncement_text());
                intent.putExtra(AddEditAnnouncementActivity.EXTRA_DATE, announcement.getDate_announcement());
                startActivityForResult(intent, EDIT_ANNOUNCEMENT_REQUEST);
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        //This is for adding an announcement
        if(requestCode == ADD_ANNOUNCEMENT_REQUEST && resultCode == RESULT_OK ){


            Intent isTeacher = getIntent();

            if(isTeacher.getStringExtra("IS_TEACHER").equals("YES")){
                String text = data.getStringExtra(AddEditAnnouncementActivity.EXTRA_TEXT);
                String date = data.getStringExtra(AddEditAnnouncementActivity.EXTRA_DATE);

                Announcement announcement = new Announcement(text, date);
                announcementViewModel.insert(announcement);

                Toast.makeText(this, "Announcement is saved.", Toast.LENGTH_SHORT).show();
            }



        }
        //This is for editing an already created announcement.
        else if(requestCode==EDIT_ANNOUNCEMENT_REQUEST && resultCode == RESULT_OK && data.getBooleanExtra("IS_TEACHER", false)){
            int id = data.getIntExtra(AddEditAnnouncementActivity.EXTRA_ID, -1);

            if(id==-1){
                Toast.makeText(this, "Something wrong with id", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent isTeacher = getIntent();
            if(isTeacher.getStringExtra("IS_TEACHER").equals("YES")){
                String text = data.getStringExtra(AddEditAnnouncementActivity.EXTRA_TEXT);
                String date = data.getStringExtra(AddEditAnnouncementActivity.EXTRA_DATE);
                Announcement announcement = new Announcement(text, date);
                announcement.setAnnouncement_id(id);
                announcementViewModel.update(announcement);
                Toast.makeText(this, "Announcement is created.", Toast.LENGTH_SHORT).show();
            }


        }
        else{
           String b = data.getStringExtra("IS_TEACHER");
           Toast.makeText(this, "Nothing happened !?", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_announcement_menu, menu);
        return true;
    }


}
