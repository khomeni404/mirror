package com.mirror2.messaging.util;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 31/08/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 31/08/15
 * Version : 1.0
 */

public class TestThread {
    public static void main(String args[]) {

        RunnableDemo R1 = new RunnableDemo( "Thread-1");
        R1.start();

        //RunnableDemo R2 = new RunnableDemo( "Thread-2");
       // R2.start();
    }

}
