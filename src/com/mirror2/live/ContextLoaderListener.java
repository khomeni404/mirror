package com.mirror2.live;

import com.mirror2.messaging.threads.SmsThread;
import com.mirror2.messaging.threads.SmsThreadOut;

import java.util.logging.Logger;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 19/10/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 19/10/15
 * Version : 1.0
 */

public class ContextLoaderListener extends org.springframework.web.context.ContextLoaderListener {
    //private static final Logger logger = LoggerFactory.getLogger( ContextLoaderListener.class );

    public ContextLoaderListener() {
        //SmsThread smsThread = new SmsThread("01717659287", "System Started Up ContextLoaderListener.java");
        //SmsThreadOut smsThread =  new SmsThreadOut();
       // smsThread.start();
    }
}
