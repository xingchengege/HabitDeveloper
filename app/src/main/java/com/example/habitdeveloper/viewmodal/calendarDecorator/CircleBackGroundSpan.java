package com.example.habitdeveloper.viewmodal.calendarDecorator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

public class CircleBackGroundSpan implements LineBackgroundSpan {
    @Override
    public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int lnum) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#def0ef"));
        c.drawCircle((right - left) / 2, (bottom - top) / 2 + 10, 50, paint);
    }
}