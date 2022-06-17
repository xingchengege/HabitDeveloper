package com.example.habitdeveloper.habitdb;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteDatabase.CursorFactory;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;
        import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper{
    private static final String DB_Name="Habit_db";


    public MyDatabaseHelper(Context context) {
        super(context,DB_Name,null,1);
    }
    public MyDatabaseHelper(Context context,String name,CursorFactory factory,int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("tag", "+++++++++++onCreate++++++++++");
   }

    // 创建数据库不会执行，增大版本号才会执行
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 在这里可以把旧的表drop掉，从而创建新的表
    }

}