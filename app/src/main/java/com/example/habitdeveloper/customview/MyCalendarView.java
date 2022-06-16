package com.example.habitdeveloper.customview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;
import android.util.AttributeSet;
import android.util.Log;

import com.example.habitdeveloper.R;
import com.example.habitdeveloper.viewmodal.calendarDecorator.EventDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyCalendarView extends MaterialCalendarView {

    /**
     * 添加需要打卡的日期信息
     * @param dates 日期列表
     */
    public void addCheckInDate(List<Date> dates){
        Calendar cd = Calendar.getInstance();
        List<LocalDate> res = new ArrayList<>();
        for(Date date: dates){
            cd.setTime(date);
            Log.e("TAG", "addCheckInDate: " + String.format("%d, %d, %d\n", cd.get(Calendar.YEAR), cd.get(Calendar.MONTH)-1, cd.get(Calendar.DATE)));
            System.out.printf("%d, %d, %d\n", cd.get(Calendar.YEAR), cd.get(Calendar.MONTH)-1, cd.get(Calendar.DATE));
            res.add(LocalDate.of(cd.get(Calendar.YEAR), cd.get(Calendar.MONTH)+1, cd.get(Calendar.DATE)));
        }
        this.addDecorator(new CheckInDecorator(res));
    }

    public MyCalendarView(Context context) {
        super(context);
        initial();
    }

    public MyCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initial();
    }

    private void initial(){
        Calendar cd = Calendar.getInstance();

        this.state().edit()
        .setFirstDayOfWeek(DayOfWeek.SUNDAY)
        .setMinimumDate(CalendarDay.from(cd.get(Calendar.YEAR)-10, cd.get(Calendar.MONTH)+1, 1))
        .setMaximumDate(CalendarDay.from(cd.get(Calendar.YEAR)+10, cd.get(Calendar.MONTH)+1, cd.get(Calendar.DATE)))
        .setCalendarDisplayMode(CalendarMode.MONTHS)
        .commit();

        this.setWeekDayLabels(new String[]{"一","二","三","四","五","六","日"});

        this.setTitleFormatter(day ->{
            StringBuffer buffer = new StringBuffer();
            buffer.append(day.getYear()).append("年").append(day.getMonth()).append("月");
            return buffer;
        });

        this.setSelectionColor(0xFFBAC2D4);

        List<Date> dates=new ArrayList<>();
        dates.add(new Date(System.currentTimeMillis() - 1000 * 60));
        this.addDecorator(new EventDecorator(dates));
    }



    private static class CheckInDecorator implements DayViewDecorator {

        private final List<LocalDate> dates;

        public CheckInDecorator(List<LocalDate> dates){
            if (dates == null) {
                throw new NullPointerException();
            }
            this.dates = dates;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day.getDate());
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan((LineBackgroundSpan) (canvas, p, left, right, top, baseline, bottom, charSequence, i5, i6, i7) -> {
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.parseColor("#dcdcdc"));
                paint.setStrokeWidth(10);
                canvas.drawCircle((right-left)>>1, 40+((top-bottom)>>1), 40, paint);
            });
        }
    }


}
