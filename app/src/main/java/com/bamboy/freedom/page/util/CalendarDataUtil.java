package com.bamboy.freedom.page.util;

import android.annotation.SuppressLint;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarDataUtil {
    /**
     * 年
     */
    public int mYear, mCurrentYear, mLastClickYear;
    /**
     * 月
     */
    public int mMonth, mCurrentMonth, mLastClickMonth;
    /**
     * 日
     */
    public int mDay, mCurrentDay, mLastClickDay;
    /**
     * 当前周几
     */
    public int mWeek;
    /**
     * 当月天数
     */
    public int mMonthLastDay;
    /**
     * 「今日」的点的顶部距离
     */
    public int dotTop;
    /**
     * 线性插值器
     */
    public LinearInterpolator interpolatorLinear = new LinearInterpolator();
    /**
     * 插值器
     */
    public OvershootInterpolator interpolator = new OvershootInterpolator(0.9f);


    /**
     * 处理日期数据
     *
     * @param timestamp 时间戳
     */
    @SuppressLint("SimpleDateFormat")
    public void setDate(long timestamp) {
        Date date = new Date(timestamp);

        // 获取年
        String year = new SimpleDateFormat("yyyy").format(date);
        mYear = Integer.parseInt(year);
        mCurrentYear = mYear;

        // 获取月
        String month = new SimpleDateFormat("M").format(date);
        mMonth = Integer.parseInt(month);
        mCurrentMonth = mMonth;

        // 获取日
        String day = new SimpleDateFormat("d").format(date);
        mDay = Integer.parseInt(day);
        mCurrentDay = mDay;
    }

    /**
     * 计算是周几
     *
     * @return 当月第一天周几
     */
    public int getMonthFirstWeekLabel() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, mYear);
        calendar.set(Calendar.MONTH, mMonth - 1);
        calendar.set(Calendar.DATE, 1);//将今天设为1号
        //calendar.set(Calendar.dat, 1);//把日期设置为当月第一天
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        switch (week) {
            case Calendar.MONDAY:
                return 1;
            case Calendar.TUESDAY:
                return 2;
            case Calendar.WEDNESDAY:
                return 3;
            case Calendar.THURSDAY:
                return 4;
            case Calendar.FRIDAY:
                return 5;
            case Calendar.SATURDAY:
                return 6;
            case Calendar.SUNDAY:
                return 7;
        }
        return 1;
    }

    /**
     * 得到指定月的天数
     */
    public int getMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, mYear);
        calendar.set(Calendar.MONTH, mMonth - 1);
        calendar.set(Calendar.DATE, 1);//把日期设置为当月第一天
        calendar.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        return calendar.get(Calendar.DATE);
    }

}
