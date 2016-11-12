package com.mirror2.csd.test;

import com.mirror2.csd.util.OrdinalNumber;
import com.mirror2.security.DESEDE;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {

        Integer i = 21;
        System.out.println(getPassword("md", "fvgLpLGSVTnrCyiZ9Grh/g=="));
        //System.out.println(i+new OrdinalNumber().getOrdinalNumber(i));

    }

    public static String getPassword(String username, String password) {
        DESEDE desede = new DESEDE(username);
        return desede.decrypt(password);
    }
}
