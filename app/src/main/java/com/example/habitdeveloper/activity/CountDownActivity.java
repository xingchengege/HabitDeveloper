package com.example.habitdeveloper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;

import androidx.fragment.app.*;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.habitdeveloper.R;
import com.example.habitdeveloper.customview.*;
import com.example.habitdeveloper.customview.MyCalendarView;
import com.example.habitdeveloper.habitdb.DBUtils;
import com.example.habitdeveloper.habitdb.MyDatabaseHelper;
import com.example.habitdeveloper.model.*;
import com.example.habitdeveloper.model.CountDownBg;
import com.example.habitdeveloper.model.TipsModel;
import com.example.habitdeveloper.habitdb.entity.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.habitdeveloper.habitdb.DBUtils.*;
import java.util.Calendar;

public class CountDownActivity extends AppCompatActivity {

    private MyCalendarView calendarView;
    private AntiChronometer chronometer;
    private VideoView videoView;

    private CountDownBg countDownBg;
    private List<BallModel> mBallList;
    private List<TipsModel> mTipsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        init();
        super.onCreate(savedInstanceState);

        if(getSupportActionBar() != null)
            getSupportActionBar().hide();
        setContentView(R.layout.activity_countdown);

        videoView = findViewById(R.id.videoView);
        String url = "android.resource://" + getPackageName()+"/"+R.raw.v1;
        videoView.setVideoURI(Uri.parse(url));
        videoView.setOnPreparedListener(mediaPlayer -> mediaPlayer.setLooping(true));
        videoView.start();

        chronometer = findViewById(R.id.textClock);
        Intent intent = getIntent();
        String name=intent.getStringExtra("name");
        long tm=intent.getIntExtra("time", 999);

        chronometer.setOnTimeCompleteListener(()-> {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
            Toast.makeText(CountDownActivity.this, "在"+tm+" min中，你做了"+name+"呢~", Toast.LENGTH_LONG).show();

            addRecord(name, new Date());
        });
        chronometer.start(tm*60);
//        chronometer.start(5);

        init();
        countDownBg = findViewById(R.id.countDownBg);
        countDownBg.setModelList(mBallList,mTipsList);  //这是初始化设置的
        countDownBg.isCollectTips(false);
        countDownBg.setOnBallItemListener(new CountDownBg.OnBallItemListener() {
            @Override
            //在这里也可以加个统计时间的量放到自己串里面
            public void onItemClick(BallModel ballModel) {
                Toast.makeText(CountDownActivity.this,"燃烧了"+ballModel.getValue()+"的热量呢~",Toast.LENGTH_SHORT).show();
                if(mBallList.size()<2) {
                    init();
                    countDownBg.setModelList(mBallList, mTipsList);
                }
            }
        });

        countDownBg.setOnTipsItemListener(new CountDownBg.OnTipsItemListener() {
            @Override
            public void onItemClick(TipsModel tipsModel) {
                Toast.makeText(CountDownActivity.this,tipsModel.getContent(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void init(){
        if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        /*
        * 在这里可以加个随机数生成器什么的，自动选择生成数字
        *
        *
        * */
        mBallList = new ArrayList<>();
        mBallList.add(new BallModel("卡路里","5KJ"));
        mBallList.add(new BallModel("卡路里","9KJ"));
        mBallList.add(new BallModel("卡路里","1KJ"));
        mBallList.add(new BallModel("卡路里","2KJ"));
        mBallList.add(new BallModel("卡路里","12KJ"));
        mBallList.add(new BallModel("卡路里","1KJ"));
        mBallList.add(new BallModel("卡路里","8KJ"));
        mTipsList = new ArrayList<>();
        mTipsList.add(new TipsModel("今天的你还是那么的棒呢"));
        mTipsList.add(new TipsModel("我一直以为人无完人，直到我遇见了坚持的你"));
        mTipsList.add(new TipsModel("我只看到了4个字:仙女下凡"));
        mTipsList.add(new TipsModel("月色和雪色之间，你是第三种绝色"));
    }
    //开发用按钮，到时候删除，切换页面的方法
    public void nextView(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void addRecord(String name, Date day){
        Record record=new Record();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = simpleDateFormat.format(day);

        record.setDates(time);
        record.setRecord(name);
        MyDatabaseHelper instance = DBUtils.getInstance(this);
        SQLiteDatabase database = instance.getReadableDatabase();
        DBUtils db = new DBUtils(database);
        if(db.findifRecordexist_byDate(time)) {
            String origin = db.findRecord_byDate(time);
            record.setRecord(origin+", "+name);
            db.updateRecord(record);
        }
        else
            db.AddRecord(record);
        database.close();
        finish();
    }

}