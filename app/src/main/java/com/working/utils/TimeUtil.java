package com.working.utils;

import java.util.Calendar;

public class TimeUtil {

    public static long getTimeInMills(int year, int month, int day){
        Calendar c1 =Calendar.getInstance();  //java.util.Calendar
        c1.clear();
        c1.set(year,month,day,0,0,0);
        long mills = c1.getTimeInMillis();
        return mills;
    }
}
