package com.example.habitdeveloper.habitdb.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"id"})
public class Action {

    @ColumnInfo(name="id")
    @NonNull
    private String id;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="times")
    private int times;


    public String getId() {return this.id;}
    public String getName() {
        return name;
    }
    public int getTimes() {return times;}
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTimes(int times) {
        this.times = times;
    }
}
