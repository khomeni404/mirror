package com.mirror2.report;

import com.mirror2.util.DateUtil;
import net.sf.jasperreports.engine.JasperFillManager;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ayatullah213574 on 29/07/2015.
 */
public class Test {
    public static void main(String[] args) throws Exception{
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = formatter.format(date);
        System.out.println("Date Format with MM/dd/yyyy : "+strDate);

        formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        strDate = formatter.format(date);
        System.out.println("Date Format with dd-M-yyyy hh:mm:ss : "+strDate);

        formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        //strDate = formatter.format(date);
        System.out.println(formatter.parse("31/12/2017 12:05"));
        Date dates = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-12-31 23:59:59");
        System.out.println("dates = " + dates);

    }

    // E:\Projects\Spring\Store\dplreport\src\main\java\com\dpl\report\resources\Director.jasper
}
