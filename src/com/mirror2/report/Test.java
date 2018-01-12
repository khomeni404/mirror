package com.mirror2.report;

import com.mirror2.csd.model.Building;
import com.mirror2.util.DateUtil;
import net.sf.jasperreports.engine.JasperFillManager;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by ayatullah213574 on 29/07/2015.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Building b = new Building();
        b.setNameAlias("BB");
        b.setId(45L);
        b.setBuildingName("Jamal");

        List<Building> list = Arrays.asList(b);

        list.remove(new Building(45L));

        System.out.println(list.size());
    }

    // E:\Projects\Spring\Store\dplreport\src\main\java\com\dpl\report\resources\Director.jasper
}
