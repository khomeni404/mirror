package com.mirror2.messaging;

import com.mirror2.csd.service.CsdService;
import com.mirror2.csd.service.CsdServiceImpl;
import com.mirror2.messaging.threads.SmsThread;
import com.mirror2.messaging.threads.SmsThreadOut;

/**
 * Copyright to Soft Engine Inc.
 * Created on 23/08/15
 * Edited By : Khomeni,
 * Last Edited by : Khomeni
 * Last Edited on : 23/08/15
 * Version : 1.0
 */

public class RunSms {
    public static void main(String args[]) {
        try {
           SmsThread smsThread =  new SmsThread("01717659287", "Hello This is a new SMS...");
         //   SmsThreadOut smsThread =  new SmsThreadOut();
          smsThread.start();

            //CsdServiceImpl.startService();
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error(e);
        }
    }
}
