package com.example.habitdeveloper.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akshay.library.CurveBottomBar;
import com.example.habitdeveloper.R;
import com.example.habitdeveloper.fragment.AboutFragment;
import com.example.habitdeveloper.fragment.CalendarFragment;
import com.example.habitdeveloper.fragment.MainFragment;
import com.example.habitdeveloper.fragment.TempFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout containerView;
    private CurveBottomBar curveBottomBar;
    private FloatingActionButton floatingActionButton;
    private CalendarFragment calendarFragment;
    private MainFragment mainFragment;
    private AboutFragment aboutFragment;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        containerView = findViewById(R.id.container_view);
        textView=findViewById(R.id.view_title);
        curveBottomBar = findViewById(R.id.curveBottomBar);
        floatingActionButton=findViewById(R.id.fab);
        mainFragment=new MainFragment();
        calendarFragment=new CalendarFragment();
        aboutFragment = new AboutFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_view, mainFragment)
                .add(R.id.container_view, calendarFragment)
                .add(R.id.container_view, aboutFragment)
                .hide(calendarFragment)
                .hide(aboutFragment)
                .commitAllowingStateLoss();
        curveBottomBar.getMenu().setGroupCheckable(0,false,true);
        floatingActionButton.setImageResource(R.drawable.tab_main_selected);
        setLinsteners();
    }
    void setLinsteners(){
        floatingActionButton.setOnClickListener(onClickListener);
        curveBottomBar.setOnItemSelectedListener(changeFragment);
    }
    private NavigationBarView.OnItemSelectedListener changeFragment =new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            curveBottomBar.getMenu().setGroupCheckable(0,true,true);
            floatingActionButton.setImageResource(R.drawable.tab_main_normal);
            switch (item.getItemId()){
                case R.id.menu_calendar:
                    getSupportFragmentManager().beginTransaction()
                            .hide(mainFragment)
                            .hide(aboutFragment)
                            .show(calendarFragment)
                            .commitAllowingStateLoss();
                    textView.setText("我  的  足  迹");
                    calendarFragment.updateCircles();
                    return true;
                case R.id.menu_routine:
                    getSupportFragmentManager().beginTransaction()
                            .hide(mainFragment)
                            .hide(calendarFragment)
                            .show(aboutFragment)
                            .commitAllowingStateLoss();
                    textView.setText("心  路  历  程");
                    aboutFragment.nextWord();
                    return true;
            }
            return false;
        }
    };
    private View.OnClickListener onClickListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            curveBottomBar.getMenu().setGroupCheckable(0,false,true);
            floatingActionButton.setImageResource(R.drawable.tab_main_selected);
            textView.setText("广  告  位  招  租");

            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(calendarFragment)
                    .hide(aboutFragment)
                    .show(mainFragment)
                    .commitAllowingStateLoss();

        }
    };
}