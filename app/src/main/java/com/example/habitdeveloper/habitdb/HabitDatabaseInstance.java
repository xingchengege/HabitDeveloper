package com.example.habitdeveloper.habitdb;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.room.Room;
import androidx.room.RoomDatabase;


public class HabitDatabaseInstance extends Application {
    private static final String DB_NAME="roomdatabase.db";
    public static HabitDatabase habitDatabaseinstance;

    public static Application application;

    @Override
    public void onCreate() {

        super.onCreate();
        application=this;
        habitDatabaseinstance=Room.databaseBuilder(this,HabitDatabase.class,DB_NAME).build();

    };

}
