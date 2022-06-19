package com.example.habitdeveloper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        initView();
    }

    private void initView() {
        bg.setImageResource(R.drawable.spc);

        SystemClock.sleep(3000);
        Intent lt=new Intent(Splash.this,calendar.class);
        startActivity(lt);
        finish();
    }
}
