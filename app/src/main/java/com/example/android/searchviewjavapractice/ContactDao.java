package com.example.android.searchviewjavapractice;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contacts ")
    LiveData<List<Contacts>> loadAllContacts();


    @Query("SELECT * FROM contacts WHERE name LIKE :query " +
            "OR phno LIKE :query")
    LiveData<List<Contacts>> findUserWithName(String query);


    @Insert
    void insertTask(Contacts storyEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(Contacts storyEntry);

    @Delete
    void deleteTask(Contacts story);

    // Create a Query method named loadTaskById that receives an int id and returns a TaskEntry Object
    // The query for this method should get all the data for that id in the task table
    @Query("SELECT * FROM contacts WHERE id = :id")
    LiveData<Contacts> loadContactById(int id);

}

