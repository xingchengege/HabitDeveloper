package com.example.habitdeveloper.habitdb.entity;



public class Action {


    private String id;

    private String name;

    private int times;

    public Action(String id,String name,int times){
        this.id=id;
        this.name=name;
        this.times=times;
    }
    public Action(){};
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
