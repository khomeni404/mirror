package com.mirror2.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * Copyright &copy; Soft Engine : www.soft-engine.net
 * Created on 14/09/12
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 11/11/15
 * Version : 2.1.2
 */

public class DateUtil {
    //96-2+9=107
    public static boolean getKhomeniTest() {
        // Test
        return true;
    }

    void abc() {
        System.out.println("void and print");
    }

    public static void main(String[] args) {


        Date someDate = getFirstDateByMMYY("1115");

        Date dayAfter = new Date(someDate.getTime() - TimeUnit.DAYS.toMillis(1));
        System.out.println(DateUtil.getCurrentMonthOfYear());

        System.out.println(getLastDateByMMYY("1115"));

        Date from = DateUtil.getFirstDayOfMonth();
        System.out.println("from = " + from);
        Date to = DateUtil.getLastDayOfMonth();
        System.out.println("to = " + to);
        //System.out.println(toDate("12/08/2015", "DD/MM/YYYY"));
         /*System.out.println(td.getCurrentYear()+td.getCurrentMonthSerial());
        System.out.println("20"+s.substring(2)+s.substring(0,2));*/
       /*
       map.put("forTheMonthOf", "January");// td.getNameOfCurrentMonth());
        map.put("totalDaysOfMonth", 30);// td.getTotalDaysOfCurrentMonth());
        map.put("pastDays", td.getPastDaysOfCurrentDate());


       System.out.println("Test"+td.getLastDateByMMYY("0215"));
        System.out.println("---------------AGE Calculation---------------");
        System.out.println("getAgeInYear('1985-05-01') = " + td.getAgeInYear("1985-05-01"));
        System.out.println("getAge('1985-05-01') = " + td.getAge("1985-05-01"));
        System.out.println("---------------Other Methods---------------");
        System.out.println("countDayBtn2Date('2014-12-23', '2014-02-26') = " + td.countDayBtn2Date("2014-12-23", "2014-02-26"));
        System.out.println("getFirstDayOfMonth() = " + td.getFirstDayOfMonth());
        System.out.println("getLastDayOfMonth() = " + td.getLastDayOfMonth());
        System.out.println("getToday() = " + td.getToday());
        System.out.println("getSmartToday() = " + td.getTodaySmart());
        System.out.println("getDateOfTheDay(5) = " + td.getDateOfTheDay(5));
        System.out.println("getDateAfterDay(25) = " + td.getDateAfterDay(25));
        System.out.println("To Date () : " + td.toDate("2014-2-04"));*/
    }

    public static Date toDate(String rawDate, String pattern) {
        Date rtDate = new Date();
        SimpleDateFormat sdf;

        //2015/12/16
        try {
            if (pattern.toUpperCase().equals("DD/MM/YYYY")) {
                sdf = new SimpleDateFormat("dd/MM/yyyy");
                rtDate = sdf.parse(rawDate);
            } else if (pattern.toUpperCase().equals("DD-MM-YYYY")) {
                sdf = new SimpleDateFormat("dd-MM-yyyy");
                rtDate = sdf.parse(rawDate);
            } else if (pattern.toUpperCase().equals("YYYY/MM/DD")) {
                sdf = new SimpleDateFormat("yyyy/MM/dd");
                rtDate = sdf.parse(rawDate);
            } else if (pattern.toUpperCase().equals("YYYY-MM-DD")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                rtDate = sdf.parse(rawDate);
            }
        } catch (ParseException pe) {
            System.out.println("Exception at DateUtil.toDate()");
        }
        return rtDate;
    }

    public static Date toDate(String rawDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(rawDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String toDate(Date date) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    //Getting difference in "2 Year 3 Month 12 Days" format between two string rawDate
    //Parameter-String format 'yyyy-mm-dd'
    public static String countDayBtn2Date(String newDate, String oldDate) {
        String difference = "";
        String[] now = newDate.split("-");
        String[] before = oldDate.split("-");
        int years = 0, months = 0, days = 0;
        try {
            years = Integer.parseInt(now[0]) - Integer.parseInt(before[0]);
            months = Integer.parseInt(now[1]) - Integer.parseInt(before[1]);
            days = Integer.parseInt(now[2]) - Integer.parseInt(before[2]);
        } catch (NumberFormatException e) {
            // TODO: handle exception
            years = 0;
            months = 0;
            days = 0;
        }

        if (days < 0) {
            days += 30;
            months -= 1;
            if (months < 0) {
                months += 12;
                years -= 1;
            }
        }
        if (months < 0) {
            months += 12;
            years -= 1;
        }
        if (years < 0 || (days == 0 && months == 0 && years == 0)) {
            difference = "There is problem in inserting rawDate/no rawDate found";
        } else {
            String day = days + " Day ", month = months + " Month ", year = years + " Year ";
            if (days == 0) day = "";
            if (months == 0) month = "";
            if (years == 0) year = "";
            difference = year + month + day;
        }
        return difference;
    }

    //Getting age in "2 Year 3 Month 12 Days" format from string birth rawDate
    //Parameter-String format 'yyyy-mm-dd'
    public static String getAge(String birth) {
        String difference = "";
        String[] now = getToday().split("-");
        String[] birthDate = birth.split("-");
        int years = Integer.parseInt(now[0]) - Integer.parseInt(birthDate[0]);
        int months = Integer.parseInt(now[1]) - Integer.parseInt(birthDate[1]);
        int days = Integer.parseInt(now[2]) - Integer.parseInt(birthDate[2]);

        if (days < 0) {
            days += 30;
            months -= 1;
            if (months < 0) {
                months += 12;
                years -= 1;
            }
        }
        if (months < 0) {
            months += 12;
            years -= 1;
        }
        if (years < 0) {
            difference = "There is problem in inserting rawDate";
        } else {
            String day = days + " Day ", month = months + " Month ", year = years + " Year ";
            if (days == 0) day = "";
            if (months == 0) month = "";
            if (years == 0) year = "";
            difference = year + month + day;
        }
        return difference;
    }

    public static Integer[] getMonthDifferenceTwoDate(Date startDate, Date endDate) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(startDate);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);
        Integer[] diff = new Integer[2];
        diff[0] = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        diff[1] = diff[0] * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        return diff;
    }

    //Getting age in YEAR
    //Parameter-String format 'yyyy-mm-dd'
    public static String getAgeInYear(String birth) {
        String age = "";
        String[] now = getToday().split("-");
        String[] birthDate = birth.split("-");
        int years = Integer.parseInt(now[0]) - Integer.parseInt(birthDate[0]);
        int months = Integer.parseInt(now[1]) - Integer.parseInt(birthDate[1]);
        int days = Integer.parseInt(now[2]) - Integer.parseInt(birthDate[2]);

        if (days < 0) {
            days += 30;
            months -= 1;
            if (months < 0) {
                months += 12;
                years -= 1;
            }
        }
        if (months < 0) {
            months += 12;
            years -= 1;
        }
        if (years < 0) {
            age = "This guy yet not born !";
        } else {
            age = years + "";
        }
        return age;
    }


    /**
     * To get The Date of yesterday
     *
     * @return <code>java.util.Date</code>
     */
    public static Date getTomorrow() {
        return new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1));
    }

    /**
     * To get next Date after a Date
     *
     * @param someDate is the parameter to pass a <code>java.util.Date</code>
     * @return <code>java.util.Date</code>
     */
    public static Date getNextDate(Date someDate) {
        return new Date(someDate.getTime() + TimeUnit.DAYS.toMillis(1));
    }

    /**
     * To get The Date of yesterday
     *
     * @return <code>java.util.Date</code>
     */
    public static Date getYesterday() {
        return new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(1));
    }

    /**
     * To get next Date before a Date
     *
     * @param someDate is the parameter to pass a <code>java.util.Date</code>
     * @return <code>java.util.Date</code>
     */
    public static Date getLastDate(Date someDate) {
        return new Date(someDate.getTime() - TimeUnit.DAYS.toMillis(1));
    }

    public static Date getDateAfterDay(Date someDate, int day) {
        return new Date(someDate.getTime() + TimeUnit.DAYS.toMillis(day));
    }

    /**
     * @param dateFrom
     * @param day
     * @return
     * @deprecated Use   <code>Date getDateAfterDay(Date someDate, int day)</code>
     */
    @Deprecated
    public static Date getDateAfterDay(String dateFrom, int day) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = "";
        String today = dateFrom;
        String lastDay = sdf.format(getLastDayOfMonth());
        int toDate = Integer.parseInt(today.substring(8, 10));
        int lastDate = Integer.parseInt(lastDay.substring(8, 10));
        int thisMonth = Integer.parseInt(today.substring(5, 7));
        int thisYear = Integer.parseInt(today.substring(0, 4));
        if ((toDate + day) <= lastDate) {
            date = thisYear + "-" + thisMonth + "-" + (toDate + day);
        } else if ((toDate + day) > lastDate) {
            if ((thisMonth + 1) > 12) {
                date = (thisYear + 1) + "-01-" + (toDate + day - lastDate);
            } else if ((thisMonth + 1) <= 12) {
                date = thisYear + "-" + (thisMonth + 1) + "-" + (toDate + day - lastDate);
            }
        }
        return toDate(date);

    }

    public static Date getDateBeforeDay(Date someDate, int day) {
        return new Date(someDate.getTime() - TimeUnit.DAYS.toMillis(day));
    }

    public static String getDateAfterToday(int day) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = "";
        String today = getToday();
        String lastDay = sdf.format(getLastDayOfMonth());
        int toDate = Integer.parseInt(today.substring(8, 10));
        int lastDate = Integer.parseInt(lastDay.substring(8, 10));
        int thisMonth = Integer.parseInt(today.substring(5, 7));
        int thisYear = Integer.parseInt(today.substring(0, 4));
        if ((toDate + day) <= lastDate) {
            date = thisYear + "-" + thisMonth + "-" + (toDate + day);
        } else if ((toDate + day) > lastDate) {
            if ((thisMonth + 1) > 12) {
                date = (thisYear + 1) + "-01-" + (toDate + day - lastDate);
            } else if ((thisMonth + 1) <= 12) {
                date = thisYear + "-" + (thisMonth + 1) + "-" + (toDate + day - lastDate);
            }
        }

        return date;
    }

    public static Date getDateAfterToday(String dateFrom, int day) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = "";
        String today = dateFrom;
        String lastDay = sdf.format(getLastDayOfMonth());
        int toDate = Integer.parseInt(today.substring(8, 10));
        int lastDate = Integer.parseInt(lastDay.substring(8, 10));
        int thisMonth = Integer.parseInt(today.substring(5, 7));
        int thisYear = Integer.parseInt(today.substring(0, 4));
        if ((toDate + day) <= lastDate) {
            date = thisYear + "-" + thisMonth + "-" + (toDate + day);
        } else if ((toDate + day) > lastDate) {
            if ((thisMonth + 1) > 12) {
                date = (thisYear + 1) + "-01-" + (toDate + day - lastDate);
            } else if ((thisMonth + 1) <= 12) {
                date = thisYear + "-" + (thisMonth + 1) + "-" + (toDate + day - lastDate);
            }
        }
        return toDate(date);


    }

    public static String getToday() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(today);
    }

    public static String getTodaySmart() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        DateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
        return sdf.format(today);
    }

    //You have to call getDateOfTheDay(4); to get 4th day rawDate of current month etc.
    public static Date getDateOfTheDay(int d) {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1 + d);  //2
        calendar.add(Calendar.DATE, -1);

        return calendar.getTime();
    }

    public static Date getFirstDayOfMonth() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 2);  //2
        calendar.add(Calendar.DATE, -1);

        return calendar.getTime();
    }


    public static Date getLastDayOfMonth() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);  //1
        calendar.add(Calendar.DATE, -1);

        return calendar.getTime();
    }

    public static String getNameOfCurrentMonth() {
        Date today = new Date();
        DateFormat sdf = new SimpleDateFormat("MMMM");
        return sdf.format(today);
    }

    public static String getNameOfMonth(int monthSerial) {
        String[] names = {"Null", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        try {
            return names[monthSerial];
        } catch (ArrayIndexOutOfBoundsException ex) {
            return names[0];
        }
    }

    public static int getCurrentMonthSerial() {
        Date today = new Date();
        DateFormat sdf = new SimpleDateFormat("MM");
        return Integer.parseInt((sdf.format(today)));
    }

    public static String getCurrentYear() {
        Date today = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy");
        return (sdf.format(today));
    }

    public static String getYear(Date anyDate) {
        DateFormat sdf = new SimpleDateFormat("yyyy");
        return (sdf.format(anyDate));
    }

    public static String getCurrentMonthOfYear() {
        Date today = new Date();
        DateFormat sdf1 = new SimpleDateFormat("MMMM");
        DateFormat sdf2 = new SimpleDateFormat("yyyy");
        return (sdf1.format(today) + ", " + sdf2.format(today));
    }


    public static Date getFirstDayOfCurrentYear() {
        return toDate((getCurrentYear() + "-01-01"));
    }

    public static Date getLastDayOfCurrentYear() {
        return toDate((getCurrentYear() + "-12-31"));
    }

    public static Date getFirstDayOfYear(Date anyDate) {
        return toDate((getYear(anyDate) + "-01-01"));
    }

    public static Date getLastDayOfYear(Date anyDate) {
        return toDate((getYear(anyDate) + "-12-31"));
    }


   /* public static int getCurrentMonthPosition() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 2);  //2
        calendar.add(Calendar.DATE, -1);

        Date firstDay = calendar.getTime();

        DateFormat sdf = new SimpleDateFormat("MM");
        return (Integer.parseInt(sdf.format(firstDay)));
    }
*/

    public static int getTotalDaysOfCurrentMonth() {
        Date lastDay = getLastDayOfMonth();
        DateFormat sdf = new SimpleDateFormat("dd");
        return Integer.parseInt(sdf.format(lastDay));
    }

    public static int getTotalDaysOfMonth(String mmYY) {
        try {
            int[] days = {31, ((Integer.parseInt(mmYY.substring(2)) % 4 == 0) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            return days[Integer.parseInt(mmYY.substring(0, 2)) - 1];
        } catch (Exception e) {
            return getTotalDaysOfCurrentMonth();
        }
    }


    public static int getPastDaysOfCurrentDate() {
        return Integer.parseInt(getToday().toString().substring(8));
    }

    //1114
    public static int getPastDaysOfMonthByMmYy(String mmYY) {
        Date[] dates = getFirstLastDate(mmYY);
        int currentYYYYmm = Integer.parseInt(getCurrentYear() + getCurrentMonthSerial());
        int inputYYYYmm = Integer.parseInt("20" + mmYY.substring(2) + mmYY.substring(0, 2));
        if (inputYYYYmm < currentYYYYmm) {
            return Integer.parseInt(toDate(dates[1]).substring(8));
        } else if (inputYYYYmm > currentYYYYmm) {
            return 0;
        } else {
            return Integer.parseInt(getToday().toString().substring(8));
        }

    }

    /**
     * @param mmYY
     * @return
     */
    public static Date getFirstDateByMMYY(String mmYY) {
        String date = "20" + mmYY.substring(2) + "-" + mmYY.substring(0, 2) + "-01";
        return toDate(date);
    }

    public static Date getLastDateByMMYY(String mmYY) throws NumberFormatException {
        int[] days = {31, ((Integer.parseInt(mmYY.substring(2)) % 4 == 0) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String date = "20" + mmYY.substring(2) + "-"
                + mmYY.substring(0, 2) + "-" + days[Integer.parseInt(mmYY.substring(0, 2)) - 1];

        return toDate(date);
    }

    public static Date[] getFirstLastDate(String mmYY) {
        Date[] dates = new Date[2];
        if (mmYY.length() != 4) {
            dates[0] = getFirstDayOfMonth();
            dates[1] = getLastDayOfMonth();
        } else {
            try {
                if (Integer.parseInt(mmYY.substring(0, 2)) <= 12) {
                    dates[0] = getFirstDateByMMYY(mmYY);
                    dates[1] = getLastDateByMMYY(mmYY);
                } else {
                    dates[0] = getFirstDayOfMonth();
                    dates[1] = getLastDayOfMonth();
                }
            } catch (NumberFormatException nf) {
                dates[0] = getFirstDayOfMonth();
                dates[1] = getLastDayOfMonth();
            }
        }
        return dates;

    }

}
