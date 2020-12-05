package com.example.android.searchviewjavapractice;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Contacts {

    @PrimaryKey(autoGenerate = true)
    private	int	id;
    private String name;
    private String phno;

    @Ignore
    public Contacts(String name,  String phno) {
        this.name = name;
        this.phno = phno;
    }


    public Contacts(int id, String name, String phno) {
        this.id = id;
        this.name = name;
        this.phno = phno;
    }

    public Contacts() {

    }


    public Contacts[] newArray(int size) {
        return new Contacts[size];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

}
