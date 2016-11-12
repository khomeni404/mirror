package com.mirror2.report;

import net.sf.jasperreports.engine.JasperFillManager;

/**
 * Created by ayatullah213574 on 29/07/2015.
 */
public class Test {
    public static void main(String[] args) {
        String workingDir = System.getProperty("user.dir");
        String reportName = "Director";
        String s2 = "\\com\\dpl\\report\\resources\\" + reportName + ".jasper";
        String ss = (workingDir+s2).replaceAll("\\\\", "/");
        System.out.println(ss);

        System.out.println(workingDir.replaceAll("\\\\", "/"));
    }

    // E:\Projects\Spring\Store\dplreport\src\main\java\com\dpl\report\resources\Director.jasper
}
