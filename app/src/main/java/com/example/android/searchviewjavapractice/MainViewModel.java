package com.example.android.searchviewjavapractice;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    //Constant for logging
    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<Contacts> tasks;
    private LiveData<List<Contacts>> findtasks;

    public MainViewModel(@NonNull Application application, int taskId, String find) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving  the tasks from the Database");
        tasks = database.contactDao().loadContactById(taskId);
        findtasks =  database.contactDao().findUserWithName(find);
    }

    public LiveData<Contacts> getTasks() {
        return tasks;
    }
    public LiveData<List<Contacts>> getFind() {
        return findtasks;
    }
}