package com.example.cengonline_v1.AssignmentMVVM;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface AssignmentDao {

    @Insert
    void insert(Assignment assignment);
    @Update
    void update(Assignment assignment);
    @Delete
    void delete(Assignment assignment);

    @Query("SELECT * FROM assignment_table")
    LiveData<List<Assignment>> getAllAssignments();
}
