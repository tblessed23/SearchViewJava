package com.example.android.searchviewjavapractice;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Contacts.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "todolist";
    private static AppDatabase sInstance;

    private Contacts contacts;
    private int size;

    public static  AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .addCallback(new RoomDatabase.Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                db.execSQL("INSERT INTO contacts VALUES(name, phno);");
                            }
                        })
                        .allowMainThreadQueries()
                        .build();
               // sInstance.populateInitialData();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

//    /**
//     * Inserts the dummy data into the database if it is currently empty.
//     */
//    private void populateInitialData() {
//
//            runInTransaction(new Runnable() {
//                @Override
//                public void run() {
//
//                    Contacts contacts = new Contacts();
//                    contacts = contacts.newArray(size);
//
//                        contactDao().insertTask(contact);
//
//                }
//            });
//
//    }

    public abstract ContactDao contactDao();

}