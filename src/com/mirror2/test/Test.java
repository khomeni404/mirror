package com.mirror2.test;

import com.mirror2.messaging.threads.SmsThread;
import com.mirror2.security.DESEDE;
import com.mirror2.util.DateUtil;
import com.mirror2.util.MirrorDataList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by DORPAN Server on 05/06/2015.
 */
public class Test {
    public static void main(String[] args) {
        String username = "yesali1988";
        String password = "r01921190799";
        DESEDE desede = new DESEDE(username);
        Object[] paramArr = new Object[2];
        paramArr[0] = new String(username);
        paramArr[1] = desede.encrypt(password);
        System.out.println(paramArr[1]);
    }
    public static void main2(String[] args) {

        SmsThread startUpMsg =  new SmsThread("01717659287", "MIRROR has been started.");

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
                    //SmsThread smsThread = new SmsThread("01717659287", "System Started Up MyInit.java"+i);
                    //SmsThreadOut smsThread =  new SmsThreadOut();
                    //smsThread.start();

            }
        }, 0, 5, TimeUnit.SECONDS);

        //startUpMsg.start();

        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(new Date()));
        System.out.println(getPassword("shoelr45", "XsiSbBcVFrg="));  //20688*/

    }

    public static String getPassword(String username, String password) {
        DESEDE desede = new DESEDE(username);
        return desede.decrypt(password);
    }
}
