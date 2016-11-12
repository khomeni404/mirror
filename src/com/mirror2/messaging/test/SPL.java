package com.mirror2.messaging.test;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 04/09/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 04/09/15
 * Version : 1.0
 */

public class SPL {

    public static void main(String[] args) {
        q6ci();
    }

    public static void q6ci() {
        int i = 6, j = 7, k;
        ++i;
        k = --i;
        j = (++j) + k + (i++);
        --j;
        System.out.println("i = " + i + " i = " + j + " i = " + k);
    }

    public static void q6cii() {
        int i = 5, j=0;
        double a = 4.667;
        double b;
        i+=a;
        b=a*i;
       // j=j+a;
       // System.out.println("i = " + i + " i = " + j + " i = " + k);
    }

    public static void q3b() {
        int x;
        for (x = -1; x <= 10; x++) {
            if (x < 5) {
                continue;
            }
            System.out.println("CSEJU");
        }
    }

    public static void q2c() {
        int i;
        for (i = 0; i <= 3; i++) {
            switch (i) {
                case 1:
                    System.out.println(i);
                case 2:
                    System.out.println(i + 1);
                default:
                    System.out.print(i + 2);
            }
        }
    }

}
