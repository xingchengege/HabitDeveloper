package com.example.habitdeveloper.habitdb.entity;


public class Record {


    private String date;

    private String record;

    public Record(){};
    public Record(String date,String record){
        this.record=record;
        this.date=date;
    }

    public String getDates() {
        return date;
    }

    public String getRecord() {
        return record;
    }

    public void setDates(String dates) {
        this.date = dates;
    }

    public void setRecord(String record) {
        this.record = record;
    }

}
