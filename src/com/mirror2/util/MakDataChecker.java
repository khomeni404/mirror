package com.mirror2.util;

/**
 * Created by DORPAN Server on 14/04/2015.
 */
public class MakDataChecker {

    public static String setDashOnEmpty(String s) {
        String rtString = null;
        if (s.trim().equals("") || s.trim().equals(null)) {
            rtString = "---";
        } else {
            rtString = s;
        }
        return rtString;
    }

    public static Double setZeroOnEmpty(Double d) {
        Double rtNum = null;
        if (d.equals("") || d.equals(null)) {
            rtNum = 0.0;
        } else {
            rtNum = d;
        }
        return rtNum;
    }
    public static Integer setZeroOnEmpty(Integer i) {
        Integer rtNum = null;
        if (i.equals("") || i.equals(null)) {
            rtNum = 0;
        } else {
            rtNum = i;
        }
        return rtNum;
    }
}
