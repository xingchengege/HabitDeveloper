package com.example.habitdeveloper.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

import java.text.SimpleDateFormat;

public class AntiChronometer extends Chronometer implements Chronometer.OnChronometerTickListener {

    private long leftTime;
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
    private OnTimeCompleteListener onTimeCompleteListener;

    /**
     * 开始计时，需要时间作为参数
     * @param times 计时时间，单位为秒
     */
    public void start(long times){
        leftTime = times;
        super.setBase(SystemClock.elapsedRealtime()+times*1000);
        super.setCountDown(true);
        super.start();
    }

    /**
     * 设置完毕后的事件，建议使用lambda表达式传参
     * @param listener 监听器
     */
    public void setOnTimeCompleteListener(OnTimeCompleteListener listener){
        onTimeCompleteListener = listener;
    }

    public AntiChronometer(Context context) {
        super(context);
        super.setOnChronometerTickListener(this);
    }

    public AntiChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setOnChronometerTickListener(this);
    }

    public AntiChronometer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setOnChronometerTickListener(this);
    }

    public AntiChronometer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        super.setOnChronometerTickListener(this);
    }

    @Override
    public void onChronometerTick(Chronometer chronometer) {
        leftTime -= 1;
        if(leftTime < 0){
            super.stop();
            if(onTimeCompleteListener != null)
                onTimeCompleteListener.onTimeComplete();
        }
    }

    public interface OnTimeCompleteListener{
        void onTimeComplete();
    }
}
