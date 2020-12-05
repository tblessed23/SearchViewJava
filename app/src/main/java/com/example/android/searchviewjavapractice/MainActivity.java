package com.example.android.searchviewjavapractice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private AppDatabase mDatabase;
    private Application mApplication;
    private ArrayList<Contacts> allContacts=new ArrayList<>();
    private ContactAdapter mAdapter;
    private RecyclerView mRecyclerView;
private String find;

    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_TASK_ID = -1;
    private int mTaskId = DEFAULT_TASK_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout fLayout = (FrameLayout) findViewById(R.id.activity_to_do);
        // Set the RecyclerView to its corresponding view
        mRecyclerView = findViewById(R.id.product_list);

        // Set the layout for the RecyclerView to be a linear layout, which measures and
        // positions items within a RecyclerView into a linear list
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new ContactAdapter(this, allContacts);
        mRecyclerView.setAdapter(mAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTaskIntent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(addTaskIntent);
            }
        });


        mDatabase = AppDatabase.getInstance(this);


        //setupViewModel();

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);


        return true;
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getNamesFromDb(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getNamesFromDb(newText);
                return true;
            }
        });
    }

//    private void setupViewModel() {
//        // COMPLETED (9) Remove the logging and the call to loadTaskById, this is done in the ViewModel now
//        // COMPLETED (10) Declare a AddTaskViewModelFactory using mDb and mTaskId
//       MainViewModelFactory factory = new MainViewModelFactory(mApplication, mTaskId, find);
//        // COMPLETED (11) Declare a AddTaskViewModel variable and initialize it by calling ViewModelProviders.of
//        // for that use the factory created above AddTaskViewModel
//        final MainViewModel viewModel
//                = new ViewModelProvider(this).get(MainViewModel.class);
//
//        // Observe the LiveData object in the ViewModel. Use it also when removing the observer
//        viewModel.getTasks().observe(this, new Observer<Contacts>() {
//            @Override
//            public void onChanged(Contacts contacts) {
//                mAdapter.setTasks((List<Contacts>) contacts);
//            }
//        });
//
//
//    }

    private void getNamesFromDb (String search){
        String searchTextQuery = "%$searchText%";

        mDatabase.contactDao().findUserWithName(searchTextQuery).observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {
                if (contacts == null) {
                    return;
                } else {
                mAdapter = new ContactAdapter(MainActivity.this, R.layout.contact_list_layout, contacts);
                    mRecyclerView.setAdapter(mAdapter);


            }
            }
        });

    }
}

