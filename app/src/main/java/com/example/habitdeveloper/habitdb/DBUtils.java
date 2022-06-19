package com.example.habitdeveloper.habitdb;

import android.content.Context;
import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.habitdeveloper.habitdb.entity.Action;
import com.example.habitdeveloper.habitdb.entity.Record;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


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
        String sql1 = "insert into Actiontb values ('活动id1','眼保健操',1)";
        String sql2 = "insert into Actiontb values ('活动id2','阅览自习',2)";
        String sql3 = "insert into Actiontb values ('活动id3','健身锻炼',3)";
        String sql5 = "insert into "+Record_tablename+" values ('2022-06-15','阅览自习')";
        String sql6 = "insert into "+Record_tablename+" values ('2022-06-17','广播体操')";


        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql5);
        db.execSQL(sql6);

    }





    //删除活动：已通过测试
    public boolean deleteAction(Action action) {
        //活动id不存在则删除失败
        String sql1 = "select count(*) from "+Action_tablename+" where id = '" + action.getId() + "'";    //sql语句查询是否存在该名字
        Cursor cursor = db.rawQuery(sql1, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        System.out.println(count);
        if (count < 1)      //若不存在这个名字的习惯则直接返回false
            return false;
        String sql="delete from "+Action_tablename+" where id ='"+action.getId()+"'";
        db.execSQL(sql);
        return true;
    }




    //插入活动（uuid专供版）:已通过测试
    public boolean AddAction(Action action) {
        UUID tuuid=UUID.randomUUID();
        String uuid=tuuid.toString();
        action.setId(uuid);
        String sql1 = "select count(*) from "+Action_tablename+" where id = '" + action.getId() + "'";    //sql语句查询是否存在该名字
        Cursor cursor = db.rawQuery(sql1, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if (count == 1)      //若已经存在这个id的习惯则直接返回false
            return false;

        String sql="insert into "+Action_tablename+" values ('"+action.getId()+"','"+action.getName()+"',"+action.getTimes()+")";
        db.execSQL(sql);
        return true;
    }


    //获取所有record记录：已经通过测试！
    public List<Record> getALLRecord(){
        List<Record> records=new ArrayList<Record>();
        String sql = "select count(*) from "+Record_tablename+" ";
        Cursor cursor=db.rawQuery(sql,null);
        cursor.moveToFirst();
        int count=cursor.getInt(0);
        if(count<1){
            return null;
        }
        String sql2="select * from "+Record_tablename+" ";
        Cursor c=db.rawQuery(sql2,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            Record record=new Record(c.getString(0),c.getString(1));
            records.add(record);
            c.moveToNext();
        }
        return records;
    }


    //获取已打卡的天数：已经通过测试
    public int getALLRecord_days(){
        String sql = "select count(*) from "+Record_tablename+" ";
        Cursor cursor=db.rawQuery(sql,null);
        cursor.moveToFirst();
        int count=cursor.getInt(0);
        if(count<1){
            return -1;
        }
        return count;
    }
    //通过name查找action_times:已通过测试
    public List<Action> getAllAction() {
        List<Action> actions=new ArrayList<Action>();
        //action表是否为空
        String sql = "select count(*) from "+Action_tablename+" ";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if (count < 1)
            return null;
        String sql1="select * from "+Action_tablename+" ";
        Cursor c=db.rawQuery(sql1,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            Action action=new Action(c.getString(0),c.getString(1),c.getInt(2));
            actions.add(action);
            c.moveToNext();
        }
        return actions;
    }

    //查找date对应的record是否存在：已通过测试
    //存在为：true;不存在为：false
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

    //查找并返回date对应的record：已通过测试
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
    //增加记录（PS：增加前记得要用findifRecordexist_byDate函数看一下是否存在，不存在再使用新增，否则用修改就可以了）:已经通过测试
    public void AddRecord(Record record) {
        String sql="insert into "+Record_tablename+" values ('"+record.getDates()+"','"+record.getRecord()+"')";
        db.execSQL(sql);
    }

    //更新记录: 已经通过测试
    public boolean updateRecord(Record record) {
        boolean if_exist=findifRecordexist_byDate(record.getDates());
        if(!if_exist){
            Log.i("tag", "date当天无record");
            return false;
        }
        String sql="update "+Record_tablename+" set  record = '"+record.getRecord()+"' where date  = '"+record.getDates()+"'";
        db.execSQL(sql);
        return true;
    }
}