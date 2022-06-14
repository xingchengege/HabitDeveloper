package com.example.habitdeveloper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import com.example.habitdeveloper.R;
import com.example.habitdeveloper.customview.AntiChronometer;
import com.example.habitdeveloper.customview.MyCalendarView;


public class CountDownActivity extends AppCompatActivity {

    private MyCalendarView calendarView;
    private AntiChronometer chronometer;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();
        setContentView(R.layout.activity_countdown);

        videoView = findViewById(R.id.videoView);
        String url = "android.resource://" + getPackageName()+"/"+R.raw.park;
        videoView.setVideoURI(Uri.parse(url));
        videoView.start();
        chronometer = findViewById(R.id.textClock);
        chronometer.setOnTimeCompleteListener(()->
            new AlertDialog.Builder(this)
                    .setTitle("HelloWorld")
                    .show()
        );
        chronometer.start(30);

    }
}