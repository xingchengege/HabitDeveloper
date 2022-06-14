package com.example.habitdeveloper.habitdb;

public abstract class HabitDatabase {
    private volatile static HabitDatabase INSTANCE; //单例模式

    public static HabitDatabase getInstance() {
        if(INSTANCE == null){
            synchronized (HabitDatabase.class){
                if(INSTANCE == null){
                    //创建实例
                }
            }
        }
        return INSTANCE;
    }
}
