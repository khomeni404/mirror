package com.mirror2.icm.test;

import com.mirror2.util.DateUtil;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.*;

public class TEst {
    public static void main(String[] args) {
        DateTimeFormatter formatterFecha = DateTimeFormat.forPattern("MMMM, yyyy");
       // DateTime primerDiaDelMes = new DateTime().dayOfMonth().withMinimumValue();
        String desde = new LocalDate(new Date()).toString(formatterFecha);
        System.out.println("desde = " + desde);
    }
}
