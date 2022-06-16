package com.example.habitdeveloper.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.habitdeveloper.R;
import com.example.habitdeveloper.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    private LinearLayout containerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        containerView = findViewById(R.id.container_view);
        getSupportFragmentManager().beginTransaction().add(R.id.container_view, new MainFragment()).commit();
    }
}