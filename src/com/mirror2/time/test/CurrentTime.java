package com.mirror2.time.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentTime {

    public static void main(String[] args) {
        System.out.println(new CurrentTime().getCurrentTime());
    }

    public String getCurrentTime(){
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }
}