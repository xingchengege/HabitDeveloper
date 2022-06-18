package com.example.habitdeveloper.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;


import com.example.habitdeveloper.R;
import com.example.habitdeveloper.customview.MyCalendarView;
import com.example.habitdeveloper.habitdb.DBUtils;
import com.example.habitdeveloper.habitdb.MyDatabaseHelper;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class calendar extends AppCompatActivity {

    private MyDatabaseHelper helper;
    private SQLiteDatabase db;
    private DBUtils dbUtils;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = dbUtils.getInstance(this);
        db = helper.getWritableDatabase();//创建或打开数据库
        dbUtils=new DBUtils(db);
        dbUtils.createTable();

        setContentView(R.layout.activity_calendar);
        MyCalendarView calendarView = (MyCalendarView) findViewById(R.id.myCalendarView);
        TextView textView = (TextView) findViewById(R.id.textView);
        calendarView.setElevation(30);
        textView.setElevation(30);


        CalendarDay calendarDay=CalendarDay.today();
        calendarView.setDateSelected(calendarDay,true);


        //打卡日期画圈
        Date date=new Date();
        List<Date> dates = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        for(int i=0;i<10;i++){
            calendar.add(calendar.DATE,-1); //把日期往后增加一天,整数  往后推,负数往前移动
            dates.add(calendar.getTime()); //这个时间就是日期往后推一天的结果
        }
        calendarView.addCheckInDate(dates);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener(){
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                String dateString =date.getYear()+"-"+date.getMonth()+"-"+date.getDay();
                String text=dbUtils.findRecord_byDate(dateString);
                 if(text!= "null"){
                    textView.setText(text);
                }else{
                    textView.setText(dateString+getString(R.string.calendar_undo));
                }
            }
        });

    }

}
