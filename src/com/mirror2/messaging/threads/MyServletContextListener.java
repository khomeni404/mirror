package com.mirror2.messaging.threads;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 18/10/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 18/10/15
 * Version : 1.0
 */

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

public class MyServletContextListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        try {
            //SmsThread startUpMsg =  new SmsThread("01717659287", "MyServletContextListener started.\n-System");
            //startUpMsg.start();

            //SmsThreadOut smsThread =  new SmsThreadOut();
            //smsThread.start();
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent contextEvent) {
        /* Do Shutdown stuff. */
    }

}
