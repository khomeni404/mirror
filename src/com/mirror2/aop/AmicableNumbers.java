package com.mirror2.aop;


import java.util.Date;

/**
 * Copyright to Soft Engine Inc.
 * Created on 25/08/15
 * Edited By : Khomeni,
 * Last Edited by : Khomeni
 * Last Edited on : 25/08/15
 * Version : 1.0
 */

public class AmicableNumbers {

    public static void main(String[] args) {
        Integer findAmong = 10000000;
        int counter = 0;
        for (int i = 0; i < findAmong; i++) {
            for (int j = 0; j < i; j++) {
                if (i == getSum(j)) {
                    if (j == getSum(i)) {
                        System.out.println("They are " + j + " & " + i);
                        System.out.println(new Date());
                    }
                    if (counter % 54 == 0) {
                        System.out.println(counter);
                    }
                    counter++;
                }
            }
        }
    }

    /**
     *
     * @param n1  to get a number
     * @return  sum of its divisors except itself this.
     */
    public static Integer getSum(Integer n1) {
        Integer sumDivi = 0;
        for (int i = 1; i < n1; i++) {
            if (n1 % i == 0) {
                sumDivi += i;
            }
        }
        return sumDivi;
    }

}
