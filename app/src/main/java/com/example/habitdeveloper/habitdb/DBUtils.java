package com.example.habitdeveloper.habitdb;

import android.content.Context;
import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;

import com.example.habitdeveloper.habitdb.entity.Action;

import java.util.Date;
import java.util.List;


/**
 * 封装操作数据库的工具类 和相关常量
 */
public class DBUtils {
    private static MyDatabaseHelper helper;
    private SQLiteDatabase db;
    private String Action_tablename="Actiontb";
    private String Record_tablename="Recordtb";
    public static MyDatabaseHelper getInstance(Context context){
        if(helper==null){
            helper=new MyDatabaseHelper(context);
        }
        return helper;
    }

    public DBUtils(SQLiteDatabase db){
        this.db=db;
    }

    public void createTable(){
        boolean ifnot=true;

        int count=-1;
        //判断表是否存在
        String sql="select count(*) as c from  sqlite_master where type='table' and name='Actiontb'";

        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToNext()){
            count=cursor.getInt(0);
            if(count>0){
                ifnot=false;
            }
        }

        //action表不存在则创建表
        if(ifnot){
            String sql1="create table Actiontb (id text primary key,name text,times integer)";
            String sql2="create table Recordtb (date text primary key,record text)";
            db.execSQL(sql1);
            db.execSQL(sql2);
            this.insertTestRecord();
        }
    }
    //插入测试例
    public void insertTestRecord() {
        String sql1 = "insert into Actiontb values ('活动id1','活动name1',1)";
        String sql2 = "insert into Actiontb values ('活动id2','活动name2',2)";
        String sql5 = "insert into "+Record_tablename+" values ('2022-6-15','活动1 10')";


        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql5);



    }

    //插入活动
    public boolean AddAction(Action action) {
        //活动id已存在则插入失败
        String sql1 = "select count(*) from action_tb where id = '" + action.getId() + "'";    //sql语句查询是否存在该名字
        Cursor cursor = db.rawQuery(sql1, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if (count == 1)      //若已经存在这个名字的习惯则直接返回false
            return false;
        String sql="insert into action_tb values （'"+action.getId()+"','"+action.getName()+"',"+action.getTimes()+"）";
        db.execSQL(sql);
        return true;
    }

    //更新活动的time
    public void updateAction_times(Action action) {
        String sql="update action_tb set times="+action.getTimes()+" where id='"+action.getId()+"'";
        db.execSQL(sql);
    }

    //更新活动的name
    public void updateAction_name(Action action) {
        String sql="update action_tb set name='"+action.getName()+"' where id='"+action.getId()+"'";
        db.execSQL(sql);
    }

    //删除活动
    public boolean deleteAction(Action action) {
        //活动id不存在则删除失败
        String sql1 = "select count(*) from action_tb where id = '" + action.getId() + "'";    //sql语句查询是否存在该名字
        Cursor cursor = db.rawQuery(sql1, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if (count <= 1)      //若不存在这个名字的习惯则直接返回false
            return false;
        String sql="delete from action_tb where id ='"+action.getId()+"'";
        db.execSQL(sql);
        return true;
    }

    //通过name查找action_times
    public List<Action> findAction_byid(Action action) {
        //name是否存在
        String sql = "select count(*) from action_tb where name = '" + action.getName() + "'";    //sql语句查询是否存在该名字
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if (count == 1)      //若不存在这个名字的习惯则直接返回false
            return null;
        String sql1="select times from action_tb where name = '" + action.getName() + "'";
        Cursor c=db.rawQuery(sql1,null);
        c.moveToFirst();
        Action[] actions=new Action[count];
        return null;
    }

    public boolean findifRecordexist_byDate(String date){
        String sql1 = "select count(*) from "+Record_tablename+" where date = '" +date + "'";
        String sql2 = "select record from action_tb where date = '" +date + "'";
        Cursor c=db.rawQuery(sql1,null);
        c.moveToFirst();
        int count=c.getInt(0);
        if(count<1){
            return false;
        }
        return true;
    }
    public String findRecord_byDate(String date){
        boolean f=findifRecordexist_byDate(date);
        String record="null";
        if(f){
            String sql2 = "select * from "+Record_tablename+" where date = '" +date + "'";
            Cursor cursor=db.rawQuery(sql2,null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                record = cursor.getString(1);
                cursor.moveToNext();
            }
        }
        return record;
    }
}