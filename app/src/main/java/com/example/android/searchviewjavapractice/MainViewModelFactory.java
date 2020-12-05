package com.example.android.searchviewjavapractice;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    // COMPLETED (2) Add two member variables. One for the database and one for the taskId
    private final Application mApplication;
    private final int mTaskId;
    private final String mFind;

    // COMPLETED (3) Initialize the member variables in the constructor with the parameters received
    public MainViewModelFactory(Application application, int taskId, String find) {
        mApplication = application;
        mTaskId = taskId;
        mFind = find;

    }

    // COMPLETED (4) Uncomment the following method
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainViewModel(mApplication, mTaskId, mFind);
    }
}
