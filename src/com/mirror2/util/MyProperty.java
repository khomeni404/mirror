package com.mirror2.util;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 03/09/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 03/09/15
 * Version : 1.0
 */
@Service
public class MyProperty {
    public final static String PORT;
    public final static int BAUD_RATE;
    public final static String MANUFACTURER;
    public final static String MODEL;
/*    public final static String USERNAME;
    public final static String PASSWORD;
    public final static String DRIVER;
    public final static String URL;*/
    public final static String COMPANY_NAME;

    static {
        Properties p = new Properties();
        try {
            InputStream is = new MyProperty().getClass().getClassLoader().getResourceAsStream("dorpan.properties");
            //InputStream is = new FileInputStream("dorpan.properties");
            p.load(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PORT = p.getProperty("modem.comPort");
        BAUD_RATE = Integer.parseInt(p.getProperty("modem.baudRate"));
        MANUFACTURER = p.getProperty("modem.manufacturer");
        MODEL = p.getProperty("modem.model");
        COMPANY_NAME = p.getProperty("company.name");

    /*    USERNAME = p.getProperty("database.connection.username");
        PASSWORD = p.getProperty("database.connection.password");
        DRIVER = p.getProperty("database.connection.driver_class");
        URL = p.getProperty("database.connection.url");
*/
    }

    public static void main(String[] args) {
        System.out.println(COMPANY_NAME);
       // main2();

    }

    public void main2() {
        try {
            Properties p = new Properties();
            String fileName = "dorpan.properties";
            InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
            p.load(is);
            String companyName =  p.getProperty("company.name");
            System.out.println("companyName = " + companyName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
