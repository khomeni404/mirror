package com.mirror2.messaging.test;

import com.mirror2.messaging.threads.SmsThread;

import java.math.BigDecimal;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 02/09/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 02/09/15
 * Version : 1.0
 */

public class ExecuteMe {
    public static void main(String[] args) {
        //Downloader dt1 = new Downloader("XX", "4545");
        //dt1.start();
        String s = "Dear Sir/Madam,\n" +
                "Greetings from DPL. Account of your booked apartment in DSI has been updated with your recent payment. Please check following particulars.\n" +
                "\n" +
                "ID: DPL-000056\n" +
                "Received amount: BDT 45,000/-\n" +
                "Dorpan Properties Limited";

        SmsThread smsThread = new SmsThread("01717659287", s);
        smsThread.start();
        System.out.println("SMS is sending.......");

    }

}
