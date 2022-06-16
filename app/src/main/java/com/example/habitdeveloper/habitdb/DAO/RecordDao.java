package com.example.habitdeveloper.habitdb.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.habitdeveloper.habitdb.entity.Record;

import java.util.List;

@Dao
public interface RecordDao {
    //获取所有记录
    @Query("Select * from 'Record' ")
    List<Record> getallRecord();

    //删除单条记录
    @Query("DELETE FROM 'Record'")
    void deleteAction();

    //新增单条记录
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    void insert(Record record);

    //更新记录
    @Update
    void update(Record record);

    //根据dates查找记录
    @Query("SELECT * FROM `Record` WHERE dates= :dates")
    Record getRecordBydates(String dates);

    //根据record查找活动
    @Query("SELECT * FROM `Record` WHERE record= :record")
    Record getRecordByrecord(String record);


}
