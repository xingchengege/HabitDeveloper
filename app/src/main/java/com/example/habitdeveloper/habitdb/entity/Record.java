package com.example.habitdeveloper.habitdb.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"dates"})
public class Record {

    @ColumnInfo(name="dates")
    @NonNull
    private String dates;

    @ColumnInfo(name="record")
    private String record;

    public String getDates() {
        return dates;
    }

    public String getRecord() {
        return record;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public void setRecord(String record) {
        this.record = record;
    }

}
