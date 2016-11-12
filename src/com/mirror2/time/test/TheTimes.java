package com.mirror2.time.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TheTimes {
    public static void main(String[] args) {
        String myTime = "10:30:54";
    }

    public String getCurrentTimeString(){
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    public String makeTimeString(String myTime){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(myTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedTime = sdf.format(date);

        return formattedTime;
    }

    public String getCurrentTime(){
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    public Date makeTime(String myTime){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(myTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
