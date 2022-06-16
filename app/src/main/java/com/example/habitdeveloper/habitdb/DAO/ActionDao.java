package com.example.habitdeveloper.habitdb.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.habitdeveloper.habitdb.entity.Action;

import java.util.List;

@Dao
public interface ActionDao {

    //获取所有活动
    @Query("Select * from 'Action' ")
    List<Action> getallAction();

    //删除单条活动
    @Query("DELETE FROM 'Action'")
    void deleteAction();

    //新增单条活动
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    void insert(Action action);

    //更新活动
    @Update
    void update(Action action);

    //根据id查找活动
    @Query("SELECT * FROM `Action` WHERE id= :id")
    List<Action> getActionByid(String id);

    //根据name查找活动
    @Query("SELECT * FROM `Action` WHERE name= :name")
    List<Action> getActionByname(String name);



}
