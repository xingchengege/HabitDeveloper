package com.example.habitdeveloper.habitdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.habitdeveloper.habitdb.DAO.ActionDao;
import com.example.habitdeveloper.habitdb.DAO.RecordDao;
import com.example.habitdeveloper.habitdb.entity.Action;
import com.example.habitdeveloper.habitdb.entity.Record;



@Database(entities = {Action.class, Record.class}, version = 1,exportSchema = false)
public abstract class HabitDatabase extends RoomDatabase {

    public abstract ActionDao getActionDao();
    public abstract RecordDao getRecordDao();

}

