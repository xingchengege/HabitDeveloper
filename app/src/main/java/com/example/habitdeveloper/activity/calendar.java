package com.example.habitdeveloper.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.example.habitdeveloper.R;
import com.example.habitdeveloper.customview.MyCalendarView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;


public class calendar extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        MyCalendarView calendarView = (MyCalendarView) findViewById(R.id.myCalendarView);
        TextView textView = (TextView) findViewById(R.id.textView);
        calendarView.setElevation(30);
        textView.setElevation(30);

        CalendarDay calendarDay=CalendarDay.today();
        calendarView.setDateSelected(calendarDay,true);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener(){
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                String dateString =date.getYear()+"-"+date.getMonth()+"-"+date.getDay();
                String text=dateString;
                if(text!= null){
                    textView.setText(dateString);
                }else{
                    textView.setText(dateString);
                }
            }
        });

    }

}
