package com.example.habitdeveloper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.widget.ImageView;

import com.example.habitdeveloper.R;

public class Splash extends AppCompatActivity {
    private ImageView bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        bg=findViewById(R.id.imageView);
        bg.setImageResource(R.drawable.spc);
        CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent lt=new Intent(Splash.this,MainActivity.class);
                startActivity(lt);
                finish();
            }
        };
        countDownTimer.start();
    }
}
