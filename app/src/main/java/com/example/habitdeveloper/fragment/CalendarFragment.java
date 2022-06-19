package com.example.habitdeveloper.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.habitdeveloper.R;
import com.example.habitdeveloper.customview.MyCalendarView;
import com.example.habitdeveloper.habitdb.DBUtils;
import com.example.habitdeveloper.habitdb.MyDatabaseHelper;
import com.example.habitdeveloper.habitdb.entity.Record;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CalendarFragment extends Fragment {

    private MyDatabaseHelper helper;
    private SQLiteDatabase db;
    private DBUtils dbUtils;
    private MyCalendarView myCalendarView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(),R.layout.fragment_calendar,null);

        super.onCreate(savedInstanceState);

        myCalendarView = view.findViewById(R.id.myCalendarView);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        myCalendarView.setElevation(30);
        textView.setElevation(30);

        Date date = new Date();

        updateCircles();

        myCalendarView.setOnDateChangedListener(new OnDateSelectedListener(){
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                String dateString =date.getYear()+"-"+String.format("%02d", date.getMonth())+"-"+String.format("%02d", date.getDay());
                String text=dbUtils.findRecord_byDate(dateString);
                if(!text.equals("null")){
                    textView.setText(text);
                }else{
                    textView.setText(dateString+getString(R.string.calendar_undo));
                }
            }
        });


        return view;
    }

    public void updateCircles(){
        helper = dbUtils.getInstance(getActivity());
        db = helper.getWritableDatabase();//创建或打开数据库
        dbUtils=new DBUtils(db);
        List<Date> dates = new ArrayList<>();
        List<Record> records = dbUtils.getALLRecord();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<records.size(); i++){
            try {
                dates.add(simpleDateFormat.parse(records.get(i).getDates()));
            }catch(Exception e){
            }

        }
        myCalendarView.addCheckInDate(dates);

        CalendarDay calendarDay=CalendarDay.today();
        myCalendarView.setDateSelected(calendarDay,false);
        myCalendarView.setDateSelected(calendarDay,true);
    }

}